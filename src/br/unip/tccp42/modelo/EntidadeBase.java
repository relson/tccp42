/**
 * TODO Documentar arquivo, inserir licença
 */
package br.unip.tccp42.modelo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * TODO documentar classe.
 * 
 * @author relson
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy=InheritanceStrategy.SUBCLASS_TABLE)
public abstract class EntidadeBase {

	/**
	 * TODO documentar se
	 */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long codigo;
	/**
	 * TODO Documentar campos
	 */
	@Persistent
	private String nome;

	public EntidadeBase() {

	}

	public EntidadeBase(Long codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

	/**
	 * TODO Documentar metodo
	 * 
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * TODO Documentar metodo
	 * 
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * TODO Documentar método
	 * 
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * TODO Documentar metodo
	 * 
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}