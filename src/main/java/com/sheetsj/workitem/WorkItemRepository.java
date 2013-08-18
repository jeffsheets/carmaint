package com.sheetsj.workitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class WorkItemRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public WorkItem save(WorkItem obj) {
		entityManager.persist(obj);
		return obj;
	}
	
	public List<WorkItem> findAll() {
		try {
			return entityManager.createQuery("select w from WorkItem w", WorkItem.class).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
	}

	
}
