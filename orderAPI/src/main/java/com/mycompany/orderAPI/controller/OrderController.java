package com.mycompany.orderAPI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/list")
	public Map<String, Object> list(@RequestBody Member member) {
		log.info("실행");
		log.info("memberId : " + member);
		
		List<Order> list = orderService.getOrders(member.getMemberId());
		Map<String, Object> map = new HashMap<>();
		map.put("orders", list);
		
		return map;
	}
}
