package com.mycompany.orderAPI.dto.member;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private String memberId;
	private String password;
	private String name;
	private String nickname;
	private String email;
	private String tel;
	private String birthday;
	private char memberLevel;
	private String oneclickpayPassword;
	private String kakaoNo;
	private String memberRole;
	private char status;
	private Date lastLoginDate;
	private int point;
}