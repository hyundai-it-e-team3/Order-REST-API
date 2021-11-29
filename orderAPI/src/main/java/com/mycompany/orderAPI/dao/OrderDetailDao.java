package com.mycompany.orderAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.order.OrderDetail;
import com.mycompany.orderAPI.dto.product.ProductDTO;

@Mapper
public interface OrderDetailDao {
	public List<OrderDetail> selectByOid(String orderId);
	public void insert(OrderDetail orderDetail);
	public void updateState(OrderDetail orderDetail);
}
