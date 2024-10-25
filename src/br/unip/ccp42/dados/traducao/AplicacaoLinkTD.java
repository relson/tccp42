package br.unip.ccp42.dados.traducao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.TradutorBase;
import br.unip.ccp42.dados.util.Utils;
import br.unip.ccp42.negocio.entidade.AplicacaoLink;
import br.unip.ccp42.negocio.entidade.Usuario;
import br.unip.ccp42.negocio.mi.Classe;

/**
 * Classe 
 * @author relson
 *
 */
public class AplicacaoLinkTD extends TradutorBase{

	private static final int COLUNA_URL = 3;

	@Override
	public EntidadeBase obterPorCodigo(int codigo) {
		
		return null;
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
		sbSQL.append("	url");
		sbSQL.append(" FROM aplicacao_link ");
		
		return sbSQL.toString();
	}

	/* (non-Javadoc)
	 * @see br.unip.ccp42.dados.traducao.TradutorBase#obterEntidade(java.sql.ResultSet)
	 */
	@Override
	protected EntidadeBase obterEntidade(ResultSet resultSet) {
		AplicacaoLink aplicacaoLink = new AplicacaoLink();
		
		try {
			aplicacaoLink.setCodigo(resultSet.getInt(COLUNA_CODIGO));
			aplicacaoLink.setNome(resultSet.getString(COLUNA_NOME));
			aplicacaoLink.setUrl(resultSet.getString(COLUNA_URL));
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return (EntidadeBase)aplicacaoLink;
	}

	@Override
	public void atualizar(EntidadeBase entidade) {
		// TODO Implementar Método
		
	}

	@Override
	public void excluir(EntidadeBase entidade) {
		// TODO Implementar Método
		
	}

	@Override
	public void inserir(EntidadeBase entidade) throws SQLException, ClassNotFoundException {
		String sql = obterSQLInsert();
		Object[] parametros = this.getParametrosInsert(entidade);

		Utils utils = new Utils();

		ResultSet resultSet = utils.obterResultSet(sql, parametros);

		if (resultSet.next()) {
			entidade.setCodigo(resultSet.getInt(COLUNA_CODIGO));
		}

		utils.finalizarResultSet(resultSet);
		
	}

	private Object[] getParametrosInsert(EntidadeBase entidade) {
		AplicacaoLink aplicacaoLink = (AplicacaoLink)entidade;
		Object[] parametros = { aplicacaoLink.getNome(), aplicacaoLink.getUrl(), aplicacaoLink.getAplicacao().getCodigo() };
		return parametros;
	}

	private String obterSQLInsert() {
		String sqlInsert = "INSERT INTO aplicacao_link(nome_entidade, url, codigo_aplicacao) VALUES (?, ?, ?) RETURNING codigo_entidade";
		return sqlInsert;
	}

	@Override
	protected void setNomeTabela() {
		this.setNomeTabela("aplicacao_link");
	}
	
	public ArrayList<AplicacaoLink> obterAplicacoesPerfilUsuario(Usuario usuario) {
		return null;		
	}

	public AplicacaoLink getPorAplicacao(Classe aplicacao) throws SQLException, ClassNotFoundException {
		AplicacaoLink aplicacaoLink =
			new AplicacaoLink();
		
		String sql = obterSQLTodos() + " WHERE codigo_aplicacao = ? ";
		
		Utils utils = new Utils();
		
		ResultSet resultSet = utils.obterResultSet(sql, aplicacao.getCodigo());
		
		if (resultSet.next())
		{
			aplicacaoLink= (AplicacaoLink)this.obterEntidade(resultSet);
		}
		
		utils.finalizarResultSet(resultSet);
		
		return aplicacaoLink;
	}
}
