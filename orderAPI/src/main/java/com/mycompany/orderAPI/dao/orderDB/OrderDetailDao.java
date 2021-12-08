package com.mycompany.orderAPI.dao.orderDB;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.order.OrderDetail;

@Mapper
public interface OrderDetailDao {
	public List<OrderDetail> selectByOid(String orderId);
	public void insert(OrderDetail orderDetail);
	public void updateState(OrderDetail orderDetail);
}
