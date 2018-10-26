package com.uam.aps.server;

import java.net.ServerSocket;
import java.net.Socket;

import com.uam.aps.connection.Connection;
import com.uam.aps.request.ClientRequest;
import com.uam.aps.response.ClientResponse;

public class ServerController {

	// Temos que ver o melhor jeito de renomear essas variaveis
	static ServerSocket serverSocket;
	static Socket app_socket;
	static Socket serverController_socket;

	public ServerController() {
		try {
			
			// Socket que enviará a requisição do ServerController para o server1
			serverController_socket = new Socket("localhost", 9322);
			
			// Socket que recebe a requisição da app
			serverSocket = new ServerSocket(9321);
			
			System.out.println("ServerController no ar!!!");
			System.out.println("Aguardando app fazer requisiçao ...");
		} catch (Exception e) {
			System.out.println("Nao criei o server socket...");
		}
	}

	public static void main(String args[]) {

		new ServerController();

		while (true) {
			if (connect()) {
				ClientRequest clientRequest = new ClientRequest();
				
				// Recebe requisição da app
				clientRequest = (ClientRequest) Connection.receive(app_socket);
				
				// Envia esta mesma requisição para o server1
				Connection.send(serverController_socket, clientRequest);
				
				// Recebe resposta do server1
				ClientResponse clientResponse = (ClientResponse) Connection.receive(serverController_socket);
				
				// Envia esta resposta para a app
				Connection.send(app_socket, clientResponse);
				
				try {
		            serverController_socket.close();
		        } catch (Exception e) {
		            System.out.println("problemas ao fechar socket");
		        }
				
			} else {
				try {
					serverSocket.close();
					break;
				} catch (Exception e) {
					System.out.println("Nao desconectei...");
				}
			}
		}
	}

	static boolean connect() {
		try {
			app_socket = serverSocket.accept();
			return true;
		} catch (Exception e) {
			System.out.println("Erro de conexão..." + e.getMessage());
			return false;
		}
	}

}
