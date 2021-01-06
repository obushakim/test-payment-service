package com.irfan.payment.consumer;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.irfan.payment.dto.Product;

@Component
public class ProductHttpClient {
	@Autowired
	private RestTemplate restTemplate;

	public Product getProductByProductNo(int productNo) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Product product = restTemplate.getForObject("http://localhost:8081/products?productNo=" + productNo,
				Product.class);

		return product;
	}

	public int updateProduct(Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

		int isUpdated = restTemplate.exchange("http://localhost:8081/products", HttpMethod.PUT, entity, Integer.class)
				.getBody();

		return isUpdated;
	}
}
