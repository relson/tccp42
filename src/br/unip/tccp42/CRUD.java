/*
 * TODO Inserir Licen�a
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
	 * TODO Documentar m�todo
	 * 
	 * @param objetoInserir
	 */
	void inserir(T objetoInserir);

	// Read

	
	List<T> obterTodos() throws Exception;
	/**
	 * TODO Documentar m�todo
	 * 
	 * @param codigo
	 * @return
	 * @throws Exception 
	 */
	T obterPorCodigo(Long codigo) throws Exception;

	/**
	 * TODO Documentar m�todo
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
	 * TODO Documentar m�todo
	 * @param objetoExcluir
	 */
	void excluir(T objetoExcluir);
}
