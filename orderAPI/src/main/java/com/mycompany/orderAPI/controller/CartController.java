package com.mycompany.orderAPI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.orderAPI.dto.member.Member;
import com.mycompany.orderAPI.dto.order.Cart;
import com.mycompany.orderAPI.service.CartService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {
	@Resource CartService cartService;
	
	@GetMapping("/{cartId}")
	public Cart getCart(@PathVariable String cartId) {
		return cartService.getCart(cartId);
	}
	
	@GetMapping
	public List<Cart> getCarts(@RequestParam String memberId) {
		log.info("실행");
		Member member = new Member();
		member.setMemberId(memberId);
		List<Cart> cartList = cartService.getCarts(member);
		System.out.println(cartList);
		return cartList;
	}
	
	@PostMapping
	public void insert(Cart cart) {
		log.info("실행");
		log.info(cart.toString());
		cartService.insert(cart);
	}
	
	@PatchMapping("/{cartId}")
	public void update(
			@PathVariable String cartId,
			@RequestBody Cart cart) {
		log.info("실행");
		cart.setCartId(cartId);
		cartService.update(cart);
	}
	
	@DeleteMapping("/{cartId}")
	public Map<String, String> delete(@PathVariable String cartId) {
		log.info("실행");
		cartService.delete(cartId);
		Map<String,String> map = new HashMap<>();
		map.put("result", "success");
		return map;
	}
	
	

}
