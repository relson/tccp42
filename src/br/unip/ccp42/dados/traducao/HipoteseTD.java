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
		// TODO Implementar M�todo
		return null;
	}

	@Override
	public EntidadeBase obterPorCodigo(int codigo) throws SQLException,
			ClassNotFoundException {
		// TODO Implementar M�todo
		return null;
	}

	@Override
	public ArrayList<EntidadeBase> obterTodos() throws SQLException,
			ClassNotFoundException {
		// TODO Implementar M�todo
		return null;
	}

	@Override
	public void atualizar(EntidadeBase entidade) {
		// TODO Implementar M�todo

	}

	@Override
	public void excluir(EntidadeBase entidade) {
		// TODO Implementar M�todo

	}

	@Override
	public void inserir(EntidadeBase entidade) {
		// TODO Implementar M�todo

	}

	@Override
	protected void setNomeTabela() {
		this.setNomeTabela("hipotese");
	}

}
