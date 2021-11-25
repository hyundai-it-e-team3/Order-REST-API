package com.mycompany.orderAPI.dto.order;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Order {
	String orderId;
	String memberId;
	Date orderDate;
	int totalPrice;
	int discountPrice;
	char state;
	String request;
	int zipCode;
	String address1;
	String address2;
	String tel;
	String recName;
	String couponId;
	List<OrderDetail> orderDetailList;
	List<Payment> paymentList;
}
