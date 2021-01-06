package com.irfan.payment.service;

import java.util.List;

import com.irfan.payment.entity.Cart;

public interface CartService {
	public List<Cart> getCartItems(int cartNo);
}
