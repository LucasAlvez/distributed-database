package com.uam.aps.request;

import java.io.Serializable;

public class ProductRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private Double price;

	private Integer quantity;

	public ProductRequest(String name, Double price, Integer quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
