package com.mycompany.orderAPI.dao.orderDB;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.order.Payment;

@Mapper
public interface PaymentDao {
	public List<Payment> selectByOid(String orderId);
	public void insert(Payment payment);
	public void updateState(Payment payment);
	public void updateStateByOid(Payment payment);
}
