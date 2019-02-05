package ar.com.ml.xmen.persistence.service;

import ar.com.ml.xmen.beans.MutantStats;
import ar.com.ml.xmen.persistence.exception.PersistenceException;

public interface StatsCacheService {
	
	/**
	 * Guarda un objeto MutantStats en el cache.
	 * @param stats
	 */
	public MutantStats putCache(MutantStats stats);
	
	/**
	 * Recalcula las estadisticas en el cache.
	 * @param boolean
	 */
	public MutantStats recalculateCache(boolean swMutant) throws PersistenceException;
	
	/**
	 * Busca las estadisticas en el cache y si no esta en este, lo busca en la base de datos.
	 */
    public MutantStats findCache() throws PersistenceException;

}
