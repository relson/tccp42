package br.unip.ccp42.dados.traducao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.TradutorBase;
import br.unip.ccp42.negocio.mi.Opcao;

public class PerfilTD extends TradutorBase {

	@Override
	/**
	 * TODO Documentar m�todo
	 */
	protected EntidadeBase obterEntidade(ResultSet resultSet)
			throws SQLException {

		return null;
	}

	/**
	 * TODO Documentar m�todo
	 */
	@Override
	public EntidadeBase obterPorCodigo(int codigo) throws SQLException,
			ClassNotFoundException {
		return null;
	}

	@Override
	/**
	 * TODO Documentar m�todo
	 */
	public ArrayList<EntidadeBase> obterTodos() throws SQLException,
			ClassNotFoundException {
		return null;
	}

	@Override
	/**
	 * TODO Documentar m�todo
	 */
	protected void setNomeTabela() {
		this.setNomeTabela("perfil");
	}

	/**
	 * TODO Documentar m�todo
	 */
	@Override	
	public void excluir(EntidadeBase entidade) throws SQLException,
			ClassNotFoundException, Exception {
	}

	@Override
	public void inserir(EntidadeBase entidade) throws SQLException,
			ClassNotFoundException, Exception {
		// TODO Implementar M�todo
		throw new UnsupportedOperationException(
				"Recurso de inserção ainda não implementado");
	}

	/**
	 * 
	 * @param entidade
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Opcao> obterPorEntidade(EntidadeBase entidade)
			throws SQLException, ClassNotFoundException {
		ArrayList<Opcao> opcoes;
		OpcaoTD opcaoTD = new OpcaoTD();

		opcoes = opcaoTD.obterPorEntidade(entidade);

		return opcoes;
	}

}
