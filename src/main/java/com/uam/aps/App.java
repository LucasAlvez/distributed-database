package com.uam.aps;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.uam.aps.connection.Connection;
import com.uam.aps.request.ClientRequest;
import com.uam.aps.request.EmployeeRequest;
import com.uam.aps.request.ProductRequest;
import com.uam.aps.response.ClientResponse;
import com.uam.aps.response.EmployeeResponse;
import com.uam.aps.response.ProductResponse;
import com.uam.aps.utils.Builder;
import com.uam.aps.utils.FileManager;
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
		
		FileManager.InitializeFiles();

		while (true) {
			System.out.println("\nEscolha:");
			System.out.println("(1) Cliente\n" + "(2) Funcionário\n" + "(3) Produto");
			int operation = in.nextInt();

			switch (operation) {
			case 1:
				ClientRequest clientRequest = new ClientRequest();
				ClientResponse clientResponse = new ClientResponse();

				clientRequest = Menu.clientMenu();

				new App();

				// Enviando requisição para o ServerController
				Connection.send(app_socket, clientRequest);

				// Recebendo resposta do ServerController
				clientResponse = (ClientResponse) Connection.receive(app_socket);

				// Printa a resposta
				Builder.printClient(clientResponse);

				try {
					app_socket.close();
				} catch (Exception e) {
					System.out.println("problemas ao fechar socket");
				}
				break;
			case 2:
				EmployeeRequest employeeRequest = new EmployeeRequest();
				EmployeeResponse employeeResponse = new EmployeeResponse();

				employeeRequest = Menu.employeeMenu();

				new App();

				Connection.send(app_socket, employeeRequest);

				employeeResponse = (EmployeeResponse) Connection.receive(app_socket);

				Builder.printEmployee(employeeResponse);

				try {
					app_socket.close();
				} catch (Exception e) {
					System.out.println("problemas ao fechar socket");
				}
				break;
			case 3:
				ProductRequest productRequest = new ProductRequest();
				ProductResponse productResponse = new ProductResponse();

				productRequest = Menu.productMenu();

				new App();

				Connection.send(app_socket, productRequest);

				productResponse = (ProductResponse) Connection.receive(app_socket);

				Builder.printProduct(productResponse);

				try {
					app_socket.close();
				} catch (Exception e) {
					System.out.println("problemas ao fechar socket");
				}
				break;
			}
		}
	}
}
