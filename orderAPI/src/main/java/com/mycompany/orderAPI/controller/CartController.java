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
@RequestMapping("/order")
@Slf4j
public class CartController {
	@Resource CartService cartService;
	
	@GetMapping
	public List<Cart> getCarts(@RequestBody Member member) {
		return cartService.getCarts(member);
	}
	
	@PostMapping
	public void insert(@RequestBody Cart cart) {
		cartService.insert(cart);
	}
	
	@PatchMapping
	public void update(@RequestBody Cart cart) {
		cartService.update(cart);
	}
	
	@DeleteMapping
	public void delete(@RequestBody Cart cart) {
		cartService.delete(cart);
	}
	
	

}
