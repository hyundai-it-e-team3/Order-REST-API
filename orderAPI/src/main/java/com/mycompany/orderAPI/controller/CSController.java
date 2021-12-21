package com.mycompany.orderAPI.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.orderAPI.dto.order.OrderCS;
import com.mycompany.orderAPI.service.CSService;
import com.mycompany.orderAPI.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cs")
@Slf4j
public class CSController {
	@Resource
	private CSService csService;
	
	@Resource
	private OrderService orderService;
	
	@PostMapping
	public Map<String, Object> insertOrderCS(@RequestBody OrderCS orderCS) {
		log.info("실행");
		log.info("CS 등록 : " + orderCS);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		csService.insertOrderCS(orderCS);
		
		resultMap.put("result", "success");
		return resultMap;
	}
	
	
}
