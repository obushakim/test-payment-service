package com.irfan.payment.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.irfan.payment.entity.Payment;

@Repository
public class PaymentDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public int createPayment(Payment payment) {
		int isCreated = entityManager
				.createNativeQuery("INSERT INTO payment (trx_date, cart_no, cust_no, total_price) VALUES (?,?,?,?)")
				.setParameter(1, payment.getTrxDate()).setParameter(2, payment.getCartNo())
				.setParameter(3, payment.getCustNo()).setParameter(4, payment.getTotal_price()).executeUpdate();

		return isCreated;
	}

	public List<Payment> getPayments() {
		List<Payment> payments = entityManager.createNativeQuery("SELECT * FROM payment").getResultList();

		return payments;
	}

	public Payment getPayment(int id) {
		Payment payment = (Payment) entityManager.createNativeQuery("SELECT * FROM payment WHERE id = ?", Payment.class)
				.setParameter(1, id).getSingleResult();

		return payment;
	}

	@Transactional
	public int deletePayment(int id) {
		int isDeleted = entityManager.createNativeQuery("DELETE FROM payment WHERE id = :id").setParameter("id", id)
				.executeUpdate();

		return isDeleted;
	}
}
