package ar.com.ml.xmen.persistence.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ar.com.ml.xmen.persistence.exception.PersistenceException;
import ar.com.ml.xmen.persistence.factory.HibernateUtil;

@Repository("genericDAO")
public abstract class GenericDAOImpl<T extends Serializable> {

	private Class<T> clazz = getDomainClass();

	protected abstract Class<T> getDomainClass();
	
	private Session session;
	
	public final void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(long id) throws PersistenceException {
		try {
			session = getCurrentSession();
			T objectGeneric = session.get(clazz, id);
			session.close();
			
			return (T) objectGeneric;
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() throws PersistenceException {
		try {
			session = getCurrentSession();
			List<T> objectListGeneric = session.createQuery("from " + clazz.getName()).list();
			session.close();
			
			return objectListGeneric;
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e.getCause());
		}
	}

	@SuppressWarnings("finally")
	public boolean create(T entity) throws PersistenceException {
		boolean success = false;
		try {
			session = getCurrentSession();
			session.save(entity);
			session.close();
			success = true;
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e.getCause());
		} finally {
			return success;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean create(List<T> entities) throws PersistenceException {
		boolean success = false;
		try {
			session = getCurrentSession();
			
			for (T entity : entities) {
				session.save(entity);
			}
			
			session.close();
			success = true;
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e.getCause());
		} finally {
			return success;
		}
		
	}

	@SuppressWarnings("finally")
	public boolean update(T entity) throws PersistenceException {
		boolean success = false;
		try {
			session = getCurrentSession();
			session.update(entity);
			session.close();
			success = true;
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e.getCause());
		} finally {
			return success;
		}
	}

	@SuppressWarnings("finally")
	public boolean delete(T entity) throws PersistenceException {
		boolean success = false;
		try {
			session = getCurrentSession();
			session.delete(entity);
			session.close();
			success = true;
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e.getCause());
		} finally {
			return success;
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteById(long entityId) throws PersistenceException {
		boolean success = false;
		try {
			session = getCurrentSession();
			T objectGeneric = this.findOne(entityId);
			success = delete(objectGeneric);
			session.close();
			success = true;
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e.getCause());
		} finally {
			return success;
		}
	}

	protected final Session getCurrentSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}
