package com.irfan.payment.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.irfan.payment.entity.Cart;

@Repository
public interface CartDAO extends CrudRepository<Cart, Integer> {

	@Query(value = "SELECT * FROM cart WHERE cart_no = ?1 AND paid_flag < 1 ORDER BY product_no ASC", nativeQuery = true)
	public List<Cart> findByCartNo(int cartNo);

	@Transactional
	@Modifying
	@Query(value = "UPDATE cart SET paid_flag = 1 WHERE cart_no = :cartNo", nativeQuery = true)
	public void updatePaidCart(@Param("cartNo") int cartNo);

}
