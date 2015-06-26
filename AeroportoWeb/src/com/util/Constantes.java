package com.util;


@SuppressWarnings("rawtypes")
public class Constantes {

	public static final String UNIDADE_PERSISTENCA = "AeroportoPU";
	public static final String USUARIO_SESSAO = "usuario";
	public static final String FORMATO_DATA_HORA = "dd/MM/yyyy HH:mm:ss";
	
	
	/**
	 * Constantes de Operacao
	 */
	public static final String OPERACAO_INCLUSAO = "Inclusão";
	public static final String OPERACAO_ATUALIZACAO = "Atualização";
	public static final String OPERACAO_EXCLUSAO = "Exclusão";
	public static final String OPERACAO_PESQUISA = "Pesquisa";
	
	/**
	 * CONSTANTES DE MENSAGEM
	 */
	public static final String MENSAGEM_SUCESSO = "A {operacao} do {item} foi realizada com sucesso!";
	public static final String MENSAGEM_FALHA = "A {operacao} do {item} falhou! Tente mais tarde!";
	
	public static final String getMensagemSucesso(Class qualVO, String operacao){
		return MENSAGEM_SUCESSO.replace("{operacao}", operacao).replace("{item}", qualVO.getSimpleName());
	}

	public static final String getMensagemFalha(Class qualVO, String operacao){
		return MENSAGEM_SUCESSO.replace("{operacao}", operacao).replace("{item}", qualVO.getSimpleName());
	}

}
