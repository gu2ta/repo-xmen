package ar.com.ml.xmen.persistence.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ar.com.ml.xmen.persistence.exception.PersistenceException;
import ar.com.ml.xmen.persistence.service.NewHumanCacheService;
import ar.com.ml.xmen.persistence.service.StatsCacheService;
import ar.com.ml.xmen.persistence.dao.HumanDao;
import ar.com.ml.xmen.persistence.entity.Human;

@Component
public class NewHumanCacheTask {
	
	@Autowired
	private HumanDao humanDao;
	
	@Autowired
	private NewHumanCacheService newHumanCacheService;
	
	@Autowired
	private StatsCacheService statsCacheService;
	
	/**
	 * Tarea que se ejecuta una vez por segundo para insertar el cache guardado en la base de datos.
	 * Periodicidad: 1 segundo
	 * @throws PersistenceException 
	 */
	@Scheduled(fixedRate = 1000)
	public void reportCurrentTime() throws PersistenceException {
		List<Human> humanList = newHumanCacheService.getListCache();
		boolean success = humanDao.create(humanList);
		
		if (success) {
			boolean isMutant = false;
			for (Human human : humanList) {
				if (human.getIsMutant() == 1) isMutant = true;
				statsCacheService.recalculateCache(isMutant);
			}
		}
		
		newHumanCacheService.clearCache();
	}

}
