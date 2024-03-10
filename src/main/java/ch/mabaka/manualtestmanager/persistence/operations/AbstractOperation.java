package ch.mabaka.manualtestmanager.persistence.operations;

import java.util.List;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import ch.mabaka.manualtestmanager.service.IdentityService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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
		if (entity.getSysid() != null) {
			entity = em.merge(entity);
		}
		if (entity.getSysInsertedBy() == null || entity.getSysInsertedBy().isBlank()) {
			entity.setSysInsertedBy(identityService.getLoggedInUserName());
		}
		entity.setSysUpdatedBy(identityService.getLoggedInUserName());
		em.persist(entity);
//		em.refresh(entity);
		return entity;
	}

	public List<T> findAll() {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getMyType());
		Root<T> rootEntry = cq.from(getMyType());
		CriteriaQuery<T> all = cq.select(rootEntry);

		TypedQuery<T> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	public void delete(T entity) {
		if (entity.getSysid() != null) {
			entity = em.merge(entity);
		}
		em.remove(entity);
	}

}
