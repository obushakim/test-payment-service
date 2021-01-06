package com.irfan.payment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.irfan.payment.dao.PaymentDao;
import com.irfan.payment.entity.Payment;
import com.irfan.payment.service.PaymentService;
import com.irfan.payment.service.impl.PaymentServiceImpl;

@SpringBootTest
class PaymentServiceApplicationTests {

	@TestConfiguration
	static class PaymentServiceTestContextConfiguration {
		@Bean
		public PaymentService paymentServTest() {
			return new PaymentServiceImpl();
		}
	}

	@Autowired
	PaymentService paymentServTest;

	@MockBean
	private PaymentDao paymentDao;

	@Test
	void contextLoads() {
		Payment pay = new Payment();
		pay.setId(1);
		pay.setCustNo(100001);
		pay.setTotal_price(10000.5);

		Mockito.when(paymentDao.getPayment(pay.getId())).thenReturn(pay);

		Payment payment = paymentDao.getPayment(1);

		assertThat(payment.getCustNo()).isEqualTo(100001);
	}

}
