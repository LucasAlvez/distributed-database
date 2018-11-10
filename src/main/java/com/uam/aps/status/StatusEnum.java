package com.uam.aps.status;

public enum StatusEnum {
	
	SUCESSO("0", "Operação realizada com sucesso"),
	CADASTRO_EXISTENTE("1", "Já existe um cadastro com essas informações"),
	NAO_ENCONTRADO("2", "Busca não retornou nenhum resultado"),
	TEMPO_EXCEDIDO("3", "Processamento demorou mais do que o esperado"),
	ERRO_SERVIDOR("10", "Dificuldade de processamento do servidor. Necessário acessar outro"),
	REQUISIÇÃO_INVALIDA("11", "Erro na requisição");
	
	private final String code;
	private final String name;
	
	StatusEnum(String code, String name){
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
