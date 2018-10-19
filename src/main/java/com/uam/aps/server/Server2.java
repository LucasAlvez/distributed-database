package com.uam.aps.server;

import java.net.ServerSocket;
import java.net.Socket;

import com.uam.aps.connection.Connection;

public class Server2 {

	static ServerSocket serversocket;
	static Socket client_socket;
	static Connection connect;

	public Server2() {
		try {
			serversocket = new ServerSocket(9300);
			System.out.println("Servidor no ar!!!");
			System.out.println("Aguardando app fazer requisiçao ...");
		} catch (Exception e) {
			System.out.println("Nao criei o server socket...");
		}
	}

	public static void main(String args[]) {
	}

	static boolean connect() {
		try {
			client_socket = serversocket.accept();
			return true;
		} catch (Exception e) {
			System.out.println("Erro de connect..." + e.getMessage());
			return false;
		}
	}

}
