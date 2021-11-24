package com.mycompany.orderAPI.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.orderAPI.dao.OrderDao;
import com.mycompany.orderAPI.dto.order.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	@Resource
	private OrderDao orderDao;
	
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

}
