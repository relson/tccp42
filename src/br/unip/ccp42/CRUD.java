package br.unip.ccp42;

public interface CRUD {
	void novo() throws Exception;
	void carregar() throws Exception;
	void atualizar() throws Exception;
	void excluir() throws Exception;
}