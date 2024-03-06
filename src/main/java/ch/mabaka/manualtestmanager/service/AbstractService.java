package ch.mabaka.manualtestmanager.service;

import java.util.List;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import ch.mabaka.manualtestmanager.persistence.operations.AbstractOperation;

public abstract class AbstractService <T extends AbstractEntity>{


	protected abstract AbstractOperation<T> getOperations();
	
	public T findById(Long sysid) {
		return (T) getOperations().findById(sysid);
	}

	public T save(T entity) {
		return getOperations().save(entity);
	}

	public void delete(T entity) {
		getOperations().delete(entity);
	}
	
	public List<T> findAll() {
		return getOperations().findAll();
	}
}
