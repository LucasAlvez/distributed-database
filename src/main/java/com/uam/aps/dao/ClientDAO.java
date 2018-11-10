package com.uam.aps.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.uam.aps.request.ClientRequest;
import com.uam.aps.response.ClientResponse;
import com.uam.aps.status.StatusEnum;
import com.uam.aps.utils.Builder;

public class ClientDAO {

	@SuppressWarnings({ "unchecked", "resource" })
	public static ClientResponse create(ClientRequest request, String path) throws IOException {
		Gson gsonWrtiter = new Gson();
		Gson gsonReader = new Gson();

		BufferedReader br = new BufferedReader(new FileReader(path));

		if (request != null) {
			// Converte String JSON para objeto Java
			List<ClientRequest> list = new ArrayList<>();
			list = gsonReader.fromJson(br, ArrayList.class);
			br.close();
			
			list.add(request);

			// converte objetos Java para JSON e retorna JSON como String
			String json = gsonWrtiter.toJson(list);

			// Escreve Json convertido em arquivo
			Writer writer = new BufferedWriter(new FileWriter(path, false));
			writer.write(json);
			writer.close();

			return Builder.clientBuild(request, StatusEnum.SUCESSO);
		} else {
			return Builder.clientBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
	}

	@SuppressWarnings("unused")
	public static ClientResponse read(ClientRequest request, String path) throws IOException {
		Gson gson = new Gson();

		if (request != null && request.getCpfAux() != "") {
			BufferedReader br = new BufferedReader(new FileReader(path));
			ClientRequest[] list = gson.fromJson(br, ClientRequest[].class);
			br.close();
			for (int i = 0; i < list.length; i++) {
				if (list[i].getCpf().equals(request.getCpf())) {
					return Builder.clientBuild(list[i], StatusEnum.SUCESSO);
				} else {
					return Builder.clientBuildError(request, StatusEnum.NAO_ENCONTRADO);
				}
			}
		} else {
			return Builder.clientBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
		return null;
	}

	@SuppressWarnings("unused")
	public static ClientResponse update(ClientRequest request, String path) throws IOException {
		Gson gson = new Gson();

		if (request != null && request.getCpfAux() != "") {
			BufferedReader br = new BufferedReader(new FileReader(path));

			ClientRequest[] list = gson.fromJson(br, ClientRequest[].class);
			br.close();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					if (list[i].getCpf().equals(request.getCpfAux())) {
						list[i].setName(request.getName());
						list[i].setCpf(request.getCpf());
						list[i].setCpfAux(request.getCpf());
						list[i].setAge(request.getAge());
						list[i].setOperation(request.getOperation());
						String json = gson.toJson(list);

						Writer writer = new BufferedWriter(new FileWriter(path, false));
						writer.write(json);
						writer.close();
						return Builder.clientBuild(list[i], StatusEnum.SUCESSO);
					} else {
						return Builder.clientBuildError(request, StatusEnum.NAO_ENCONTRADO);
					}
				}
			} else {
				return Builder.clientBuildError(request, StatusEnum.NAO_ENCONTRADO);
			}
		} else {
			return Builder.clientBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
		return null;
	}

	public static ClientResponse delete(ClientRequest request, String path) throws IOException {
		Gson gson = new Gson();

		if (request != null && request.getCpfAux() != "") {
			BufferedReader br = new BufferedReader(new FileReader(path));

			ClientResponse[] list = gson.fromJson(br, ClientResponse[].class);
			br.close();
			List<ClientResponse> response = new ArrayList<>();
			for (int i = 0; i < list.length; i++) {
				response.add(list[i]);
				if (list[i].getCpf().equals(request.getCpf())) {
					request.setName(list[i].getName());
					request.setCpf(list[i].getCpf());
					request.setCpfAux(request.getCpf());
					request.setAge(list[i].getAge());
					response.remove(list[i]);
				} else {
					return Builder.clientBuildError(request, StatusEnum.NAO_ENCONTRADO);
				}
			}

			String json = gson.toJson(response);

			Writer writer = new BufferedWriter(new FileWriter(path, false));
			writer.write(json);
			writer.close();
			return Builder.clientBuild(request, StatusEnum.SUCESSO);
		} else {
			return Builder.clientBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
	}
	
}
