package br.unip.ccp42.negocio.mi;

import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.traducao.PerfilTD;

public class Perfil extends EntidadeBase {

	private ArrayList<Opcao> opcoes;

	/**
	 * @return the opcoes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected ArrayList<Opcao> getOpcoes() throws SQLException,
			ClassNotFoundException {

		if (opcoes == null) {

			PerfilTD perfilTD = new PerfilTD();

			this.opcoes = perfilTD.obterPorEntidade(this);

		}

		return opcoes;
	}

	/**
	 * @param opcoes
	 *            the opcoes to set
	 */
	protected void setOpcoes(ArrayList<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

	@Override
	protected void setTradutorDados() {
		this.setTradutorDados(new PerfilTD());
	}

}
