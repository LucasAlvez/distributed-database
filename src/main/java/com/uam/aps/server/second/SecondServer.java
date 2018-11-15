package com.uam.aps.server.second;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.uam.aps.connection.Connection;
import com.uam.aps.dao.ClientDAO;
import com.uam.aps.dao.EmployeeDAO;
import com.uam.aps.request.ClientRequest;
import com.uam.aps.request.EmployeeRequest;
import com.uam.aps.utils.Path;

public class SecondServer {

	static ServerSocket secondServer_socket;
	static Socket server_controller;

	public SecondServer() {
		try {
			secondServer_socket = new ServerSocket(9323);
			System.out.println("Servidor 2 no ar!!!\nAguardando ServerController enviar requisiçao ...");
		} catch (Exception e) {
			System.out.println("Nao criei o server socket...");
		}
	}

	public static void main(String args[]) throws IOException {

		new SecondServer();

		while (true) {
			if (connect()) {
				// Recebe requisição do ServerController
				Object request = (Object) Connection.receive(server_controller);

				if (request.getClass().getName().equals(Path.CLIENTCLASS)) {
					ClientRequest clientRequest = new ClientRequest();
					clientRequest = (ClientRequest) request;
					clientCRUD(clientRequest);
				}

				if (request.getClass().getName().equals(Path.EMPLOYEECLASS)) {
					EmployeeRequest employeeRequest = new EmployeeRequest();
					employeeRequest = (EmployeeRequest) request;
					employeeCRUD(employeeRequest);
				}
			} else {
				try {
					secondServer_socket.close();
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
			Connection.send(server_controller, ClientDAO.create(clientRequest, Path.SECOND_SERVER_CLIENT));
		} else if (clientRequest.getOperation() == 2) {
			Connection.send(server_controller, ClientDAO.update(clientRequest, Path.SECOND_SERVER_CLIENT));
		} else if (clientRequest.getOperation() == 3) {
			Connection.send(server_controller, ClientDAO.read(clientRequest, Path.SECOND_SERVER_CLIENT));
		} else if (clientRequest.getOperation() == 4) {
			Connection.send(server_controller, ClientDAO.delete(clientRequest, Path.SECOND_SERVER_CLIENT));
		} else {
			System.out.println("Erro no CRUD");
		}
	}

	public static void employeeCRUD(EmployeeRequest employeeRequest) throws IOException {
		if (employeeRequest.getOperation() == 1) {
			employeeRequest.setCpfAux(employeeRequest.getCpf());
			Connection.send(server_controller, EmployeeDAO.create(employeeRequest, Path.SECOND_SERVER_EMPLOYEE));
		} else if (employeeRequest.getOperation() == 2) {
			Connection.send(server_controller, EmployeeDAO.update(employeeRequest, Path.SECOND_SERVER_EMPLOYEE));
		} else if (employeeRequest.getOperation() == 3) {
			Connection.send(server_controller, EmployeeDAO.read(employeeRequest, Path.SECOND_SERVER_EMPLOYEE));
		} else if (employeeRequest.getOperation() == 4) {
			Connection.send(server_controller, EmployeeDAO.delete(employeeRequest, Path.SECOND_SERVER_EMPLOYEE));
		} else {
			System.out.println("Erro no CRUD");
		}
	}

	static boolean connect() {
		try {
			server_controller = secondServer_socket.accept();
			return true;
		} catch (Exception e) {
			System.out.println("Erro de connect..." + e.getMessage());
			return false;
		}
	}

}
