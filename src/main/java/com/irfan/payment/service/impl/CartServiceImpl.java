package com.irfan.payment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irfan.payment.dao.CartDAO;
import com.irfan.payment.entity.Cart;
import com.irfan.payment.service.CartService;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDao;

	@Override
	public List<Cart> getCartItems(int cartNo) {
		// TODO Auto-generated method stub
		List<Cart> cartItems = cartDao.findByCartNo(cartNo);

		return cartItems;
	}

}
