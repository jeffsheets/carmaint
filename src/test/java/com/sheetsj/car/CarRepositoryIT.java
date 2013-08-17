package com.sheetsj.car;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sheetsj.manufacturer.Manufacturer;
import com.sheetsj.manufacturer.ManufacturerRepository;
import com.sheetsj.test.BaseIntegrationTestCase;

public class CarRepositoryIT extends BaseIntegrationTestCase {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	/**
	 * Demonstrating old-school JUnit Style assertEquals statements
	 * 
	 * Notice the 'backwards' thinking of putting the expected value before the actual value
	 */
	@Test
	public void testFindByModel() {
		Manufacturer make = new Manufacturer("JunitMotors");
		make = manufacturerRepository.save(make);
		
		Car car = new Car(2013, make, "JunitModel", "LT FWD 3.6L V6 DOHC 24V");
		car = carRepository.save(car);
		
		List<Car> results = carRepository.findByModel("JunitModel");
		
		assertEquals(1, results.size());
		
		Car result = results.get(0);
		assertEquals(2013, result.getYear().intValue());
		assertEquals("JunitMotors", result.getMake().getName());
		assertEquals(make.getId(), result.getMake().getId());
		assertEquals(car.getDescription(), result.getDescription());
	}

}
