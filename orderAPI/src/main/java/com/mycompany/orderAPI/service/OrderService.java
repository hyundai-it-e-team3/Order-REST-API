package com.mycompany.orderAPI.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mycompany.orderAPI.dao.orderDB.OTimelineDao;
import com.mycompany.orderAPI.dao.orderDB.OdTimelineDao;
import com.mycompany.orderAPI.dao.orderDB.OrderDao;
import com.mycompany.orderAPI.dao.orderDB.OrderDetailDao;
import com.mycompany.orderAPI.dao.orderDB.PaymentDao;
import com.mycompany.orderAPI.dto.member.MemberCoupon;
import com.mycompany.orderAPI.dto.member.Point;
import com.mycompany.orderAPI.dto.order.Order;
import com.mycompany.orderAPI.dto.order.OrderDetail;
import com.mycompany.orderAPI.dto.order.Payment;
import com.mycompany.orderAPI.dto.product.StockDTO;
import com.mycompany.orderAPI.service.PointService.PointResult;
import com.mycompany.orderAPI.service.StockService.StockResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	public enum OrderResult {
		SUCCESS,
		FAIL
	}
	
	@Resource
	PointService pointService;
	@Resource
	StockService stockService;
	@Resource
	MemberCouponService memberCouponService;
	
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderDetailDao orderDetailDao;
	@Resource
	private PaymentDao paymentDao;
	@Resource 
	private OdTimelineDao odTimelineDao;
	@Resource
	private OTimelineDao oTimelineDao;
	
	public Map<String, Object> getOrderInfo(
			String orderId) {
		
		Map<String, Object> map = new HashMap<>();
		log.info(orderId);
		Order order = orderDao.selectByOid(orderId);
		
		if(order == null) {
			map.put("result", "fail");
		} else {
			map.put("result", "success");
			map.put("order", order);
			
			List<OrderDetail> odList = orderDetailDao.selectByOid(orderId);
			List<Payment> payment = paymentDao.selectByOid(orderId);
			
			map.put("orderDetails", odList);
			map.put("payments", payment);
		}
		return map;
	}
	
	
	public List<Order> getOrders(String memberId) {
		log.info("실행");
		return orderDao.selectByMid(memberId);
	}
	
	@Transactional
	public OrderResult insertOrder(Order order) {
		log.info("실행");
		//Order 주문 처리
		orderDao.insert(order);
		oTimelineDao.insert(order);
		
		//Order 결제 처리
		List<Payment> pList = order.getPaymentList();
		for(Payment payment : pList) {
			//orderDB 주문 처리
			if(payment.getTypeCode() != 0) {
				payment.setOrderId(order.getOrderId());
				paymentDao.insert(payment);
			}
			
			//Member DB 주문 처리
			if(payment.getTypeCode() == 1) {
				//쿠폰 사용 처리
				MemberCoupon coupon = new MemberCoupon();
				coupon.setMemberId(order.getMemberId());
				coupon.setCouponId(order.getCouponId());
				
				memberCouponService.useMemberCoupon(coupon);
			} else if(payment.getTypeCode() == 2) {
				//포인트 사용 처리
				Point usePoint = new Point();
				usePoint.setMemberId(order.getMemberId());
				usePoint.setOrderId(order.getOrderId());
				usePoint.setPoint(payment.getPrice());
				usePoint.setType("사용");
				
				PointResult pr = pointService.insertUsePoint(usePoint);
				if(pr != PointResult.SUCCESS) return OrderResult.FAIL;
			}
		}
		
		//Order 상품 처리
		List<OrderDetail> odList = order.getOrderDetailList();
		for(OrderDetail orderDetail : odList) {
			//Order DB 주문 처리
			orderDetail.setOrderId(order.getOrderId());
			orderDetail.setStateCode(1);
			orderDetailDao.insert(orderDetail);
			odTimelineDao.insert(orderDetail);
			
			//Product DB 주문 처리
			StockDTO stock = new StockDTO();
			stock.setPsize(orderDetail.getPsize());
			stock.setProductDetailId(orderDetail.getProductDetailId());
			stock.setAmount(orderDetail.getAmount());
			StockResult sr = stockService.minusStock(stock);
			if(sr != StockResult.SUCCESS) return OrderResult.FAIL;
		}
		return OrderResult.SUCCESS;
	}
	
	@Transactional
	public String insert(Order order) {
		log.info("실행");
		orderDao.insert(order);
		oTimelineDao.insert(order);
		return order.getOrderId();
	}
	
	@Transactional
	public void updateState(Order order) {
		log.info("실행");
		orderDao.updateState(order);
		oTimelineDao.insert(order);
	}
	
	@Transactional
	public void insert(OrderDetail orderDetail) {
		log.info("실행");
		orderDetailDao.insert(orderDetail);
		odTimelineDao.insert(orderDetail);
	}
	
	public List<OrderDetail> getOrderDetail(String orderId) {
		log.info("실행");
		return orderDetailDao.selectByOid(orderId);
	}
	
	@Transactional
	public void updateState(OrderDetail orderDetail) {
		log.info("실행");
		orderDetailDao.updateState(orderDetail);
		odTimelineDao.insert(orderDetail);
	}
	
	@Transactional
	public void insert(Payment payment) {
		log.info("실행");
		paymentDao.insert(payment);
	}
	
	public List<Payment> getPayments(String orderId) {
		log.info("실행");
		return paymentDao.selectByOid(orderId);
	}
	
	@Transactional
	public void updateState(Payment payment) {
		log.info("실행");
		paymentDao.updateState(payment);
	}
	
	public List<OrderDetail> getOrderProducts(String orderId) {
		log.info("실행");
		return orderDetailDao.selectByOid(orderId);
	}
	
	public OrderResult updateAddress(Order order) {
		log.info("실행");
		Order nOrder = orderDao.selectByOid(order.getOrderId());
		if(nOrder.getStateCode() != 1) return OrderResult.FAIL;
		
		orderDao.updateAddress(order);
		return OrderResult.SUCCESS;
	}
	
	@Transactional
	public OrderResult cancelOrder(Order order) {
		log.info("실행");
		Order nOrder = orderDao.selectByOid(order.getOrderId());
		if(nOrder.getStateCode() != 1 && nOrder.getStateCode() != 4) return OrderResult.FAIL;
		
		order.setStateCode(0);
		orderDao.updateState(order);
		
		//결제 환불 처리
		List<Payment> paymentList = paymentDao.selectByOid(order.getOrderId());
		for(Payment payment : paymentList) {
			//Member DB 환불 처리
			if(payment.getType().equals("쿠폰")) {
				//쿠폰 환불 로직
				MemberCoupon coupon = new MemberCoupon();
				coupon.setMemberId(order.getMemberId());
				coupon.setCouponId(order.getCouponId());
				
				memberCouponService.refundMemberCoupon(coupon);
			} else if(payment.getType().equals("포인트")) {
				//포인트 환불 로직
			}
			
			// Order DB 환불 처리
			payment.setStateCode(0);
			paymentDao.updateStateByOid(payment);
		}
		
		//상품 환불 처리
		List<OrderDetail> orderDetailList = orderDetailDao.selectByOid(order.getOrderId());
		for(OrderDetail orderDetail : orderDetailList) {
			//ProductDB 환불처리
			StockDTO stock = new StockDTO();
			stock.setPsize(orderDetail.getPsize());
			stock.setProductDetailId(orderDetail.getProductDetailId());
			stock.setAmount(orderDetail.getAmount());
			StockResult sr = stockService.addStock(stock);
			if(sr != StockResult.SUCCESS) return OrderResult.FAIL;
			
			//OrderDB 환불처리
			orderDetail.setStateCode(0);
			orderDetailDao.updateStateByOid(orderDetail);
		}
		
		return OrderResult.SUCCESS;
	}

}
