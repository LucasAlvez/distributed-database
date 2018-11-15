package com.uam.aps.response;

import java.io.Serializable;

import com.uam.aps.status.StatusEnum;

public class ProductResponse implements Serializable {
	
	private String name;
	
	private String code;

	private Double price;

	private Integer quantity;
	
	private StatusEnum message;

	public ProductResponse () {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public StatusEnum getMessage() {
		return message;
	}
	
	public void setMessage(StatusEnum message) {
		this.message = message;
	}
}
