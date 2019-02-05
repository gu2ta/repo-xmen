package ar.com.ml.xmen.persistence.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ar.com.ml.xmen.persistence.exception.PersistenceException;
import ar.com.ml.xmen.beans.MutantStats;
import ar.com.ml.xmen.persistence.dao.HumanDao;

@Component
public class StatsCacheTask {
	
	@Autowired
	private HumanDao humanDao;
	
	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * Tarea que se ejecuta luego de construir el cacheManager donde se encuentra el cache de estadisticas (stats), dicha tarea sirve para que una vez
	 * que se inicie la API, el cache stats sea precargado con las estadisticas de los registros de la tabla de la BD xmen_human.
	 * @throws PersistenceException
	 */
    @PostConstruct
    public void init() throws PersistenceException {
    	MutantStats stats = humanDao.getCountsMutant();
		cacheManager.getCache("stats").put(1, stats);
    }
    
    /**
	 * Tarea que servira en casos eventuales que la base de datos sea manipulada.
	 * Si no se realiza esta tarea periodicamente el cache podria estar desactualizado, si se da dicha situacion.
	 * Otra opcion podria ser que el clear cache sea ejecutado de manera externa, posteriormente a la manipulacion de la BD de manera externa.
	 * Periodicidad: 1 hora
	 * @throws PersistenceException 
	 */
	@Scheduled(fixedRate = 3600000)
	public void refreshCacheDBScheduled() throws PersistenceException {
		cacheManager.getCache("stats").clear();
	}

}
