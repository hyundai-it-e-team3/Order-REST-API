package com.mycompany.orderAPI.dto.order;

import java.util.Date;

import lombok.Data;

@Data
public class OTimeline {
	String orderId;
	Date issueDate;
	String state;
	int stateCode;
}
