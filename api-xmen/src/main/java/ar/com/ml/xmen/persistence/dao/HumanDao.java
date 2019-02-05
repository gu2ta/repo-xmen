package ar.com.ml.xmen.persistence.dao;

import ar.com.ml.xmen.beans.MutantStats;
import ar.com.ml.xmen.persistence.entity.Human;
import ar.com.ml.xmen.persistence.exception.PersistenceException;

public interface HumanDao extends GenericDAO<Human> {
	
	/**
	 * Obtiene un objeto Human con la cantidad total y la cantidad de mutantes en la base de datos.
	 */
	public MutantStats getCountsMutant() throws PersistenceException;
	
}
