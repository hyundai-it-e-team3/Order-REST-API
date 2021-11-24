package com.mycompany.orderAPI.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.orderAPI.dto.member.Member;
import com.mycompany.orderAPI.dto.order.Order;
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
}
