package com.irfan.payment.dto;

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
public class Customer {
	private int id;
	private int custNo;
	private String fullName;
	private double balance;
	private String address;
}
