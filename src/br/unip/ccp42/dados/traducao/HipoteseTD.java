package br.unip.ccp42.dados.traducao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.TradutorBase;

public class HipoteseTD extends TradutorBase {

	@Override
	protected EntidadeBase obterEntidade(ResultSet resultSet)
			throws SQLException {
		// TODO Implementar Método
		return null;
	}

	@Override
	public EntidadeBase obterPorCodigo(int codigo) throws SQLException,
			ClassNotFoundException {
		// TODO Implementar Método
		return null;
	}

	@Override
	public ArrayList<EntidadeBase> obterTodos() throws SQLException,
			ClassNotFoundException {
		// TODO Implementar Método
		return null;
	}

	@Override
	public void atualizar(EntidadeBase entidade) {
		// TODO Implementar Método

	}

	@Override
	public void excluir(EntidadeBase entidade) {
		// TODO Implementar Método

	}

	@Override
	public void inserir(EntidadeBase entidade) {
		// TODO Implementar Método

	}

	@Override
	protected void setNomeTabela() {
		this.setNomeTabela("hipotese");
	}

}
