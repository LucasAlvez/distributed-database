package com.uam.aps.server.third;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.uam.aps.connection.Connection;
import com.uam.aps.dao.EmployeeDAO;
import com.uam.aps.dao.ProductDAO;
import com.uam.aps.request.EmployeeRequest;
import com.uam.aps.request.ProductRequest;
import com.uam.aps.utils.Path;

public class ThirdServer {

	static ServerSocket thirdServer_socket;
	static Socket server_controller;

	public ThirdServer() {
		try {
			thirdServer_socket = new ServerSocket(9324);
			System.out.println("Servidor 3 no ar!!!\nAguardando ServerController enviar requisiçao ...");
		} catch (Exception e) {
			System.out.println("Nao criei o server socket...");
		}
	}

	public static void main(String args[]) throws IOException {

		new ThirdServer();

		while (true) {
			if (connect()) {
				// Recebe requisição do ServerController
				Object request = (Object) Connection.receive(server_controller);

				if (request.getClass().getName().equals(Path.EMPLOYEECLASS)) {
					EmployeeRequest employeeRequest = new EmployeeRequest();
					employeeRequest = (EmployeeRequest) request;
					employeeCRUD(employeeRequest);
				}

				if (request.getClass().getName().equals(Path.PRODUCTCLASS)) {
					ProductRequest productRequest = new ProductRequest();
					productRequest = (ProductRequest) request;
					productCRUD(productRequest);
				}
			} else {
				try {
					thirdServer_socket.close();
					break;
				} catch (Exception e) {
					System.out.println("Nao desconectei...");
				}
			}
		}
	}

	public static void employeeCRUD(EmployeeRequest employeeRequest) throws IOException {
		if (employeeRequest.getOperation() == 1) {
			employeeRequest.setCpfAux(employeeRequest.getCpf());
			Connection.send(server_controller, EmployeeDAO.create(employeeRequest, Path.THIRD_SERVER_EMPLOYEE));
		} else if (employeeRequest.getOperation() == 2) {
			Connection.send(server_controller, EmployeeDAO.update(employeeRequest, Path.THIRD_SERVER_EMPLOYEE));
		} else if (employeeRequest.getOperation() == 3) {
			Connection.send(server_controller, EmployeeDAO.read(employeeRequest, Path.THIRD_SERVER_EMPLOYEE));
		} else if (employeeRequest.getOperation() == 4) {
			Connection.send(server_controller, EmployeeDAO.delete(employeeRequest, Path.THIRD_SERVER_EMPLOYEE));
		} else {
			System.out.println("Erro no CRUD");
		}
	}

	public static void productCRUD(ProductRequest productRequest) throws IOException {
		if (productRequest.getOperation() == 1) {
			productRequest.setCodeAux(productRequest.getCode());
			Connection.send(server_controller, ProductDAO.create(productRequest, Path.THIRD_SERVER_PRODUCT));
		} else if (productRequest.getOperation() == 2) {
			Connection.send(server_controller, ProductDAO.update(productRequest, Path.THIRD_SERVER_PRODUCT));
		} else if (productRequest.getOperation() == 3) {
			Connection.send(server_controller, ProductDAO.read(productRequest, Path.THIRD_SERVER_PRODUCT));
		} else if (productRequest.getOperation() == 4) {
			Connection.send(server_controller, ProductDAO.delete(productRequest, Path.THIRD_SERVER_PRODUCT));
		} else {
			System.out.println("Erro no CRUD");
		}
	}

	static boolean connect() {
		try {
			server_controller = thirdServer_socket.accept();
			return true;
		} catch (Exception e) {
			System.out.println("Erro de connect..." + e.getMessage());
			return false;
		}
	}

}
