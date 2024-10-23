package br.unip.ccp42.dados.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe com métodos usados no contexto de negócios
 * @author relson
 */
public class Utils {
	
	/**
	 * Obtém um ResultSet configurado somente leitura e com o cursor somente 
	 * para frente
	 * @param sql da Consulta
	 * @return ResultSet com o resultado da consulta
	 * @throws SQLException quando existe algum erro no SQL
	 * @throws ClassNotFoundException se não conseguir localizar o driver de conexão
	 * @see ConexaoFabrica#criar()
	 */
	public ResultSet obterResultSet(String sql) throws SQLException, ClassNotFoundException
	{	
			Connection cn = ConexaoFabrica.criar();
			
			 Statement statement = cn.createStatement(
					 ResultSet.TYPE_FORWARD_ONLY,
					 ResultSet.CONCUR_READ_ONLY);
			 
			 ResultSet resultSet = statement.executeQuery(sql);
			 
			 return resultSet;
	}
	
	/**
	 * Método sobrecarregado
	 * @param sql
	 * @param parametros
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @see #obterResultSet(String)
	 */
	public ResultSet obterResultSet(String sql, Object[] parametros) throws SQLException, ClassNotFoundException
	{
			Connection cn = ConexaoFabrica.criar();
			
			PreparedStatement preparedStatement = cn.prepareStatement(
					sql,
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			
			System.out.println(sql);
			
			for (int i = 0; i < parametros.length; i++) {
				
				if (parametros[i] instanceof String)				
					preparedStatement.setString(i + 1, parametros[i].toString());
				else if (parametros[i] instanceof Integer)
					preparedStatement.setInt(i + 1, Integer.parseInt(parametros[i].toString()));
				else if (parametros[i] instanceof Double)
					preparedStatement.setDouble(i + 1, Double.parseDouble(parametros[i].toString()));				
				else
					preparedStatement.setObject(i + 1, parametros[i]);
			}
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			return resultSet;	
	}
	
	/**
	 * 
	 * @param sql
	 * @param parametro
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see #obterResultSet(String, Object[])
	 */
	public ResultSet obterResultSet(String sql, Object parametro) throws SQLException, ClassNotFoundException {		
		return obterResultSet(sql, new Object[]{parametro});
	}
	
	/**
	 * Finaliza as conexões e o ResultSet
	 * @param resultSet
	 * @throws SQLException 
	 */
	public void finalizarResultSet(ResultSet resultSet) throws SQLException
	{
		if (resultSet.getStatement().getConnection() != null) {
			resultSet.getStatement().getConnection().close();
		}
		
		if (resultSet.getStatement() != null) {
			resultSet.getStatement().close();
		}
		
		if (resultSet != null) {
			resultSet.close();
		}		
	}

	/**
	 * Executa um comando sql em que não é necessário retornar
	 * um ResultSet
	 * @param comando Comando SQL a a ser executado
	 * @param parametros para o comando
	 * @throws ClassNotFoundException quando o driver de conexão não foi encontrado 
	 * @throws SQLException quando um erro no comando a ser executado é encontrado pelo SGBD 
	 */
	public void executarComandoSQL(String comando, Object[] parametros) throws SQLException, ClassNotFoundException {
		Connection cn = ConexaoFabrica.criar();
		
		PreparedStatement preparedStatement = cn.prepareStatement(
				comando,
				ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		System.out.println(comando);
		for (int i = 0; i < parametros.length; i++) {
			preparedStatement.setObject(i + 1, parametros[i]);
			System.out.println(parametros[i]);
		}
		
		preparedStatement.executeUpdate();		
	}	
}
