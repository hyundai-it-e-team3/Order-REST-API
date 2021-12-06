package com.mycompany.orderAPI.dto.order;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Order {
	String orderId;
	String memberId;
	Date orderDate;
	char state;
	String request;
	int zipCode;
	String address1;
	String address2;
	String tel;
	String name;
	String couponId;
	List<OrderDetail> orderDetailList;
	List<Payment> paymentList;
}
