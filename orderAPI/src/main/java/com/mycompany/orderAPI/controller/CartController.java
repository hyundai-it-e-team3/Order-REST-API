package com.mycompany.orderAPI.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping
	public List<Cart> getCarts() {
		log.info("실행");
		Member member = new Member();
		member.setMemberId("user1");
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
	
	@PatchMapping
	public void update(@RequestBody Cart cart) {
		log.info("실행");
		cartService.update(cart);
	}
	
	@DeleteMapping
	public void delete(@RequestBody Cart cart) {
		log.info("실행");
		cartService.delete(cart);
	}
	
	

}
