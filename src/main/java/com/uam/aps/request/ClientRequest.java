package com.uam.aps.request;

import java.io.Serializable;

public class ClientRequest implements Serializable {
	
	private String name;
	
	private String cpf;
	
	private Integer age;
	
	public ClientRequest() {
	}
	
	public ClientRequest (String name, String cpf, Integer age) {
		this.name = name;
		this.cpf = cpf;
		this.age = age;
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

	public void setAge(Integer idade) {
		this.age = idade;
	}
}
