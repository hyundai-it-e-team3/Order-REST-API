package com.mycompany.orderAPI.dto.order;

import java.util.Date;

import lombok.Data;

@Data
public class OdTimeline {
	String psize;
	String productDetailId;
	String orderId;
	Date issueDate;
	String state;
	int stateCode;
}
