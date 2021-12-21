package com.mycompany.orderAPI.dao.memberDB;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.member.MemberCoupon;

@Mapper
public interface MemberCouponDao {
	public void updateMemberCoupon(MemberCoupon memberCoupon);
}