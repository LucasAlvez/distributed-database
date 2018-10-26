package com.uam.aps.server;

import java.net.ServerSocket;
import java.net.Socket;

import com.uam.aps.connection.Connection;
import com.uam.aps.dao.ClientDAO;
import com.uam.aps.request.ClientRequest;
import com.uam.aps.response.ClientResponse;

public class Server1 {
	// Temos que ver o melhor jeito de renomear essas variaveis
	static ServerSocket serverControllerSocket;
	static Socket serverController_socket;

	public Server1() {
		try {
			
			// Recebe requisição do ServerController
			serverControllerSocket = new ServerSocket(9322);
			System.out.println("Server1 no ar!!!");
			System.out.println("Aguardando ServerController enviar a requisiçao ...");
		} catch (Exception e) {
			System.out.println("Nao criei o server socket...");
		}
	}

	public static void main(String args[]) {
		
		new Server1();
		
		while (true) {
			if (connect()) {
				ClientRequest clientRequest = new ClientRequest();
				// Recebe requisição do ServerController
				clientRequest = (ClientRequest) Connection.receive(serverController_socket);
				
				// Monta resposta
				ClientResponse clientResponse = new ClientResponse();
				clientResponse.setName(clientRequest.getName());
				clientResponse.setCpf(clientRequest.getCpf());
				clientResponse.setAge(clientRequest.getAge());
				clientResponse.setMessage("OK");
				
				// Cria cliente no arquivo json
				ClientDAO.create(clientRequest);
				
				// Envia resposta para o ServerController
				Connection.send(serverController_socket, clientResponse);
			} else {
				try {
					serverControllerSocket.close();
					break;
				} catch (Exception e) {
					System.out.println("Nao desconectei...");
				}
			}
		}
	}

	static boolean connect() {
		try {
			serverController_socket = serverControllerSocket.accept();
			return true;
		} catch (Exception e) {
			System.out.println("Erro de conexão..." + e.getMessage());
			return false;
		}
	}

}
