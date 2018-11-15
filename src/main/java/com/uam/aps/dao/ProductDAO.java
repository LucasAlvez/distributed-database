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
import com.uam.aps.request.ProductRequest;
import com.uam.aps.response.ProductResponse;
import com.uam.aps.status.StatusEnum;
import com.uam.aps.utils.Builder;

public class ProductDAO {

	@SuppressWarnings({ "unchecked", "resource" })
	public static ProductResponse create(ProductRequest request, String path) throws IOException {
		Gson gsonWrtiter = new Gson();
		Gson gsonReader = new Gson();

		BufferedReader br = new BufferedReader(new FileReader(path));
		BufferedReader brTwo = new BufferedReader(new FileReader(path));

		if (request != null) {
			// Converte String JSON para objeto Java
			List<ProductRequest> list = new ArrayList<>();
			list = gsonReader.fromJson(br, ArrayList.class);
			br.close();
			
			ProductRequest[] listAux = gsonReader.fromJson(brTwo, ProductRequest[].class);
			brTwo.close();
			
			if (listAux.length > 0) {
				for (int i = 0; i < listAux.length; i++) {
					if (listAux[i].getCode().equals(request.getCode())) {
						return Builder.productBuildError(listAux[i], StatusEnum.CADASTRO_EXISTENTE);
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

			return Builder.productBuild(request, StatusEnum.SUCESSO);
		} else {
			return Builder.productBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
	}

	@SuppressWarnings("unused")
	public static ProductResponse read(ProductRequest request, String path) throws IOException {
		Gson gson = new Gson();

		if (request != null && request.getCodeAux() != "") {
			BufferedReader br = new BufferedReader(new FileReader(path));
			ProductRequest[] list = gson.fromJson(br, ProductRequest[].class);
			br.close();
			
			if (list.length > 0) {
				for (int i = 0; i < list.length; i++) {
					if (list[i].getCode().equals(request.getCode())) {
						return Builder.productBuild(list[i], StatusEnum.SUCESSO);
					} else {
						return Builder.productBuildError(request, StatusEnum.NAO_ENCONTRADO);
					}
				}
			} else {
				return Builder.productBuildError(request, StatusEnum.NAO_ENCONTRADO);
			}
		} else {
			return Builder.productBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
		return null;
	}

	@SuppressWarnings({ "unused"})
	public static ProductResponse update(ProductRequest request, String path) throws IOException {
		Gson gson = new Gson();

		if (request != null && request.getCodeAux() != "") {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			ProductRequest[] list = gson.fromJson(br, ProductRequest[].class);
			br.close();

			if (list.length > 0) {
				for (int i = 0; i < list.length; i++) {
					if (list[i].getCode().equals(request.getCodeAux())) {
						list[i].setName(request.getName());
						list[i].setCode(request.getCode());
						list[i].setCodeAux(request.getCode());
						list[i].setPrice(request.getPrice());
						list[i].setQuantity(request.getQuantity());
						list[i].setOperation(request.getOperation());
						String json = gson.toJson(list);

						Writer writer = new BufferedWriter(new FileWriter(path, false));
						writer.write(json);
						writer.close();
						
						return Builder.productBuild(list[i], StatusEnum.SUCESSO);
					} else {
						return Builder.productBuildError(request, StatusEnum.NAO_ENCONTRADO);
					}
				}
			} else {
				return Builder.productBuildError(request, StatusEnum.NAO_ENCONTRADO);
			}
		} else {
			return Builder.productBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
		return null;
	}

	public static ProductResponse delete(ProductRequest request, String path) throws IOException {
		Gson gson = new Gson();
		String json = "";

		if (request != null && request.getCodeAux() != "") {
			BufferedReader br = new BufferedReader(new FileReader(path));

			ProductResponse[] list = gson.fromJson(br, ProductResponse[].class);
			br.close();

			if (list.length > 0) {
				List<ProductResponse> response = new ArrayList<>();
				for (int i = 0; i < list.length; i++) {
					response.add(list[i]);
					if (list[i].getCode().equals(request.getCode())) {
						request.setName(list[i].getName());
						request.setCode(list[i].getCode());
						request.setCodeAux(request.getCode());
						request.setPrice(list[i].getPrice());
						request.setQuantity(list[i].getQuantity());
						response.remove(list[i]);
						json = gson.toJson(response);
					} else {
						return Builder.productBuildError(request, StatusEnum.NAO_ENCONTRADO);
					}
				}
			} else {
				return Builder.productBuildError(request, StatusEnum.NAO_ENCONTRADO);
			}

			Writer writer = new BufferedWriter(new FileWriter(path, false));
			writer.write(json);
			writer.close();
			
			return Builder.productBuild(request, StatusEnum.SUCESSO);
		} else {
			return Builder.productBuildError(request, StatusEnum.REQUISICAO_INVALIDA);
		}
	}

}
