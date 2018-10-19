package com.uam.aps;

import java.net.Socket;

import com.uam.aps.connection.Connection;

public class App 
{
	 static Connection connect;
	    static Socket socket;

	    public App() {
	        try {
	            socket = new Socket("localhost", 9100);
	        } catch (Exception e) {
	            System.out.println("Nao consegui resolver o host...");
	        }
	    }

	    public static void main(String[] args) {

	        
	}
}
