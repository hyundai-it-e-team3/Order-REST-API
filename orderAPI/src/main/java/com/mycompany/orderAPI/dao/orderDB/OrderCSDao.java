package com.mycompany.orderAPI.dao.orderDB;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.order.OrderCS;

@Mapper
public interface OrderCSDao {
	public void insert(OrderCS orderCS);
}
