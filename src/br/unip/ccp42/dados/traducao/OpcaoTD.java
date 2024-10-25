package br.unip.ccp42.dados.traducao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.ccp42.EntidadeBase;
import br.unip.ccp42.dados.TradutorBase;
import br.unip.ccp42.dados.util.Utils;
import br.unip.ccp42.negocio.entidade.Usuario;
import br.unip.ccp42.negocio.mi.Opcao;
import br.unip.ccp42.negocio.mi.Pergunta;

public class OpcaoTD extends TradutorBase {

	private Pergunta pergunta;

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	@Override
	protected EntidadeBase obterEntidade(ResultSet resultSet)
			throws SQLException {

		Opcao opcao = new Opcao();

		opcao.setCodigo(resultSet.getInt(COLUNA_CODIGO));
		opcao.setNome(resultSet.getString(COLUNA_NOME));

		return (EntidadeBase) opcao;
	}

	@Override
	public EntidadeBase obterPorCodigo(int codigo) throws SQLException,
			ClassNotFoundException {
		Opcao opcao = new Opcao();

		String sql = obterSQLTodos();

		sql += " WHERE  codigo_entidade = ? or codigo_entidade = " + codigo;

		Utils utils = new Utils();

		ResultSet resultSet = utils.obterResultSet(sql, codigo);

		if (resultSet.next()) {
			opcao = (Opcao) this.obterEntidade(resultSet);
		}

		utils.finalizarResultSet(resultSet);

		return opcao;
	}

	@Override
	public ArrayList<EntidadeBase> obterTodos() throws SQLException,
			ClassNotFoundException {
		ArrayList<EntidadeBase> opcoes = new ArrayList<EntidadeBase>();

		String sql = obterSQLTodos();

		Utils utils = new Utils();

		ResultSet resultSet = utils.obterResultSet(sql);

		while (resultSet.next()) {
			opcoes.add(this.obterEntidade(resultSet));
		}

		utils.finalizarResultSet(resultSet);

		return opcoes;
	}

	/**
	 * ObtÃ©m as opÃ§Ãµes para a pergunta
	 * 
	 * @param codigoPergunta
	 *            CÃ³digo da Pergunta
	 * @return Lista de OpÃ§Ãµes relacionadas a pergunta
	 * @throws SQLException
	 *             erro dectedo no SQL
	 * @throws ClassNotFoundException
	 *             se nÃ£o for possÃ­vel encontrar o driver de conexÃ£o com o banco
	 *             de dados
	 */
	public ArrayList<Opcao> obterPorPergunta(int codigoPergunta)
			throws SQLException, ClassNotFoundException {
		ArrayList<Opcao> opcoes = new ArrayList<Opcao>();

		String sql = obterSQLTodos();

		sql += " WHERE  codigo_pergunta = ?";

		Utils utils = new Utils();

		ResultSet resultSet = utils.obterResultSet(sql, codigoPergunta);

		while (resultSet.next()) {
			opcoes.add((Opcao) this.obterEntidade(resultSet));
		}

		utils.finalizarResultSet(resultSet);

		return opcoes;
	}

	private String obterSQLTodos() {
		StringBuffer sbSQL = new StringBuffer();

		sbSQL.append(" SELECT ").append("	codigo_entidade, ").append(
				"   nome_entidade ").append(" FROM ").append(
				"   pergunta_opcao ");

		return sbSQL.toString();
	}

	public ArrayList<Opcao> obterPorPergunta(Pergunta pergunta)
			throws SQLException, ClassNotFoundException {
		return this.obterPorPergunta(pergunta.getCodigo());
	}

	@Override
	public void excluir(EntidadeBase entidade) throws SQLException,
			ClassNotFoundException {
		String sql = this.getSQLDelete();
		Object[] parametros = this.getParametrosDelete(entidade);

		Utils utils = new Utils();

		utils.executarComandoSQL(sql, parametros);
	}

	@Override
	public void inserir(EntidadeBase entidade) throws Exception {
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
		Opcao opcao = (Opcao) entidade;
		Object[] parametros = { opcao.getNome(), this.getPergunta().getCodigo() };
		return parametros;
	}

	private String obterSQLInsert() {
		String sqlInsert = "INSERT INTO pergunta_opcao(nome_entidade, codigo_pergunta) VALUES (?,?) RETURNING codigo_entidade";
		return sqlInsert;
	}

	@Override
	protected void setNomeTabela() {
		this.setNomeTabela("pergunta_opcao");
	}

	/**
	 * TODO Documentar método
	 * @param usuario
	 * @return
	 */
	public ArrayList<Opcao> obterPorUsuario(Usuario usuario) {

		return null;
	}

	public ArrayList<Opcao> obterPorEntidade(EntidadeBase entidade)
			throws SQLException, ClassNotFoundException {

		ArrayList<Opcao> opcoes = new ArrayList<Opcao>();

		String sql = obterSQLObterPorEntidade();

		Utils utils = new Utils();

		ResultSet resultSet = utils.obterResultSet(sql, entidade.getCodigo());

		while (resultSet.next()) {
			opcoes.add((Opcao) this.obterEntidade(resultSet));
		}

		utils.finalizarResultSet(resultSet);

		return opcoes;
	}

	private String obterSQLObterPorEntidade() {
		StringBuffer sql = new StringBuffer();

		sql
			.append(" SELECT ")
			.append("	codigo_opcao, ")
			.append("	nome_entidade, ")
			.append("	codigo_entidade")
		.append(" FROM ")
				.append(" 	entidade_pergunta_opcao_view ").append(" WHERE ")
				.append(" 	codigo_entidade = ? ");

		return sql.toString();
	}
}
