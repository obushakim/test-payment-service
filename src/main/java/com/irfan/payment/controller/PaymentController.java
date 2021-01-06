package com.irfan.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.irfan.payment.dto.ResponseMsg;
import com.irfan.payment.entity.Payment;
import com.irfan.payment.service.PaymentService;

import io.swagger.annotations.ApiOperation;

@RestController
public class PaymentController {

	@Autowired
	@Qualifier("paymentService")
	private PaymentService paymentService;

	@GetMapping("/")
	public String index() {

		return "Payment API";
	}

	@ApiOperation(value = "Create Payment Interface")
	@PostMapping("/payments")
	public ResponseMsg createPayment(@RequestBody Payment request) {
		ResponseMsg respMsg = paymentService.createPayment(request.getCartNo(), request.getCustNo());

		return respMsg;
	}

	@GetMapping("/payments/{id}")
	public Payment getPayment(@PathVariable int id) {
		Payment payment = paymentService.getPayment(id);

		return payment;
	}

	@GetMapping("/payments")
	public List<Payment> getPayments() {
		List<Payment> payments = paymentService.getPayments();

		return payments;
	}

	@DeleteMapping("/payments/{id}")
	public ResponseMsg deletePayment(@PathVariable int id) {
		ResponseMsg respMsg = paymentService.deletePayment(id);

		return respMsg;
	}
}
