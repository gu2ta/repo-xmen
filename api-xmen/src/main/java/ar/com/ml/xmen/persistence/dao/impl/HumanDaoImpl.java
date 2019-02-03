package ar.com.ml.xmen.persistence.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Repository;

import ar.com.ml.xmen.persistence.dao.HumanDao;
import ar.com.ml.xmen.persistence.entity.Human;
import ar.com.ml.xmen.persistence.exception.PersistenceException;
import ar.com.ml.xmen.persistence.factory.HibernateUtil;

@Repository("humanDAO")
public class HumanDaoImpl implements HumanDao {

	@Autowired
	private CacheManager cacheManager;
	
	@Override
	@CachePut(value="new_humans")
	public Human addCacheHuman(Human human) {
		return human;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void saveHumans() throws PersistenceException {
		Cache cache = cacheManager.getCache("new_humans");
		ConcurrentHashMap<Human, Human> cacheMap = (ConcurrentHashMap<Human, Human>) cache.getNativeCache();
		
		if(cacheMap.size() > 0) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			try {
				Iterator<Human> itr = cacheMap.values().iterator();
				
				while(itr.hasNext()) {
					Human element = itr.next();
					session.save(cacheMap.get(element));
				}
			} catch (Exception e) {
				throw new PersistenceException(e.getMessage(), e.getCause());
			}
			
			session.close();
			cache.clear();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Human getCountsMutant() throws PersistenceException {
		
		Human humanoRespuesta = new Human();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.getCriteriaBuilder();
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Human.class);
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.alias(Projections.groupProperty("isMutant"), "isMutant"))
				.add(Projections.count("id"), "countGeneric"));
		criteria.addOrder(Order.asc("isMutant"));
		
		criteria.setResultTransformer(Transformers.aliasToBean(Human.class));
		
		List<Human> humanList = criteria.getExecutableCriteria(session).list();
		
		session.close();
		
		for (Human humano : humanList) {
			humanoRespuesta.setCountGeneric(humanoRespuesta.getCountGeneric() + humano.getCountGeneric());
			if (humano.getIsMutant() == 1) humanoRespuesta.setCountIsMutant(humano.getCountGeneric());
		}
		
		return humanoRespuesta;
	}

}
