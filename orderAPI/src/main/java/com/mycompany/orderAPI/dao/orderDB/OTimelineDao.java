package com.mycompany.orderAPI.dao.orderDB;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.order.OTimeline;
import com.mycompany.orderAPI.dto.order.Order;

@Mapper
public interface OTimelineDao {
	void insert(Order order);
	List<OTimeline> select(Order order);
}
