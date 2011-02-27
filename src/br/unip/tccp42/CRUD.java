/*
 * TODO Inserir Licença
 */
package br.unip.tccp42;

import java.util.List;

import br.unip.tccp42.modelo.EntidadeBase;

/**
 * TODO Documentar classe
 * 
 * @author relson
 * 
 * @param <T>
 */
public interface CRUD<T extends EntidadeBase> {
	// Create
	/**
	 * TODO Documentar método
	 * 
	 * @param objetoInserir
	 */
	void inserir(T objetoInserir);

	// Read

	
	List<T> obterTodos() throws Exception;
	/**
	 * TODO Documentar método
	 * 
	 * @param codigo
	 * @return
	 * @throws Exception 
	 */
	T obterPorCodigo(Long codigo) throws Exception;

	/**
	 * TODO Documentar método
	 * 
	 * @param nome
	 * @return
	 * @throws Exception 
	 */
	List<T> obterPorNome(String nome) throws Exception;

	// Update
	/**
	 * @param objetoAtualizar
	 */
	void atualizar(T objetoAtualizar);

	// Delete
	/**
	 * TODO Documentar método
	 * @param objetoExcluir
	 */
	void excluir(T objetoExcluir);
}
