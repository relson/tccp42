package br.unip.tccp42.modelo.entidade;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;

import br.unip.tccp42.modelo.EntidadeBase;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Usuario extends EntidadeBase {
	
	/**
	 * 
	 */
	@Persistent
	@Unique(name="usuario_login_unq")
	private String login;
	@Persistent
	private String senha;
	@Persistent
	private Boolean recebeNotificacao;
	
	public Usuario(Long codigo, String nome, String login, String senha,
			Boolean recebeNotificacao) {
		super(codigo, nome);
		this.login = login;
		this.senha = senha;
		this.recebeNotificacao = recebeNotificacao;
	}
	public Usuario() {
		super();
	}
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * @return the recebeNotificacao
	 */
	public Boolean getRecebeNotificacao() {
		return recebeNotificacao;
	}
	/**
	 * @param recebeNotificacao the recebeNotificacao to set
	 */
	public void setRecebeNotificacao(Boolean recebeNotificacao) {
		this.recebeNotificacao = recebeNotificacao;
	}
}
