package com.mycompany.orderAPI.dao.memberDB;

import java.awt.Point;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointDao {
	public List<Point> getPointList(String memberId);
	public void insertSavePoint(Point savePoint);
	public void insertUsePoint(Point usePoint);
}
