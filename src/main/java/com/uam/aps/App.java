package com.uam.aps;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.uam.aps.connection.Connection;
import com.uam.aps.request.ClientRequest;
import com.uam.aps.response.ClientResponse;
import com.uam.aps.utils.Builder;
import com.uam.aps.utils.Menu;

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

	public static void main(String[] args) throws IOException {
		in = new Scanner(System.in);
		System.out.println("Escolha\n Digite a opção desejada:");
		System.out.println("(1) Cliente\n" + "(2) Funcionário\n" + "(3) Empresa");
		int operation = in.nextInt();

		new App();

		switch (operation) {
		case 1:
			ClientRequest clientRequest = new ClientRequest();
			ClientResponse clientResponse = new ClientResponse();
			
			clientRequest = Menu.clientMenu();

			// Enviando requisição para o ServerController
			Connection.send(app_socket, clientRequest);

			// Recebendo resposta do ServerController
			clientResponse = (ClientResponse) Connection.receive(app_socket);
			
			// Printa a resposta
			Builder.printClient(clientResponse);
			app_socket.close();
		}
	}
}
