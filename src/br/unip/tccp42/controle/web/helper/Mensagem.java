package br.unip.tccp42.controle.web.helper;

public class Mensagem {
	private String descricao;
	private String corpo;
	private boolean erro;
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the corpo
	 */
	public String getCorpo() {
		return corpo;
	}
	/**
	 * @param corpo the corpo to set
	 */
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	/**
	 * @return the erro
	 */
	public boolean isErro() {
		return erro;
	}
	/**
	 * @param erro the erro to set
	 */
	public void setErro(boolean erro) {
		this.erro = erro;
	}
}