package com.irfan.payment.service;

import java.util.List;

import com.irfan.payment.dto.ResponseMsg;
import com.irfan.payment.entity.Payment;

public interface PaymentService {
	public ResponseMsg createPayment(int cartNo, int custNo);

	public List<Payment> getPayments();

	public Payment getPayment(int id);

	public ResponseMsg deletePayment(int id);
}
