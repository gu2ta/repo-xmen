package ar.com.ml.xmen.persistence.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import ar.com.ml.xmen.beans.MutantStats;
import ar.com.ml.xmen.persistence.dao.HumanDao;
import ar.com.ml.xmen.persistence.entity.Human;
import ar.com.ml.xmen.persistence.exception.PersistenceException;
import ar.com.ml.xmen.persistence.factory.HibernateUtil;

@Repository("humanDAO")
public class HumanDaoImpl extends GenericDAOImpl<Human> implements HumanDao {

	@SuppressWarnings("unchecked")
	@Override
	public MutantStats getCountsMutant() throws PersistenceException {
		
		Human humanDB = new Human();
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
		
		for (Human human : humanList) {
			humanDB.setCountGeneric(humanDB.getCountGeneric() + human.getCountGeneric());
			if (human.getIsMutant() == 1) humanDB.setCountIsMutant(human.getCountGeneric());
		}
		
		return new MutantStats(humanDB.getCountIsMutant(), humanDB.getCountGeneric());
	}

}
