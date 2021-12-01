package com.mycompany.orderAPI.dto.order;

import lombok.Data;

@Data
public class OrderDetail {
	String productDetailId;
	String psize;
	String orderId;
	int amount;
	int price;
	char state;
	String deliveryNo;
}
