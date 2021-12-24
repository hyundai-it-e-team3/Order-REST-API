package com.mycompany.orderAPI.dao.memberDB;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.member.Point;

@Mapper
public interface PointDao {
	public List<Point> getPointList(String memberId);
	public void insertSavePoint(Point savePoint);
	public void insertUsePoint(Point usePoint);
	public void updateRefundPoint(Point refundPoint);
	public Point selectRefundPoint(Point refundPoint);
}
