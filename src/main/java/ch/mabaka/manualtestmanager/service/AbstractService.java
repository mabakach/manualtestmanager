package ch.mabaka.manualtestmanager.service;

import java.util.List;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import ch.mabaka.manualtestmanager.persistence.entities.authorization.User;
import ch.mabaka.manualtestmanager.persistence.operations.AbstractOperation;
import jakarta.transaction.Transactional;

public abstract class AbstractService <T extends AbstractEntity>{


	protected abstract AbstractOperation<T> getOperations();
	
	@Transactional
	public T findById(Long sysid) {
		return (T) getOperations().findById(sysid);
	}

	@Transactional
	public T save(T entity) {
		return getOperations().save(entity);
	}
	
	@Transactional
	public T saveAndDetach(final T entity) {
		return getOperations().saveAndDetach(entity);
	}

	@Transactional
	public void delete(T entity) {
		getOperations().delete(entity);
	}
	
	@Transactional
	public List<T> findAll() {
		return getOperations().findAll();
	}
}
