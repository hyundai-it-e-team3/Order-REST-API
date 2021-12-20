package com.mycompany.orderAPI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.orderAPI.dto.order.Order;
import com.mycompany.orderAPI.dto.order.OrderDetail;
import com.mycompany.orderAPI.dto.order.Payment;
import com.mycompany.orderAPI.service.OrderService;
import com.mycompany.orderAPI.service.OrderService.OrderResult;
import com.mycompany.orderAPI.service.PointService;
import com.mycompany.orderAPI.service.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController{
	@Resource
	private OrderService orderService;
	
	@Resource
	private PointService pointService;
	
	@Resource
	private StockService stockService;
	
	
	@PostMapping
	public String insertOrderTotal(@RequestBody Order order) {
		log.info("실행");
		log.info("order : " + order);
		
		orderService.insertOrder(order);
		
		return "SUCCESS";
	}
	
	@GetMapping("/list")
	public List<Order> list(@RequestParam String memberId) {
		log.info("실행");
		log.info("member : " + memberId);
		
		List<Order> list = orderService.getOrders(memberId); 
		return list;
	}
	
	
	@GetMapping("/{orderId}") 
	public Map<String, Object> getOrderInfo(
			@PathVariable String orderId) {
		log.info("실행");
		log.info("orderId : " + orderId);

		return orderService.getOrderInfo(orderId);
	}
	
	@GetMapping("/{orderId}/list") 
	public List<OrderDetail> getOrderProductList(
			@PathVariable String orderId) {
		log.info("실행");
		log.info("orderId : " + orderId);

		return orderService.getOrderDetail(orderId);
	}
	
	
	@PatchMapping("/state")
	public void updateState(@RequestBody Order order) {
		log.info("실행");
		log.info("order : " + order);
		
		orderService.updateState(order);
	}
	
	@PostMapping("/detail")
	public void insert(OrderDetail orderDetail) {
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
	public void insert(Payment payment) {
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
	
	@PatchMapping("/address")
	public Map<String, Object> updateAddress(@RequestBody Order order) {
		log.info("실행");
		log.info("배송지 수정 : " + order);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		OrderResult or = orderService.updateAddress(order);
		if(or == OrderResult.SUCCESS) resultMap.put("result", "success");
		else resultMap.put("result", "fail");
		
		return resultMap;
	}
}
