package com.irfan.payment.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irfan.payment.consumer.CustomerHttpClient;
import com.irfan.payment.consumer.ProductHttpClient;
import com.irfan.payment.dao.CartDAO;
import com.irfan.payment.dao.PaymentDao;
import com.irfan.payment.dto.Product;
import com.irfan.payment.dto.ResponseMsg;
import com.irfan.payment.entity.Cart;
import com.irfan.payment.entity.Payment;
import com.irfan.payment.service.PaymentService;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private CartDAO cartDao;

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private ProductHttpClient productHttp;

	@Autowired
	private CustomerHttpClient customerHttp;

	@Override
	public ResponseMsg createPayment(int cartNo, int custNo) {
		// TODO Auto-generated method stub
		ResponseMsg respMsg = new ResponseMsg();
		String msgFail = "failed to create payment";

		List<Cart> cartItems = cartDao.findByCartNo(cartNo);

		if (!cartItems.isEmpty()) {
			double totalPrice = getTotalPrice(cartItems);

			if (totalPrice > 0) {
				Payment payment = populatePayment(cartNo, custNo, totalPrice);

				int isCreated = paymentDao.createPayment(payment);

				if (isCreated == 1) {
					updatePaidCart(cartNo);
					updateProductQty(cartItems);
					updateCustomerBalance(payment);
					respMsg.setMsg("successfully created");
				} else {
					respMsg.setMsg(msgFail);
				}

				respMsg.setTotalPrice(totalPrice);
			} else {
				respMsg.setMsg(msgFail);
			}
		} else {
			respMsg.setMsg(msgFail);
		}

		respMsg.setCartNo(cartNo);
		respMsg.setCustNo(custNo);

		return respMsg;
	}

	@Override
	public List<Payment> getPayments() {
		return paymentDao.getPayments();
	}

	@Override
	public Payment getPayment(int id) {
		Payment payment = new Payment();

		try {
			payment = paymentDao.getPayment(id);
		} catch (Exception e) {
			LOGGER.error("no data found");
		}

		return payment;
	}

	@Override
	public ResponseMsg deletePayment(int id) {
		ResponseMsg respMsg = new ResponseMsg();

		try {
			int isDeleted = paymentDao.deletePayment(id);

			if (isDeleted > 0) {
				respMsg.setMsg("payment deleted");
			} else {
				respMsg.setMsg("payment are not deleted");
			}
		} catch (Exception e) {
			respMsg.setMsg("payment are not deleted");
		}

		return respMsg;
	}

	private void updatePaidCart(int cartNo) {
		cartDao.updatePaidCart(cartNo);
	}

	private void updateProductQty(List<Cart> cartItems) {
		for (Cart cart : cartItems) {
			Product product = new Product();
			product.setProductNo(cart.getProductNo());
			product.setQty(cart.getQty());

			productHttp.updateProduct(product);
		}
	}

	private void updateCustomerBalance(Payment payment) {
		customerHttp.updateCustomerBalance(payment);
	}

	private Payment populatePayment(int cartNo, int custNo, double totalPrice) {
		Payment payment = new Payment();
		payment.setCartNo(cartNo);
		payment.setCustNo(custNo);
		payment.setTotal_price(totalPrice);
		payment.setTrxDate(new Date());

		return payment;
	}

	private double getTotalPrice(List<Cart> cartItems) {
		double totalPrice = 0;
		double productPrice = 0;
		int productNo = 0;

		for (Cart cart : cartItems) {
			if (productNo != cart.getProductNo()) {
				productNo = cart.getProductNo();

				Product product = productHttp.getProductByProductNo(productNo);
				if (product != null) {
					productPrice = product.getPrice();
				} else {
					break;
				}
			}

			totalPrice += productPrice;
		}

		return totalPrice;
	}

}
