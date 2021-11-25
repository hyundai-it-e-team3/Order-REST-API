package com.mycompany.orderAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.order.OrderDetail;
import com.mycompany.orderAPI.dto.order.OdTimeline;

@Mapper
public interface OdTimelineDao {
	void insert(OrderDetail orderDetail);
	List<OdTimeline> select(OrderDetail orderDetail);
}
