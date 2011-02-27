package br.unip.tccp42.modelo.entidade.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import br.unip.tccp42.modelo.entidade.Usuario;
import br.unip.tccp42.modelo.entidade.dao.util.PMF;

public class UsuarioDao extends BaseDao<Usuario> {
	@SuppressWarnings("unchecked")
	public Usuario efetuarLogin(String login, String senha) throws Exception {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query query = pm.newQuery(Usuario.class);
			query.setFilter("login == loginParam && senha == senhaParam");
			// query.setOrdering("hireDate desc");
			query.declareParameters("String loginParam,String senhaParam");

			List<Usuario> usuarios = (List<Usuario>) query
					.execute(login, senha);

			if (usuarios != null && usuarios.size() > 0) {
				tx.commit();
				return usuarios.get(0);
			} else {
				throw new Exception("Usuário ou senha inválidos.");
			}
		} finally {

			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}
}
