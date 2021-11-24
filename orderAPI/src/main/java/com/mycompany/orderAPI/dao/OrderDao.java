package com.mycompany.orderAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.order.Order;

@Mapper
public interface OrderDao {
	public List<Order> selectByMid(String memberId);
	public void insert(Order order);
	public void updateState(Order order);
	
	public Order selectByOid(String orderId);
	public Order selectByOidMid(String orderId, String memberId);
}
