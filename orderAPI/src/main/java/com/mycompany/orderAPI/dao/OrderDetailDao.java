package com.mycompany.orderAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.order.OrderDetail;

@Mapper
public interface OrderDetailDao {
	public List<OrderDetail> selectByOid(String orderId);
}
