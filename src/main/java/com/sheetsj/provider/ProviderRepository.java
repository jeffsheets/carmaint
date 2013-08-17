package com.sheetsj.provider;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ProviderRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Provider save(Provider obj) {
		entityManager.persist(obj);
		return obj;
	}
	
	public List<Provider> findAll() {
		try {
			return entityManager.createQuery("select p from Provider p", Provider.class).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
	}

	
}
