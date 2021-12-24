package com.mycompany.orderAPI.dao.memberDB;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.member.MemberCoupon;

@Mapper
public interface MemberCouponDao {
	public List<MemberCoupon> getMemberCoupon(String memberId);
	public void insertMemberCoupon(MemberCoupon memberCoupon);
	public void updateMemberCoupon(MemberCoupon memberCoupon);
	public MemberCoupon getDuplicateCoupon(MemberCoupon memberCoupon);
	public void refundMemberCoupon(MemberCoupon memberCoupon);
}