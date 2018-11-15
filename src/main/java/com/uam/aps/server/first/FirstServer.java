package com.uam.aps.server.first;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.uam.aps.connection.Connection;
import com.uam.aps.dao.ClientDAO;
import com.uam.aps.dao.ProductDAO;
import com.uam.aps.request.ClientRequest;
import com.uam.aps.request.ProductRequest;
import com.uam.aps.utils.Path;

public class FirstServer {
	static ServerSocket firstServer_socket;
	static Socket server_controller;

	public FirstServer() {
		try {
			firstServer_socket = new ServerSocket(9322);
			System.out.println("Servidor 1 no ar!!!\nAguardando ServerController enviar a requisiçao ...");
		} catch (Exception e) {
			System.out.println("Nao criei o server socket...");
		}
	}

	public static void main(String args[]) throws IOException {
		new FirstServer();

		while (true) {
			if (connect()) {
				// Recebe requisição do ServerController
				Object request = (Object) Connection.receive(server_controller);

				if (request.getClass().getName().equals(Path.CLIENTCLASS)) {
					ClientRequest clientRequest = new ClientRequest();
					clientRequest = (ClientRequest) request;
					clientCRUD(clientRequest);
				}

				if (request.getClass().getName().equals(Path.PRODUCTCLASS)) {
					ProductRequest productRequest = new ProductRequest();
					productRequest = (ProductRequest) request;
					productCRUD(productRequest);
				}

			} else {
				try {
					firstServer_socket.close();
					break;
				} catch (Exception e) {
					System.out.println("Nao desconectei...");
				}
			}
		}
	}

	// Realiza o CRUD do cliente e envia resposta para o ServerController
	public static void clientCRUD(ClientRequest clientRequest) throws IOException {
		// realiza operação no arquivo json e envia resposta para o ServerController
		if (clientRequest.getOperation() == 1) {
			clientRequest.setCpfAux(clientRequest.getCpf());
			Connection.send(server_controller, ClientDAO.create(clientRequest, Path.FIRST_SERVER_CLIENT));
		} else if (clientRequest.getOperation() == 2) {
			Connection.send(server_controller, ClientDAO.update(clientRequest, Path.FIRST_SERVER_CLIENT));
		} else if (clientRequest.getOperation() == 3) {
			Connection.send(server_controller, ClientDAO.read(clientRequest, Path.FIRST_SERVER_CLIENT));
		} else if (clientRequest.getOperation() == 4) {
			Connection.send(server_controller, ClientDAO.delete(clientRequest, Path.FIRST_SERVER_CLIENT));
		} else {
			System.out.println("Erro no CRUD");
		}
	}

	public static void productCRUD(ProductRequest productRequest) throws IOException {
		if (productRequest.getOperation() == 1) {
			productRequest.setCodeAux(productRequest.getCode());
			Connection.send(server_controller, ProductDAO.create(productRequest, Path.FIRST_SERVER_PRODUCT));
		} else if (productRequest.getOperation() == 2) {
			Connection.send(server_controller, ProductDAO.update(productRequest, Path.FIRST_SERVER_PRODUCT));
		} else if (productRequest.getOperation() == 3) {
			Connection.send(server_controller, ProductDAO.read(productRequest, Path.FIRST_SERVER_PRODUCT));
		} else if (productRequest.getOperation() == 4) {
			Connection.send(server_controller, ProductDAO.delete(productRequest, Path.FIRST_SERVER_PRODUCT));
		} else {
			System.out.println("Erro no CRUD");
		}
	}

	public static boolean connect() {
		try {
			server_controller = firstServer_socket.accept();
			return true;
		} catch (Exception e) {
			System.out.println("Erro de conexão..." + e.getMessage());
			return false;
		}
	}

}
