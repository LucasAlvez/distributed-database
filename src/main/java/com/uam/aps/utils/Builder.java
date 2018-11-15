package com.uam.aps.utils;

import com.uam.aps.request.ClientRequest;
import com.uam.aps.request.EmployeeRequest;
import com.uam.aps.request.ProductRequest;
import com.uam.aps.response.ClientResponse;
import com.uam.aps.response.EmployeeResponse;
import com.uam.aps.response.ProductResponse;
import com.uam.aps.status.StatusEnum;

public class Builder {

	public static ClientResponse clientBuild(ClientRequest request, StatusEnum status) {
		ClientResponse response = new ClientResponse();
		response.setName(request.getName());
		response.setCpf(request.getCpf());
		response.setAge(request.getAge());
		response.setMessage(status);
		return response;
	}
	
	public static ClientResponse clientBuildError (ClientRequest request, StatusEnum status) {
		ClientResponse response = new ClientResponse();
		response.setName("");
		response.setCpf("");
		response.setAge(0);
		response.setMessage(status);
		return response;
	}

	public static void printClient(ClientResponse clientResponse) {
		System.out.println(clientResponse.getMessage().getName());

		if(clientResponse.getMessage().getCode().equals("0")) {
		System.out.println("Cliente:");
		System.out.println("Nome: " + clientResponse.getName() 
						+ "\nCpf: " + clientResponse.getCpf() 
						+ "\nIdade: " + clientResponse.getAge());
		}
		
	}
	
	public static EmployeeResponse employeeBuild(EmployeeRequest request, StatusEnum status) {
		EmployeeResponse response = new EmployeeResponse();
		response.setName(request.getName());
		response.setCpf(request.getCpf());
		response.setResponsibility(request.getResponsibility());
		response.setSalary(request.getSalary());
		response.setMessage(status);
		return response;
	}
	
	public static EmployeeResponse employeeBuildError (EmployeeRequest request, StatusEnum status) {
		EmployeeResponse response = new EmployeeResponse();
		response.setName("");
		response.setCpf("");
		response.setResponsibility("");
		response.setSalary(0.0);
		response.setMessage(status);
		return response;
	}
	
	public static void printEmployee (EmployeeResponse employeeResponse) {
		System.out.println(employeeResponse.getMessage().getName());

		if(employeeResponse.getMessage().getCode().equals("0")) {
		System.out.println("Funcionário:");
		System.out.println("Nome: " + employeeResponse.getName() 
						+ "\nCpf: " + employeeResponse.getCpf() 
						+ "\nCargo: " + employeeResponse.getResponsibility()
						+ "\nSalario: " + employeeResponse.getSalary());
		}
		
	}
	
	public static ProductResponse productBuild(ProductRequest request, StatusEnum status) {
		ProductResponse response = new ProductResponse();
		response.setName(request.getName());
		response.setCode(request.getCode());
		response.setPrice(request.getPrice());
		response.setQuantity(request.getQuantity());
		response.setMessage(status);
		return response;
	}
	
	public static ProductResponse productBuildError (ProductRequest request, StatusEnum status) {
		ProductResponse response = new ProductResponse();
		response.setName("");
		response.setCode("");
		response.setPrice(0.0);
		response.setQuantity(0);
		response.setMessage(status);
		return response;
	}
	
	public static void printProduct (ProductResponse productResponse) {
		System.out.println(productResponse.getMessage().getName());

		if(productResponse.getMessage().getCode().equals("0")) {
		System.out.println("Produto:");
		System.out.println("Nome: " + productResponse.getName() 
						+ "\nCódigo: " + productResponse.getCode() 
						+ "\nPreço: " + productResponse.getPrice()
						+ "\nQuantidade: " + productResponse.getQuantity());
		}
	}
}
