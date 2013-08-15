package com.sheetsj.manufacturer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ManufacturerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Manufacturer save(Manufacturer make) {
		entityManager.persist(make);
		return make;
	}
	
	public Manufacturer findByName(String name) {
		try {
			return entityManager.createQuery("select m from Manufacturer m where m.name = :name", Manufacturer.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
	}

	
}
