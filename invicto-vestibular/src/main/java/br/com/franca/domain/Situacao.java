package br.com.franca.domain;

// Usado para Unidades e Turmas
public enum Situacao {
	ATIVADA(1, "Ativada"), DESATIVADA(0, "Desativada"), 
	ATIVO(1, "Ativo"), DESATIVADO(0, "Desativado");
	
	private final int numero;
	private final String nome;

	private Situacao(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}
}
