/*
 * UNIP CCP42 - Monografia, Tema: Analisador Financeiro
 * 
 */
package br.unip.ccp42.dados.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Método que configura um conexão com o banco de dados.
 * @author relson
 *
 */
public class ConexaoFabrica {
	/**
	 * Constante com endereço do banco de dados.
	 */
	private static final String CONEXAO_URL=
		"jdbc:postgresql://localhost:5432/analisador_financeiro";
	/**
	 * Constante com o nome do usuário para conexão com o
	 * banco de dados.
	 */
	private static final String CONEXAO_USUARIO="admin";
	/**
	 * Constante com a senha do nome do usuáio para conexão com
	 * o banco de dados.
	 */
	private static final String CONEXAO_SENHA="admin";
	/**
	 * Constante com o nome da classe para ser utilizada como 
	 * driver de conexão com banco de dados.
	 */
	private static final String CONEXAO_NOME_CLASSE_DRIVER="org.postgresql.Driver";
	
	/**
	 * Método de conveniência que cria uma conexão com o banco de dados.
	 * @return Objeto Connection configurado com uma conexão com o banco
	 * 		   de dados PostGreSQL a princípio.
	 * @throws SQLException pode ser por causa do nome de banco de dados
	 * 		   ou endereço estiver errado. 
	 * @throws ClassNotFoundException se não conseguir carregar o driver
	 * 		   de conexão.
	 */
	public static Connection criar() throws SQLException, ClassNotFoundException		
	{
		Class.forName(CONEXAO_NOME_CLASSE_DRIVER);
		
		return DriverManager.getConnection(CONEXAO_URL, CONEXAO_USUARIO, CONEXAO_SENHA);
	} // fim do método criar
} // fim da classe ConexaoFabrica