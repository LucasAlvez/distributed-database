package com.uam.aps.request;

import java.io.Serializable;

public class ProductRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String code;
	
	private String codeAux;

	private Double price;

	private Integer quantity;
	
	private Integer operation;
	
	public ProductRequest () {
	}

	public ProductRequest(String name, String code, String codeAux, Double price, Integer quantity, Integer operation) {
		this.name = name;
		this.code = code;
		this.codeAux = codeAux;
		this.price = price;
		this.quantity = quantity;
		this.operation = operation;
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
	
	public String getCodeAux() {
		return codeAux;
	}
	
	public void setCodeAux(String codeAux) {
		this.codeAux = codeAux;
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
	
	public Integer getOperation() {
		return operation;
	}
	
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
}
