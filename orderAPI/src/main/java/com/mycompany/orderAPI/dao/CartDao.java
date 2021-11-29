package com.mycompany.orderAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.order.Cart;

@Mapper
public interface CartDao {
	public List<Cart> selectByMid(String memberId);
	public void insert(Cart cart);
	public void update(Cart cart);
	public void delete(String cartId);
	public Cart selectCart(Cart cart);
}
