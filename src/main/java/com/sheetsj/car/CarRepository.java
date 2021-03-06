package com.sheetsj.car;

import java.util.List;

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
	
	public List<Car> findByModel(String model) {
		try {
			return entityManager.createQuery("select c from Car c where c.model = :model", Car.class)
					.setParameter("model", model).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
	}

	
}
