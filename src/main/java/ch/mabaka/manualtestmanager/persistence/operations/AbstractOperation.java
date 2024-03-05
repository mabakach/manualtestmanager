package ch.mabaka.manualtestmanager.persistence.operations;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import ch.mabaka.manualtestmanager.service.IdentityService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class AbstractOperation<T extends AbstractEntity> {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	IdentityService identityService;
	
	public abstract Class<T> getMyType();
	
	public T findById(Long sysid) {
		return (T) em.find(getMyType(), sysid);
	}
	
	public T save(T entity) {
		if (entity.getSysInsertedBy() == null || entity.getSysInsertedBy().isBlank()){
			entity.setSysInsertedBy(identityService.getLoggedInUserName());
		}
		entity.setSysUpdatedBy(identityService.getLoggedInUserName());
		
		em.persist(entity);
		em.refresh(entity);
		return entity;
	}

}
