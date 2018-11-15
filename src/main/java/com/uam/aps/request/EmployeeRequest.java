package com.uam.aps.request;

import java.io.Serializable;

public class EmployeeRequest implements Serializable {

	private String name;
	
	private String cpf;
	
	private String cpfAux;

	private String responsibility;

	private Double salary;
	
	private Integer operation;
	
	public EmployeeRequest() {
	}

	public EmployeeRequest(String name, String cpf, String cpfAux, String responsibility, Double salary, Integer operation) {
		this.name = name;
		this.cpf = cpf;
		this.cpfAux = cpfAux;
		this.responsibility = responsibility;
		this.salary = salary;
		this.operation = operation;
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
	
	public String getCpfAux() {
		return cpfAux;
	}
	
	public void setCpfAux(String cpfAux) {
		this.cpfAux = cpfAux;
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
	
	public Integer getOperation() {
		return operation;
	}
	
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
}
