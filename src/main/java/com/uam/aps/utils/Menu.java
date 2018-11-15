package com.uam.aps.utils;

import java.util.Scanner;

import com.uam.aps.request.ClientRequest;
import com.uam.aps.request.EmployeeRequest;
import com.uam.aps.request.ProductRequest;

public class Menu {
	
	public static ClientRequest clientMenu() {
		Scanner in = new Scanner(System.in);
		ClientRequest clientRequest = new ClientRequest();

		System.out.println("*************** CLIENTE ******************");
		System.out.println("(1) Cadastrar Cliente\n" + "(2) Atualizar Cliente\n" + "(3) Buscar Cliente\n"
				+ "(4) Deletar Cliente\n");
		System.out.println("Digite a operação desejada:");
		clientRequest.setOperation(in.nextInt());

		if (clientRequest.getOperation() == 1 || clientRequest.getOperation() == 2) {

			if (clientRequest.getOperation() == 2) {
				System.out.println("Digite o cpf do cliente que deseja atualizar");
				clientRequest.setCpfAux(in.next());
			}

			System.out.println("Digite o nome:");
			clientRequest.setName(in.next());
			System.out.println("Digite o cpf:");
			clientRequest.setCpf(in.next());
			System.out.println("Digite a idade:");
			clientRequest.setAge(in.nextInt());
		} else if (clientRequest.getOperation() == 3) {
			System.out.println("Digite o cpf do cliente que deseja buscar");
			clientRequest.setCpf(in.next());
		} else  if (clientRequest.getOperation() == 4){
			System.out.println("Digite o cpf do cliente que deseja excluir");
			clientRequest.setCpf(in.next());
		} else {
			System.out.println("Operação invalida");
		}
		return clientRequest;
	}
	
	public static EmployeeRequest employeeMenu() {
		Scanner in = new Scanner(System.in);
		EmployeeRequest employeeRequest = new EmployeeRequest();

		System.out.println("*************** FUNCIONÁRIO ******************");
		System.out.println("(1) Cadastrar Funcinário\n" + "(2) Atualizar Funcionário\n" + "(3) Buscar Funcionário\n"
				+ "(4) Deletar Funcionário\n");
		System.out.println("Digite a operação desejada:");
		employeeRequest.setOperation(in.nextInt());

		if (employeeRequest.getOperation() == 1 || employeeRequest.getOperation() == 2) {

			if (employeeRequest.getOperation() == 2) {
				System.out.println("Digite o cpf do funcionário que deseja atualizar");
				employeeRequest.setCpfAux(in.next());
			}

			System.out.println("Digite o nome:");
			employeeRequest.setName(in.next());
			System.out.println("Digite o cpf:");
			employeeRequest.setCpf(in.next());
			System.out.println("Digite o cargo:");
			employeeRequest.setResponsibility(in.next());
			System.out.println("Digite o salario:");
			employeeRequest.setSalary(in.nextDouble());
		} else if (employeeRequest.getOperation() == 3) {
			System.out.println("Digite o cpf do funcionário que deseja buscar");
			employeeRequest.setCpf(in.next());
		} else  if (employeeRequest.getOperation() == 4){
			System.out.println("Digite o cpf do funcionário que deseja excluir");
			employeeRequest.setCpf(in.next());
		} else {
			System.out.println("Operação invalida");
		}
		return employeeRequest;
	}
	
	public static ProductRequest productMenu() {
		Scanner in = new Scanner(System.in);
		ProductRequest productRequest = new ProductRequest();

		System.out.println("*************** PRODUTO ******************");
		System.out.println("(1) Cadastrar Produto\n" + "(2) Atualizar Produto\n" + "(3) Buscar Produto\n"
				+ "(4) Deletar Produto\n");
		System.out.println("Digite a operação desejada:");
		productRequest.setOperation(in.nextInt());

		if (productRequest.getOperation() == 1 || productRequest.getOperation() == 2) {

			if (productRequest.getOperation() == 2) {
				System.out.println("Digite o código do produto que deseja atualizar");
				productRequest.setCodeAux(in.next());
			}

			System.out.println("Digite o nome:");
			productRequest.setName(in.next());
			System.out.println("Digite o código do produto:");
			productRequest.setCode(in.next());
			System.out.println("Digite o preço:");
			productRequest.setPrice(in.nextDouble());
			System.out.println("Digite a quantidade:");
			productRequest.setQuantity(in.nextInt());
		} else if (productRequest.getOperation() == 3) {
			System.out.println("Digite o código do produto que deseja buscar");
			productRequest.setCode(in.next());
		} else  if (productRequest.getOperation() == 4){
			System.out.println("Digite o código do produto que deseja excluir");
			productRequest.setCode(in.next());
		} else {
			System.out.println("Operação invalida");
		}
		return productRequest;
	}

}
