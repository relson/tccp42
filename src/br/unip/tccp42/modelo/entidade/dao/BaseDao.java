package br.unip.tccp42.modelo.entidade.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import br.unip.tccp42.CRUD;
import br.unip.tccp42.modelo.EntidadeBase;
import br.unip.tccp42.modelo.entidade.dao.util.PMF;

public abstract class BaseDao<T extends EntidadeBase> implements CRUD<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unip.tccp42.CRUD#obterTodos()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> obterTodos() throws Exception {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();
			T obj = (T) ((Class<T>) ((ParameterizedType) this.getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0])
					.newInstance();

			String query = "SELECT FROM " + obj.getClass().getName();

			List<T> list = (List<T>) pm.newQuery(query).execute();

			tx.commit();
			return list;

		} catch (Exception e) {
			throw new Exception("Erro ao tentar obter todos.\n"
					+ e.getMessage());
		} finally {

			if (tx.isActive()) {
				tx.commit();
			}

			pm.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unip.tccp42.CRUD#inserir(br.unip.tccp42.modelo.EntidadeBase)
	 */
	@Override
	public void inserir(T objetoInserir) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			pm.makePersistent(objetoInserir);

		} finally {
			pm.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unip.tccp42.CRUD#obterPorCodigo(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T obterPorCodigo(Long codigo) throws Exception {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {

			T obj = (T) ((Class<T>) ((ParameterizedType) this.getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0])
					.newInstance();

			obj.setCodigo(codigo);

			return (T) pm.getObjectById(obj);

		} finally {
			pm.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unip.tccp42.CRUD#obterPorNome(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> obterPorNome(String nome) throws Exception {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			T obj = (T) ((Class<T>) ((ParameterizedType) this.getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0])
					.newInstance();
			Query query = pm.newQuery(obj.getClass());
			query.setFilter("nome == nomeParam");
			// query.setOrdering("hireDate desc");
			query.declareParameters("String nomeParam");

			return (List<T>) query.execute(nome);

		} finally {
			pm.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unip.tccp42.CRUD#atualizar(br.unip.tccp42.modelo.EntidadeBase)
	 */
	@Override
	public void atualizar(T objetoAtualizar) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			pm.makePersistent(objetoAtualizar);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unip.tccp42.CRUD#excluir(br.unip.tccp42.modelo.EntidadeBase)
	 */
	@Override
	public void excluir(T objetoExcluir) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.deletePersistent(objetoExcluir);
		} finally {
			pm.close();
		}
	}
}
