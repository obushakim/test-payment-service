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
public class ResponseMsg {

	private int custNo;
	private int cartNo;
	private double totalPrice;
	private String msg;
}
