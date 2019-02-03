package ar.com.ml.xmen.persistence.dao;

import ar.com.ml.xmen.persistence.entity.Human;
import ar.com.ml.xmen.persistence.exception.PersistenceException;

public interface HumanDao {

	/**
	 * Guarda objetos Human en el cache.
	 * @param human
	 */
	public Human addCacheHuman(Human human);
	
	/**
	 * Inserta objetos Human en la base de datos tomados desde el cache.
	 */
	public void saveHumans() throws PersistenceException;
	
	/**
	 * Obtiene un objeto Human con la cantidad total y la cantidad de mutantes en la base de datos.
	 */
	public Human getCountsMutant() throws PersistenceException;
	
}
