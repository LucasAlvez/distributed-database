package com.uam.aps.utils;

import java.util.Scanner;

import com.uam.aps.request.ClientRequest;

public class Menu {
	
	@SuppressWarnings("resource")
	public static ClientRequest clientMenu() {
		Scanner in = new Scanner(System.in);
		ClientRequest clientRequest = new ClientRequest();

		System.out.println("*************** CLIENTE ******************");
		System.out.println("*************** ESCOLHA A OPERAÇÃO DESEJADA ******************");
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

}
