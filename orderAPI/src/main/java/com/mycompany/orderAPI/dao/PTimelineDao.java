package com.mycompany.orderAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.order.PTimeline;
import com.mycompany.orderAPI.dto.order.Payment;

@Mapper
public interface PTimelineDao {
	void insert(Payment payment);
	List<PTimeline> select(Payment payment);
}
