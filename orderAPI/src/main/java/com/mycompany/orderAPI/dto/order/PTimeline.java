package com.mycompany.orderAPI.dto.order;

import java.util.Date;

import lombok.Data;

@Data
public class PTimeline {
	String orderId;
	String type;
	Date issueDate;
	char state;
}
