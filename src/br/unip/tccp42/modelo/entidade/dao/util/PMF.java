package br.unip.tccp42.modelo.entidade.dao.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {

	private static final PersistenceManagerFactory pmiInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	private PMF() {
	}
	
	public static PersistenceManagerFactory get(){
		return pmiInstance;
	}

}
