package ar.com.ml.xmen.persistence.dao;

import java.util.List;

import ar.com.ml.xmen.persistence.exception.PersistenceException;

public interface GenericDAO<Entity> {

	public Entity findOne(long id) throws PersistenceException;
	
	public List<Entity> findAll() throws PersistenceException;

	public boolean create(Entity entity) throws PersistenceException;
	
	public boolean create(List<Entity> entities) throws PersistenceException;

	public boolean update(Entity entity) throws PersistenceException;

	public boolean delete(Entity entity) throws PersistenceException;

	public boolean deleteById(long entityId) throws PersistenceException;
}
