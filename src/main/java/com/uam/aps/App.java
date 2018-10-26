package com.uam.aps;

import java.net.Socket;
import java.util.Scanner;

import com.uam.aps.connection.Connection;
import com.uam.aps.request.ClientRequest;
import com.uam.aps.response.ClientResponse;

public class App {
	static Socket app_socket;
	static Scanner in;

	public App() {
		try {
			app_socket = new Socket("localhost", 9321);
		} catch (Exception e) {
			System.out.println("Nao consegui resolver o host...");
		}
	}

	public static void main(String[] args) {

		new App();
		in = new Scanner(System.in);

		System.out.println("*************** CADASTRAR CLIENTE ******************");
		ClientRequest clientRequest = new ClientRequest();
		System.out.println("Digite o nome:");
		clientRequest.setName(in.next());
		System.out.println("Digite o cpf:");
		clientRequest.setCpf(in.next());
		System.out.println("Digite a idade:");
		clientRequest.setAge(in.nextInt());

		// Enviando requisição para o ServerController
		Connection.send(app_socket, clientRequest);

		// Recebendo resposta do ServerController
		ClientResponse clientResponse = (ClientResponse) Connection.receive(app_socket);

		/*
		 * Status provisório - temos que implementar os Status ainda e Também temos que
		 * retornar o objeto
		 */
		if (clientResponse.getMessage().equals("OK")) {
			System.out.println("Resultado = " + clientResponse.getMessage());
		} else if (clientResponse.getMessage().equals("")) {
			System.out.println("Erro!");
		}

		try {
			app_socket.close();
		} catch (Exception e) {
			System.out.println("problemas ao fechar socket");
		}
	}
}
