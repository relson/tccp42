package br.unip.ccp42.negocio.mi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.traducao.ClasseTD;
import br.unip.ccp42.negocio.entidade.Usuario;

public class Indutor {
	
	private Usuario usuario;
	
	private ArrayList<Hipotese> hipotese;
	
	public ArrayList<Hipotese> getHipotese() throws SQLException, ClassNotFoundException {
		
		if (hipotese == null) {
			this.hipotese = obterHipoteseUsuario();
		}
		
		return hipotese;
	}

	private ArrayList<Hipotese> obterHipoteseUsuario() 
		throws SQLException, ClassNotFoundException {
		
		ArrayList<Hipotese> hipoteses = new ArrayList<Hipotese>();
		
		ClasseTD classeTD = new ClasseTD();
		
		// Força a obter as novas opções do banco de dados.
		this.usuario.setOpcoes(null);
						
		ArrayList<Opcao> opcoesUsuario = this.usuario.getOpcoes();
				
		for (EntidadeBase entidade :classeTD.obterTodos()){
			
			Classe aplicacao = (Classe)entidade; 
			
			ArrayList<Opcao> opcoesAplicacao = 
				aplicacao.getOpcoes();
			
			boolean inserir = false;
			
			for (Opcao opcaoUsuario:opcoesUsuario) {
				
				for (Opcao opcaoAplicao:opcoesAplicacao) {
					
					if (opcaoUsuario.getCodigo() == opcaoAplicao.getCodigo()) {
						inserir = true;
						break;
					}
				}
				if (!inserir) {
					break;
				}
			}
			
			if (inserir) {
				
				Hipotese hipotese = new Hipotese();
				
				hipotese.setAplicacao(aplicacao);
								
				if (!hipoteses.contains(hipotese)) {
					
					hipoteses.add(hipotese);
				}
			}
			
		}
		
		return hipoteses;
	}
	public void setHipotese(ArrayList<Hipotese> hipotese) {
		this.hipotese = hipotese;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void classificar() throws SQLException, ClassNotFoundException
	{
		Collections.sort(this.getHipotese());		
	}
	
	public Indutor(Usuario usuario,
			ArrayList<Hipotese> hipotese) {
		super();
		this.usuario = usuario;
		this.hipotese = hipotese;
	}
	public Indutor(Usuario usuario) {
		this.usuario = usuario;
	}	
	
}
