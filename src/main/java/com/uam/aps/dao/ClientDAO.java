package com.uam.aps.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.uam.aps.request.ClientRequest;

public class ClientDAO {

	public static void create(ClientRequest clientRequest) {
		Gson gson = new Gson();

		// converte objetos Java para JSON e retorna JSON como String
		String json = gson.toJson(clientRequest);

		try {
			// Escreve Json convertido em arquivo chamado "file.json"
			Writer writer = new BufferedWriter(new FileWriter("server1.json", true));
			writer.append(json + "\n");
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
