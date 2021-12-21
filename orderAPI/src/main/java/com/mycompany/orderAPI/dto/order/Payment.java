package com.mycompany.orderAPI.dto.order;

import lombok.Data;

@Data
public class Payment{
	String type;
	int typeCode;
	int price;
	String accountNo;
	String bank;
	int installment;
	String orderId;
	String state;
	int stateCode;
}
