package com.uam.aps.response;

import java.io.Serializable;

public class ClientResponse implements Serializable {
	
	private String name;
	
	private String cpf;
	
	private Integer age;
	
	private String message;
	
	public ClientResponse() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
