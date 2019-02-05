package ar.com.ml.xmen.persistence.service;

import java.util.List;

import ar.com.ml.xmen.persistence.entity.Human;

public interface NewHumanCacheService {
	
	/**
	 * Guarda objetos Human en el cache.
	 * @param human
	 */
	public Human addCache(Human human);
	
	/**
	 * Devuelve una lista objetos Human desde el cache.
	 */
	public List<Human> getListCache();
	
	/**
	 * Limpia todos los objetos Human en el cache.
	 */
	public void clearCache();

}
