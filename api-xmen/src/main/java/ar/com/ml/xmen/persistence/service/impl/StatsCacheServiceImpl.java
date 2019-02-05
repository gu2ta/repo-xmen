package ar.com.ml.xmen.persistence.service.impl;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import ar.com.ml.xmen.persistence.exception.PersistenceException;
import ar.com.ml.xmen.persistence.service.StatsCacheService;
import ar.com.ml.xmen.beans.MutantStats;
import ar.com.ml.xmen.persistence.dao.HumanDao;

@Service("statsCacheService")
public class StatsCacheServiceImpl implements StatsCacheService {
	
	@Autowired
	private HumanDao humanDao;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
	@CachePut(value="stats")
	public MutantStats putCache(MutantStats stats) {
		return stats;
	}
	
	@Override
	@CachePut(value="stats")
	public MutantStats recalculateCache(boolean swMutant) throws PersistenceException {
		MutantStats elementStats = this.findCache();
		
		elementStats.setCount_human_dna(elementStats.getCount_human_dna()+1);
		if (swMutant) elementStats.setCount_mutant_dna(elementStats.getCount_mutant_dna()+1);
		elementStats.recalculateRatio();
		
		return elementStats;
	}
	
	@SuppressWarnings("unchecked")
    public MutantStats findCache() throws PersistenceException {
    	Cache cache = cacheManager.getCache("stats");
		ConcurrentHashMap<MutantStats, MutantStats> cacheMap = (ConcurrentHashMap<MutantStats, MutantStats>) cache.getNativeCache();
		
		if (cacheMap.size() > 0) {
			Iterator<MutantStats> itr = cacheMap.values().iterator();
			
			if (itr.hasNext()) {
				MutantStats elementCache = itr.next();
				return elementCache;
			}
		} else {
			try {
				MutantStats elementDB = humanDao.getCountsMutant();
				// resguardo del elemento MutantStats en el cache, para el caso de ser invocado directamente del RecruitmentController desde /stats
				cacheManager.getCache("stats").put(1, elementDB);
				return elementDB;
			} catch (Exception e) {
				throw new PersistenceException(e.getMessage(), e.getCause());
			}
		}
		return null;
    }

}
