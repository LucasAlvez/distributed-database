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
import com.uam.aps.request.EmployeeRequest;
import com.uam.aps.response.EmployeeResponse;
import com.uam.aps.status.StatusEnum;
import com.uam.aps.utils.Builder;

public class EmployeeDAO {

	@SuppressWarnings({ "unchecked", "resource" })
	public static EmployeeResponse create(EmployeeRequest request, String path) throws IOException {
		Gson gsonWrtiter = new Gson();
		Gson gsonReader = new Gson();

		BufferedReader br = new BufferedReader(new FileReader(path));
		BufferedReader brTwo = new BufferedReader(new FileReader(path));

		if (request != null) {
			// Converte String JSON para objeto Java
			List<EmployeeRequest> list = new ArrayList<>();
			list = gsonReader.fromJson(br, ArrayList.class);
			br.close();
			
			EmployeeRequest[] listAux = gsonReader.fromJson(brTwo, EmployeeRequest[].class);
			brTwo.close();
			
			if (listAux.length > 0) {
				for (int i = 0; i < listAux.length; i++) {
					if (listAux[i].getCpf().equals(request.getCpf())) {
						return Builder.employeeBuildError(listAux[i], StatusEnum.CADASTRO_EXISTENTE);
					} else {
						list.add(request);
					}
				}
			} else {
				list.add(request);
			}

			// converte objetos Java para JSON e retorna JSON como String
			String json = gsonWrtiter.toJson(list);

			// Escreve Json convertido em arquivo
			Writer writer = new BufferedWriter(new FileWriter(path, false));
			writer.write(json);
			writer.close();

			return Builder.employeeBuild(request, StatusEnum.SUCESSO);
		} else {
			return Builder.employeeBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
	}

	@SuppressWarnings("unused")
	public static EmployeeResponse read(EmployeeRequest request, String path) throws IOException {
		Gson gson = new Gson();

		if (request != null && request.getCpfAux() != "") {
			BufferedReader br = new BufferedReader(new FileReader(path));
			EmployeeRequest[] list = gson.fromJson(br, EmployeeRequest[].class);
			br.close();
			
			if (list.length > 0) {
				for (int i = 0; i < list.length; i++) {
					if (list[i].getCpf().equals(request.getCpf())) {
						return Builder.employeeBuild(list[i], StatusEnum.SUCESSO);
					} else {
						return Builder.employeeBuildError(request, StatusEnum.NAO_ENCONTRADO);
					}
				}
			} else {
				return Builder.employeeBuildError(request, StatusEnum.NAO_ENCONTRADO);
			}
		} else {
			return Builder.employeeBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
		return null;
	}

	@SuppressWarnings({ "unused"})
	public static EmployeeResponse update(EmployeeRequest request, String path) throws IOException {
		Gson gson = new Gson();

		if (request != null && request.getCpfAux() != "") {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			EmployeeRequest[] list = gson.fromJson(br, EmployeeRequest[].class);
			br.close();

			if (list.length > 0) {
				for (int i = 0; i < list.length; i++) {
					if (list[i].getCpf().equals(request.getCpfAux())) {
						list[i].setName(request.getName());
						list[i].setCpf(request.getCpf());
						list[i].setCpfAux(request.getCpf());
						list[i].setResponsibility(request.getResponsibility());
						list[i].setSalary(request.getSalary());
						list[i].setOperation(request.getOperation());
						String json = gson.toJson(list);

						Writer writer = new BufferedWriter(new FileWriter(path, false));
						writer.write(json);
						writer.close();
						
						return Builder.employeeBuild(list[i], StatusEnum.SUCESSO);
					} else {
						return Builder.employeeBuildError(request, StatusEnum.NAO_ENCONTRADO);
					}
				}
			} else {
				return Builder.employeeBuildError(request, StatusEnum.NAO_ENCONTRADO);
			}
		} else {
			return Builder.employeeBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
		return null;
	}

	public static EmployeeResponse delete(EmployeeRequest request, String path) throws IOException {
		Gson gson = new Gson();
		String json = "";

		if (request != null && request.getCpfAux() != "") {
			BufferedReader br = new BufferedReader(new FileReader(path));

			EmployeeResponse[] list = gson.fromJson(br, EmployeeResponse[].class);
			br.close();

			if (list.length > 0) {
				List<EmployeeResponse> response = new ArrayList<>();
				for (int i = 0; i < list.length; i++) {
					response.add(list[i]);
					if (list[i].getCpf().equals(request.getCpf())) {
						request.setName(list[i].getName());
						request.setCpf(list[i].getCpf());
						request.setCpfAux(request.getCpf());
						request.setResponsibility(list[i].getResponsibility());
						request.setSalary(list[i].getSalary());
						response.remove(list[i]);
						json = gson.toJson(response);
					} else {
						return Builder.employeeBuildError(request, StatusEnum.NAO_ENCONTRADO);
					}
				}
			} else {
				return Builder.employeeBuildError(request, StatusEnum.NAO_ENCONTRADO);
			}

			Writer writer = new BufferedWriter(new FileWriter(path, false));
			writer.write(json);
			writer.close();
			
			return Builder.employeeBuild(request, StatusEnum.SUCESSO);
		} else {
			return Builder.employeeBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
	}

}
