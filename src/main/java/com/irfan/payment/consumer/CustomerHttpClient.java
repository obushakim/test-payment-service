package com.irfan.payment.consumer;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.irfan.payment.entity.Payment;

@Component
public class CustomerHttpClient {
	@Autowired
	private RestTemplate restTemplate;

	public int updateCustomerBalance(Payment payment) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Payment> entity = new HttpEntity<Payment>(payment, headers);

		int isUpdated = restTemplate.exchange("http://localhost:8082/customers", HttpMethod.PUT, entity, Integer.class)
				.getBody();

		return isUpdated;
	}
}
