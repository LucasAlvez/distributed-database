package com.uam.aps.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.uam.aps.connection.Connection;
import com.uam.aps.response.ClientResponse;
import com.uam.aps.response.EmployeeResponse;
import com.uam.aps.response.ProductResponse;
import com.uam.aps.utils.Path;

public class ServerController {
	static ServerSocket server_controller;
	static Socket app_socket;
	static Socket firstServer_socket;
	static Socket secondServer_socket;
	static Socket thirdServer_socket;
	static boolean send = false;

	public ServerController() {
		try {
			// Socket que recebe a requisição da app
			server_controller = new ServerSocket(9321);
			System.out.println("ServerController no ar!!!\nAguardando app fazer requisiçao ...\"");
		} catch (Exception e) {
			System.out.println("Nao criei o server socket...");
		}
	}

	public static void main(String args[]) throws IOException {
		new ServerController();

		while (true) {
			if (connect()) {

				// Recebe requisição da app
				Object request = (Object) Connection.receive(app_socket);

				// Se o objeto recebido for um cliente, envia para o Servidor 1 e 2
				if (request.getClass().getName().equals(Path.CLIENTCLASS)) {
					sendClientServerOne(request);
					sendClientServerTwo(request);
				}

				// Se o objeto recebido for um funcionário, envia para o Servidor 2 e 3
				if (request.getClass().getName().equals(Path.EMPLOYEECLASS)) {
					sendEmployeeServerTwo(request);
					sendEmployeeServerThree(request);
				}
				
				// Se o objeto recebido for um produto, envia para o Servidor 1 e 3
				if (request.getClass().getName().equals(Path.PRODUCTCLASS)) {
					sendProductServerOne(request);
					sendProductServerThree(request);
				}
			} else {
				try {
					server_controller.close();
					break;
				} catch (Exception e) {
					System.out.println("Nao desconectei...");
				}
			}
		}
	}

	public static void sendClientServerOne(Object request) {

		try {
			// Socket que enviará a requisição do ServerController para o FirstServer
			firstServer_socket = new Socket("localhost", 9322);

			// Envia esta mesma requisição para o FirstServer
			Connection.send(firstServer_socket, request);

			// Recebe resposta do FirstServer
			Object response = (Object) Connection.receive(firstServer_socket);

			ClientResponse r = (ClientResponse) response;
			if (r.getMessage().getCode().equals("2")) {
				send = false;
				sendClientServerTwo(request);
			}

			// Envia esta resposta para a app
			Connection.send(app_socket, response);

			send = true;
		} catch (Exception e) {
			System.out.println("Primeiro servidor está fora do ar, tentando se conectar ao segundo....");
			send = false;
		}

	}

	public static void sendClientServerTwo(Object request) {
		try {
			// Socket que enviará a requisição do ServerController para o SecondServer
			secondServer_socket = new Socket("localhost", 9323);

			Connection.send(secondServer_socket, request);
			Object response = (Object) Connection.receive(secondServer_socket);
			if (!send) {
				Connection.send(app_socket, response);
			}
			send = true;
		} catch (Exception ex) {
			if (!send) {
				System.out.println("Os dois servidores estão fora do ar");
			} else {
				System.out.println("Servidor 2 fora do ar");
			}
			send = false;
		}
	}

	public static void sendEmployeeServerTwo(Object request) {

		try {
			secondServer_socket = new Socket("localhost", 9323);

			Connection.send(secondServer_socket, request);

			Object response = (Object) Connection.receive(secondServer_socket);

			EmployeeResponse r = (EmployeeResponse) response;
			if (r.getMessage().getCode().equals("2")) {
				send = false;
				sendEmployeeServerThree(request);
			}

			Connection.send(app_socket, response);

			send = true;
		} catch (Exception e) {
			System.out.println("Segundo servidor está fora do ar, tentando se conectar ao terceiro....");
			send = false;
		}

	}

	public static void sendEmployeeServerThree(Object request) {
		try {
			thirdServer_socket = new Socket("localhost", 9324);

			Connection.send(thirdServer_socket, request);
			Object response = (Object) Connection.receive(thirdServer_socket);
			if (!send) {
				Connection.send(app_socket, response);
			}
			send = true;
		} catch (Exception ex) {
			if (!send) {
				System.out.println("Os dois servidores estão fora do ar");
			} else {
				System.out.println("Servidor 3 fora do ar");
			}
			send = false;
		}
	}
	
	public static void sendProductServerOne(Object request) {

		try {
			firstServer_socket = new Socket("localhost", 9322);

			Connection.send(firstServer_socket, request);

			Object response = (Object) Connection.receive(firstServer_socket);

			ProductResponse r = (ProductResponse) response;
			if (r.getMessage().getCode().equals("2")) {
				send = false;
				sendProductServerThree(request);
			}

			Connection.send(app_socket, response);

			send = true;
		} catch (Exception e) {
			System.out.println("Primeiro servidor está fora do ar, tentando se conectar ao terceiro....");
			send = false;
		}

	}
	
	public static void sendProductServerThree(Object request) {
		try {
			thirdServer_socket = new Socket("localhost", 9324);

			Connection.send(thirdServer_socket, request);
			Object response = (Object) Connection.receive(thirdServer_socket);
			if (!send) {
				Connection.send(app_socket, response);
			}
			send = true;
		} catch (Exception ex) {
			if (!send) {
				System.out.println("Os dois servidores estão fora do ar");
			} else {
				System.out.println("Servidor 3 fora do ar");
			}
			send = false;
		}
	}

	public static boolean connect() {
		try {
			app_socket = server_controller.accept();
			return true;
		} catch (Exception e) {
			System.out.println("Erro de conexão..." + e.getMessage());
			return false;
		}
	}

}
