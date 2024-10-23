package br.unip.ccp42.dados.traducao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.TradutorBase;
import br.unip.ccp42.dados.util.Utils;
import br.unip.ccp42.negocio.entidade.Usuario;
import br.unip.ccp42.negocio.mi.Opcao;

public class UsuarioTD extends TradutorBase {

	private final int COLUNA_SENHA = super.COLUNA_NOME + 1;
	
	@Override
	public EntidadeBase obterPorCodigo(int codigo) 			
			throws SQLException, ClassNotFoundException {
		
		String sql = obterSQLTodos();
		
		sql += " WHERE codigo_entidade = " + codigo;
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql);
		
		Usuario usuario = null;
		
		while (resultSet.next())
		{
			usuario = (Usuario) this.obterEntidade(resultSet);
		}
		
		utils.finalizarResultSet(resultSet);
		
		return (EntidadeBase)usuario;
	}


	@Override
	public ArrayList<EntidadeBase> obterTodos() 
			throws SQLException, ClassNotFoundException {
		
		ArrayList<EntidadeBase> aplicacaoLinkLista =
			new ArrayList<EntidadeBase>();
		
		String sql = obterSQLTodos();
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql);
		
		while (resultSet.next())
		{
			aplicacaoLinkLista.add(this.obterEntidade(resultSet));
		}
		
		utils.finalizarResultSet(resultSet);
		
		return aplicacaoLinkLista;
	}

	private String obterSQLTodos() {
		
		StringBuffer sbSQL = new StringBuffer();
		
		sbSQL.append(" SELECT ");
		sbSQL.append(" 	codigo_entidade, ");
		sbSQL.append("	nome_entidade,");
		sbSQL.append("  senha");
		sbSQL.append(" FROM usuario ");
		
		return sbSQL.toString();
	}

	/* (non-Javadoc)
	 * @see br.unip.ccp42.dados.traducao.TradutorBase#obterEntidade(java.sql.ResultSet)
	 */
	@Override
	protected EntidadeBase obterEntidade(ResultSet resultSet) 
			throws SQLException {
		Usuario usuario = new Usuario();
		
			usuario.setCodigo(resultSet.getInt(COLUNA_CODIGO));
			usuario.setNome(resultSet.getString(COLUNA_NOME));
			usuario.setSenha(resultSet.getString(COLUNA_SENHA));
		
		return (EntidadeBase)usuario;
	}

	/**
	 * Obtém uma entidade do banco de dados de usuário
	 * @param nomeUsuario nome de login do usuário
	 * @param senha senha do usuário
	 * @return se usuário e senha existirem retorna um objeto Usuario
	 * @throws Exception Se não econtrar um usuário
	 */
	public Usuario efetuarLogin(String nomeUsuario, String senha)
			throws Exception{
		Usuario usuario = new Usuario();
		
		String sql = obterSQLTodos();
		
		sql += " WHERE nome_entidade = ? and senha = ?";
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql, new String[]{nomeUsuario, senha});
		
		if (resultSet.next())
		{
			usuario = (Usuario) this.obterEntidade(resultSet);
		}
		else
		{
			throw new Exception("Usuário ou senha inválido.");
		}
		utils.finalizarResultSet(resultSet);
				
		return usuario;
	}
	
	/**
	 * Atualiza o perfil do usuário
	 * @param usuario a ser atualizado o perfil
	 * @param opcao a ser inserida no perfil
	 * @throws ClassNotFoundException quando o driver de conexão não econtrado
	 * @throws SQLException quando existe um erro no comando SQL a ser executado 
	 */
	public void inserirOpcaoPerfil(Usuario usuario, Opcao opcao) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		
		sql
			.append("INSERT INTO usuario_pergunta_opcao(")
			.append("codigo_usuario, codigo_pergunta_opcao) ")
			.append("VALUES (?, ?)");
		
		Utils utils = new Utils();
		
		utils.executarComandoSQL(
				sql.toString(),
				new Object[]{
					usuario.getCodigo(),
					opcao.getCodigo()
				});
	}


	@Override
	public void atualizar(EntidadeBase entidade) {
		// TODO Implementar M�todo
		
	}


	@Override
	public void excluir(EntidadeBase entidade) throws SQLException, ClassNotFoundException {
		Usuario usuario = (Usuario)entidade;
		
		this.excluirOpcoesUsuario(usuario);
		
		String sql = this.getSQLDelete();
		Object[] parametros = this.getParametrosDelete(entidade);
		
		Utils utils = new Utils();
		
		utils.executarComandoSQL(sql, parametros);	
	}

	/**
	 * TODO Documentar M�todo
	 * @param pergunta
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	private void excluirOpcoesUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{
		StringBuffer sbSQLDelete = new StringBuffer();
		
		sbSQLDelete
			.append(" DELETE FROM usuario_pergunta_opcao ")
			.append(" WHERE codigo_usuario = ? ");
		
		Utils utils = new Utils();
		
		Object[] parametros = this.getParametrosDelete(usuario);
		
		utils.executarComandoSQL(sbSQLDelete.toString(), parametros);
	}
	
	@Override
	public void inserir(EntidadeBase entidade) throws Exception {
		
		this.validarNovoNomeNovoUsuario(entidade.getNome());
		
		String sql = obterSQLInsert();
		Object[] parametros = this.getParametrosInsert(entidade);
				
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql, parametros);
		
		if (resultSet.next())
		{
			entidade.setCodigo(resultSet.getInt(COLUNA_CODIGO));
		}
				
		utils.finalizarResultSet(resultSet);				
	}

	/**
	 * TODO Documentar M�todo
	 * @param entidade
	 * @throws Exception 
	 */
	private void validarNovoNomeNovoUsuario(String nome) throws Exception {
		
		Usuario usuario = this.obterPorNome(nome);
		
		if (usuario != null) {
			throw new Exception("Já existe um usuário cadastrado com o nome informado.");
		}
		
	}


	private Object[] getParametrosInsert(EntidadeBase entidade) {
		String nome = entidade.getNome();
		String senha = ((Usuario)entidade).getSenha();
		
		Object[] parametros = {
				nome,
				senha
			};		
		return parametros;
	}

	private String obterSQLInsert() {
		String sqlInsert = "INSERT INTO " + 
				this.getNomeTabela()  +
				"(nome_entidade,senha) VALUES (?,?) RETURNING codigo_entidade";
		
		return sqlInsert;
	}

	public Usuario obterPorNome(String nome) throws SQLException, ClassNotFoundException {

		String sql = obterSQLTodos();
		
		sql += " WHERE nome_entidade = ?";
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql, nome);
		
		Usuario usuario = null;
		
		while (resultSet.next())
		{
			usuario = (Usuario) this.obterEntidade(resultSet);
		}
		
		utils.finalizarResultSet(resultSet);
		
		return usuario;
	}

	@Override
	protected void setNomeTabela() {
		this.setNomeTabela("usuario");
	}
}
