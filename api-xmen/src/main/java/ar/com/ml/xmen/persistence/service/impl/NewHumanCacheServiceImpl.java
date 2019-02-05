package ar.com.ml.xmen.persistence.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import ar.com.ml.xmen.persistence.service.NewHumanCacheService;
import ar.com.ml.xmen.persistence.entity.Human;

@Service("newHumanCacheService")
public class NewHumanCacheServiceImpl implements NewHumanCacheService {
	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
	@CachePut(value="new_humans")
	public Human addCache(Human human) {
		return human;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Human> getListCache() {
		Cache cache = cacheManager.getCache("new_humans");
		ConcurrentHashMap<Human, Human> cacheMap = (ConcurrentHashMap<Human, Human>) cache.getNativeCache();
		List<Human> humanList = new ArrayList<Human>();
		
		if(cacheMap.size() > 0) {
			
			Iterator<Human> itr = cacheMap.values().iterator();
			
			while(itr.hasNext()) {
				Human element = itr.next();
				humanList.add(element);
			}
		}
		
		return humanList;
	}
	
	public void clearCache() {
		Cache cache = cacheManager.getCache("new_humans");
		cache.clear();
	}

}
