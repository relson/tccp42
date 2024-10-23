package br.unip.ccp42.negocio.entidade;

import br.unip.ccp42.dados.traducao.UsuarioTD;
import br.unip.ccp42.negocio.mi.Perfil;

public class Usuario extends Perfil {
	
	private String senha;
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario(){
		
	}
	
	public Usuario(int codigo){
		super();
		this.setCodigo(codigo);
	}
	
	public Usuario(int codigo, String nome){		
		this(codigo);
		this.setNome(nome);		
	}
	
	public Usuario(int codigo, String nome, String senha){
		this(codigo, nome);
		this.setSenha(senha);
	}

	@Override
	protected void setTradutorDados() {
		this.setTradutorDados(new UsuarioTD());
	}
}