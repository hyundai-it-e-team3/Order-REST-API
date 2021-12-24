package com.mycompany.orderAPI.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.orderAPI.dao.memberDB.MemberCouponDao;
import com.mycompany.orderAPI.dto.member.MemberCoupon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberCouponService {
	
	public enum InsertMemberCouponResult {
		SUCCESS,
		DUPLICATE
	}
	
	@Resource
	private MemberCouponDao memberCouponDao;
	public void updateMemberCoupon(MemberCoupon memberCoupon) {
		memberCouponDao.updateMemberCoupon(memberCoupon);
	}
	
	public void refundMemberCoupon(MemberCoupon memberCoupon) {
		memberCouponDao.refundMemberCoupon(memberCoupon);
	}
	
}
