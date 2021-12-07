package com.mycompany.orderAPI.dto.member;

import java.util.Date;

import lombok.Data;

@Data
public class MemberCoupon {
	private String memberId;
	private String couponId;
	private Date regDate;
	private Date expDate;
	private char status;
	private String name;
	private int amount;
	private char type;
	private String content;
}