package com.mycompany.orderAPI.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.orderAPI.dao.orderDB.CartDao;
import com.mycompany.orderAPI.dto.member.Member;
import com.mycompany.orderAPI.dto.order.Cart;
import com.mycompany.orderAPI.dto.product.StockDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartService {
	@Resource StockService stockService;
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
	
	public Map<String, Object> updateAmount(String cartId, int amount) {
		log.info("실행");
		Cart cartInfo = getCart(cartId);
		Cart cart = new Cart();
		cart.setCartId(cartId);
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		//재고량 확인
		StockDTO stock = new StockDTO();
		stock.setProductDetailId(cartInfo.getProductDetailId());
		stock.setPsize(cartInfo.getPsize());
		stock = stockService.getStock(stock);
		log.info("stock : " + stock);
		if(stock.getAmount() == 0) {
			//매진
			cart.setAmount(0);
			result.put("result", "soldout");
		}
		if(stock.getAmount() < amount) {
			//수량 부족
			log.info("수량부족");
			cart.setAmount(stock.getAmount());
			result.put("result", "lowStock");
		} else {
			//수량 충분
			cart.setAmount(amount);
			result.put("result", "success");
		}
		log.info("cart : " + cart);
		cartDao.updateAmount(cart);
		result.put("amount", cart.getAmount());
		return result;
	}
	
	
	public Cart getCart(String cartId) {
		return cartDao.selectByCid(cartId);
	}
}
