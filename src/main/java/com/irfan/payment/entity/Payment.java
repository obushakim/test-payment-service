package com.irfan.payment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PAYMENT")
public class Payment {
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private int id;

	@Column(name = "trx_no", nullable = false)
	private int trxNo;

	@Column(name = "cart_no", nullable = false)
	private int cartNo;

	@Column(name = "cust_no", nullable = false)
	private int custNo;

	@Column(name = "total_price", nullable = false)
	private double total_price;

	@Column(name = "trx_date", nullable = false)
	private Date trxDate;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "modified_date", nullable = false)
	private Date modifiedDate;
}
