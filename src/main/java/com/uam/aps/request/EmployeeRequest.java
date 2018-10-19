package com.uam.aps.request;

import java.io.Serializable;

public class EmployeeRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String responsibility;

	private Double salary;

	public EmployeeRequest(String name, String responsibility, Double salary) {
		this.name = name;
		this.responsibility = responsibility;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
