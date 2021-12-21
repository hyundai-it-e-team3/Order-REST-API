package com.mycompany.orderAPI.dto.order;

import java.util.Date;

import lombok.Data;

@Data
public class OrderCS {
	int typeCode;
	int stateCode;
	String type;
	String state;
	Date issueDate;
	String content;
	String orderId;
	String productDetailId;
	String psize;
	String newProductDetailId;
	String newPsize;
}
