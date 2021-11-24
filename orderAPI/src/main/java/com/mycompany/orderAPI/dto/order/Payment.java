package com.mycompany.orderAPI.dto.order;

import lombok.Data;

@Data
public class Payment{
	char type;
	int price;
	String accountNo;
	String bank;
	int installment;
	String orderId;
}
