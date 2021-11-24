package com.mycompany.orderAPI.dto.order;

import lombok.Data;

@Data
public class OrderDetail {
	String productDetailId;
	String productName;
	String brandName;
	String psize;
	String orderId;
	int amount;
	int price;
	char deliveryState;
	String deliveryNo;
}
