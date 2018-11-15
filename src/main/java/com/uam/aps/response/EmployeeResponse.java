package com.uam.aps.response;

import java.io.Serializable;

import com.uam.aps.status.StatusEnum;

public class EmployeeResponse implements Serializable {

	private String name;
	
	private String cpf;

	private String responsibility;

	private Double salary;

	private StatusEnum message;

	public EmployeeResponse() {

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

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public StatusEnum getMessage() {
		return message;
	}

	public void setMessage(StatusEnum message) {
		this.message = message;
	}
}
