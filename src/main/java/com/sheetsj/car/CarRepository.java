package com.sheetsj.car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class CarRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Car save(Car car) {
		entityManager.persist(car);
		return car;
	}
	
	@Transactional
	public Make save(Make make) {
		entityManager.persist(make);
		return make;
	}
	
	public Make findMakeByName(String name) {
		try {
			return entityManager.createQuery("select m from Make m where m.name = :name", Make.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
	}

	
}
