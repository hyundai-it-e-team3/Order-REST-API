package com.mycompany.orderAPI.dao.memberDB;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.member.Member;

@Mapper
public interface MemberDao {
	public void updatePointBalance(Member member);
	public int getMemberPoint(String memberId);
}