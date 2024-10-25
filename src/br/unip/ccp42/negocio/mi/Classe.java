package br.unip.ccp42.negocio.mi;

import br.unip.ccp42.dados.traducao.ClasseTD;

public class Classe extends Perfil {

	@Override
	protected void setTradutorDados() {
		this.setTradutorDados(new ClasseTD());
	}

}