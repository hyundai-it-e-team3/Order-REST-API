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
import com.mycompany.orderAPI.dao.orderDB.PTimelineDao;
import com.mycompany.orderAPI.dao.orderDB.PaymentDao;
import com.mycompany.orderAPI.dto.member.DetailPoint;
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
	private OrderDao orderDao;
	@Resource
	private OrderDetailDao orderDetailDao;
	@Resource
	private PaymentDao paymentDao;
	@Resource 
	private OdTimelineDao odTimelineDao;
	@Resource
	private OTimelineDao oTimelineDao;
	@Resource
	private PTimelineDao pTimelineDao;
	
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
		orderDao.insert(order);
		oTimelineDao.insert(order);
		
		List<Payment> pList = order.getPaymentList();
		for(Payment payment : pList) {
			payment.setOrderId(order.getOrderId());
			paymentDao.insert(payment);
			pTimelineDao.insert(payment);
			if(payment.getType() == 2) {
				Point usePoint = new Point();
				usePoint.setMemberId(order.getMemberId());
				usePoint.setOrderId(order.getOrderId());
				usePoint.setPoint(payment.getPrice());
				usePoint.setType("사용");
				
				PointResult pr = pointService.insertUsePoint(usePoint);
				if(pr != PointResult.SUCCESS) return OrderResult.FAIL;
			}
		}
		
		List<OrderDetail> odList = order.getOrderDetailList();
		for(OrderDetail orderDetail : odList) {
			orderDetail.setOrderId(order.getOrderId());
			orderDetailDao.insert(orderDetail);
			odTimelineDao.insert(orderDetail);
			StockDTO stock = new StockDTO();
			stock.setPsize(orderDetail.getPsize());
			stock.setProductDetailId(orderDetail.getProductDetailId());
			stock.setAmount(orderDetail.getAmount());
			StockResult sr = stockService.minusStock(stock);
			if(sr != StockResult.SUCCESS) return OrderResult.FAIL;
		}
		return OrderResult.SUCCESS;
	}
	
	public String insert(Order order) {
		log.info("실행");
		orderDao.insert(order);
		oTimelineDao.insert(order);
		return order.getOrderId();
	}
	
	public void updateState(Order order) {
		log.info("실행");
		orderDao.updateState(order);
		oTimelineDao.insert(order);
	}
	
	public void insert(OrderDetail orderDetail) {
		log.info("실행");
		orderDetailDao.insert(orderDetail);
		odTimelineDao.insert(orderDetail);
	}
	
	public List<OrderDetail> getOrderDetail(String orderId) {
		log.info("실행");
		return orderDetailDao.selectByOid(orderId);
	}
	
	public void updateState(OrderDetail orderDetail) {
		log.info("실행");
		orderDetailDao.updateState(orderDetail);
		odTimelineDao.insert(orderDetail);
	}
	
	public void insert(Payment payment) {
		log.info("실행");
		paymentDao.insert(payment);
		pTimelineDao.insert(payment);
	}
	
	public List<Payment> getPayments(String orderId) {
		log.info("실행");
		return paymentDao.selectByOid(orderId);
	}
	
	public void updateState(Payment payment) {
		log.info("실행");
		paymentDao.updateState(payment);
		pTimelineDao.insert(payment);
	}
	
	public List<OrderDetail> getOrderProducts(String orderId) {
		log.info("실행");
		return orderDetailDao.selectByOid(orderId);
	}

}
