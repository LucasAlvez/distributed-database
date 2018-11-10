package com.uam.aps.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.uam.aps.connection.Connection;
import com.uam.aps.utils.Path;

public class ServerController {
	static ServerSocket server_controller;
	static Socket app_socket;
	static Socket firstServer_socket;
	static Socket secondServer_socket;

	public ServerController() {
		try {
			// Socket que enviará a requisição do ServerController para o server1
			firstServer_socket = new Socket("localhost", 9322);

			// Socket que enviará a requisição do ServerController para o server2
			secondServer_socket = new Socket("localhost", 9323);

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

				// Se o objeto recebido for um cliente, envia para o server1 e server2
				if (request.getClass().getName().equals(Path.CLIENTCLASS)) {
					sendOneAndTwo(request);
				}
				System.out.println("Estou no ServerController");

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

	// Envia client para o primeiro e segundo servidor
	public static void sendOneAndTwo(Object request) throws SocketException {

		if (firstServer_socket.getKeepAlive()) {
			// Envia esta mesma requisição para o server1
			Connection.send(firstServer_socket, request);

			// Recebe resposta do server1
			Object response = (Object) Connection.receive(firstServer_socket);

			// Envia esta resposta para a app
			Connection.send(app_socket, response);
		} else {
			Connection.send(secondServer_socket, request);
			Object response = (Object) Connection.receive(secondServer_socket);
			Connection.send(app_socket, response);
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
