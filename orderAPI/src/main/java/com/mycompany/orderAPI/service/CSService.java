package com.mycompany.orderAPI.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mycompany.orderAPI.dao.orderDB.OrderCSDao;
import com.mycompany.orderAPI.dao.orderDB.OrderDao;
import com.mycompany.orderAPI.dao.orderDB.OrderDetailDao;
import com.mycompany.orderAPI.dto.order.Order;
import com.mycompany.orderAPI.dto.order.OrderCS;
import com.mycompany.orderAPI.dto.order.OrderDetail;

@Service
public class CSService {
	
	public enum CSResult {
		SUCCESS,
		FAIL
	}
	
	@Resource OrderDao orderDao;
	@Resource OrderCSDao orderCSDao;
	@Resource OrderDetailDao orderDetailDao;

	@Transactional
	public CSResult insertOrderCS(OrderCS orderCS) {
		orderCS.setStateCode(1);
		orderCSDao.insert(orderCS);
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId(orderCS.getOrderId());
		orderDetail.setProductDetailId(orderCS.getProductDetailId());
		orderDetail.setPsize(orderCS.getPsize());
		
		if(orderCS.getTypeCode() == 1) {
			orderDetail.setStateCode(6);
		} else if(orderCS.getTypeCode() == 2) {
			orderDetail.setStateCode(5);
		}
		orderDetailDao.updateState(orderDetail);
		
		Order order = new Order();
		order.setOrderId(orderCS.getOrderId());
		order.setStateCode(5);
		orderDao.updateState(order);
		
		return CSResult.SUCCESS;
	}

}
