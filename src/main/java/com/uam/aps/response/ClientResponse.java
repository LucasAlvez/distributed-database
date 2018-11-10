package com.uam.aps.response;

import java.io.Serializable;

import com.uam.aps.status.StatusEnum;

public class ClientResponse implements Serializable {

	private String name;

	private String cpf;

	private Integer age;

	private StatusEnum message;

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

	public StatusEnum getMessage() {
		return message;
	}
	
	public void setMessage(StatusEnum message) {
		this.message = message;
	}
}
