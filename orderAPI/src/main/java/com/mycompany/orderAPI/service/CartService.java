package com.mycompany.orderAPI.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.orderAPI.dao.CartDao;
import com.mycompany.orderAPI.dto.member.Member;
import com.mycompany.orderAPI.dto.order.Cart;

@Service
public class CartService {
	@Resource CartDao cartDao;
	
	public enum CartResult {
		SUCCESS,
		SUCCESS_DUPLICATE,
		FAIL_DUPLICATE
	}
	
	public List<Cart> getCarts(Member member) {
		return cartDao.selectByMid(member.getMemberId());
	}
	
	public CartResult insert(Cart cart) {
		Cart dbCart = cartDao.selectCart(cart);
		if(dbCart == null) {
			cartDao.insert(cart);
			return CartResult.SUCCESS;
		} else {
			cart.setAmount(cart.getAmount()+dbCart.getAmount());
			cartDao.update(cart);
			return CartResult.SUCCESS_DUPLICATE;
		}
	}
	
	public void delete(String cartId) {
		cartDao.delete(cartId);
	}
	
	public CartResult update(Cart cart) {
		Cart dbCart = cartDao.selectCart(cart);
		if(dbCart == null) {
			cartDao.update(cart);
			return CartResult.SUCCESS;
		} else {
			dbCart.setAmount(cart.getAmount()+dbCart.getAmount());
			cartDao.update(dbCart);
			cartDao.delete(cart.getCartId());
			return CartResult.SUCCESS_DUPLICATE;
		}
	}
	public Cart getCart(String cartId) {
		return cartDao.selectByCid(cartId);
	}
}
