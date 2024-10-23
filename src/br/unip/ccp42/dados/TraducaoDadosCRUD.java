package br.unip.ccp42.dados;

import java.sql.SQLException;

import br.unip.ccp42.EntidadeBase;

public interface TraducaoDadosCRUD {
	void inserir(EntidadeBase entidade) throws SQLException, ClassNotFoundException, Exception;
	void atualizar(EntidadeBase entidade) throws SQLException, ClassNotFoundException, Exception;
	void excluir(EntidadeBase entidade) throws SQLException, ClassNotFoundException, Exception;
}
