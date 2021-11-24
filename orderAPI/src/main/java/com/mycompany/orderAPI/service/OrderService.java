package com.mycompany.orderAPI.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.orderAPI.dao.OrderDao;
import com.mycompany.orderAPI.dao.OrderDetailDao;
import com.mycompany.orderAPI.dao.PaymentDao;
import com.mycompany.orderAPI.dto.member.Member;
import com.mycompany.orderAPI.dto.order.Order;
import com.mycompany.orderAPI.dto.order.OrderDetail;
import com.mycompany.orderAPI.dto.order.Payment;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderDetailDao orderDetailDao;
	@Resource
	private PaymentDao paymentDao;
	
	public Map<String, Object> getOrderInfo(
			Member member,
			String orderId) {
		
		Map<String, Object> map = new HashMap<>();
		log.info(orderId);
		log.info(member.getMemberId());
		Order order = orderDao.selectByOidMid(orderId, member.getMemberId());
		
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
	
	public void insert(Order order) {
		log.info("실행");
		orderDao.insert(order);
	}
	
	public void updateState(Order order) {
		log.info("실행");
		orderDao.updateState(order);
	}
	
	public void insert(OrderDetail orderDetail) {
		log.info("실행");
		orderDetailDao.insert(orderDetail);
	}
	
	public List<OrderDetail> getOrderDetail(String orderId) {
		log.info("실행");
		return orderDetailDao.selectByOid(orderId);
	}
	
	public void updateState(OrderDetail orderDetail) {
		log.info("실행");
		orderDetailDao.updateState(orderDetail);
	}
	
	public void insert(Payment payment) {
		log.info("실행");
		paymentDao.insert(payment);
	}
	
	public List<Payment> getPayments(String orderId) {
		log.info("실행");
		return paymentDao.selectByOid(orderId);
	}
	
	public void updateState(Payment payment) {
		log.info("실행");
		paymentDao.updateState(payment);
	}

}
