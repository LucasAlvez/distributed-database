package com.uam.aps.utils;

import com.uam.aps.request.ClientRequest;
import com.uam.aps.response.ClientResponse;
import com.uam.aps.status.StatusEnum;

public class Builder {

	public static ClientResponse clientBuild(ClientRequest request, StatusEnum status) {
		ClientResponse response = new ClientResponse();
		response.setName(request.getName());
		response.setCpf(request.getCpf());
		response.setAge(request.getAge());
		response.setMessage(status);
		return response;
	}
	
	public static ClientResponse clientBuildError (ClientRequest request, StatusEnum status) {
		ClientResponse response = new ClientResponse();
		response.setName("");
		response.setCpf("");
		response.setAge(0);
		response.setMessage(status);
		return response;
	}

	public static void printClient(ClientResponse clientResponse) {
		System.out.println(clientResponse.getMessage().getName());

		if(clientResponse.getMessage().getName().equals("Operação realizada com sucesso"))
		System.out.println("Nome: " + clientResponse.getName() 
						+ "\nCpf: " + clientResponse.getCpf() 
						+ "\nIdade: " + clientResponse.getAge());
		
	}
}
