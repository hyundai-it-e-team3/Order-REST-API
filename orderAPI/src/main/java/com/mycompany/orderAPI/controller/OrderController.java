package com.mycompany.orderAPI.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.orderAPI.dto.member.Member;
import com.mycompany.orderAPI.dto.order.Order;
import com.mycompany.orderAPI.dto.order.OrderDetail;
import com.mycompany.orderAPI.dto.order.Payment;
import com.mycompany.orderAPI.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController{
	@Resource
	private OrderService orderService;
	
	@PostMapping
	public void insert(@RequestBody Order order) {
		log.info("실행");
		log.info("order : " + order);
		
		orderService.insert(order);
	}
	
	@GetMapping("/list")
	public List<Order> list(@RequestBody Member member) {
		log.info("실행");
		log.info("member : " + member);
		
		List<Order> list = orderService.getOrders(member.getMemberId()); 
		return list;
	}
	
	@GetMapping("/{orderId}") 
	public Map<String, Object> getOrderInfo(
			@PathVariable String orderId, 
			@RequestBody Member member) {
		log.info("실행");
		log.info("member : " + member);

		return orderService.getOrderInfo(member, orderId);
	}
	
	@PatchMapping("/state")
	public void updateState(@RequestBody Order order) {
		log.info("실행");
		log.info("order : " + order);
		
		orderService.updateState(order);
	}
	
	@PostMapping("/detail")
	public void insert(@RequestBody OrderDetail orderDetail) {
		log.info("실행");
		log.info("orderDetail : " + orderDetail);
		
		orderService.insert(orderDetail);
	}
	
	@GetMapping("/detail")
	public List<OrderDetail> detail(@RequestBody String orderId) { 
		log.info("실행");
		log.info("orderDetail : " + orderId);
		
		return orderService.getOrderDetail(orderId);
	}
	
	@PatchMapping("/detail")
	public void updateState(@RequestBody OrderDetail orderDetail) {
		log.info("실행");
		log.info("orderDetail : " + orderDetail);
		
		orderService.updateState(orderDetail);
	}
	
	@PostMapping("/payment")
	public void insert(@RequestBody Payment payment) {
		log.info("실행");
		log.info("payment : " + payment);
		
		orderService.insert(payment);
	}
	
	@GetMapping("/payment")
	public List<Payment> payment(@RequestBody String orderId) { 
		log.info("실행");
		log.info("orderId : " + orderId);
		
		return orderService.getPayments(orderId);
	}
	
	@PatchMapping("/payment")
	public void updateState(@RequestBody Payment payment) {
		log.info("실행");
		log.info("payment : " + payment);
		
		orderService.updateState(payment);
	}
}
