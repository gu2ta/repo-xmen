package ar.com.ml.xmen.persistence.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ar.com.ml.xmen.persistence.exception.PersistenceException;
import ar.com.ml.xmen.persistence.dao.HumanDao;

@Component
public class HumanCacheTask {
	
	@Autowired
	private HumanDao humanDao;
	
	/**
	 * Tarea que se ejecuta una vez por segundo para insertar el cache guardado en la base de datos.
	 * @throws PersistenceException 
	 */
	@Scheduled(fixedRate = 1000)
	public void reportCurrentTime() throws PersistenceException {
		humanDao.saveHumans();
	}

}
