package com.sheetsj.car;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sheetsj.manufacturer.Manufacturer;
import com.sheetsj.manufacturer.ManufacturerRepository;
import com.sheetsj.test.IntegrationTestBaseClass;

public class CarRepositoryIT extends IntegrationTestBaseClass {

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
	
	/**
	 * Now using Hamcrest for the same matchers
	 */
	@Test
	public void testFindByModel_withHamcrest() {
		Manufacturer make = new Manufacturer("JunitMotors");
		make = manufacturerRepository.save(make);

		Car car = new Car(2013, make, "JunitModel", "LT FWD 3.6L V6 DOHC 24V");
		car = carRepository.save(car);
		
		List<Car> results = carRepository.findByModel("JunitModel");
		
		assertEquals(1, results.size());
		
		Car result = results.get(0);
		assertThat(result.getYear().intValue(), is(equalTo(2013)));
		assertThat(result.getMake().getName(), is("JunitMotors"));
		assertThat(result.getMake().getId(), is(make.getId()));
		assertThat(result.getDescription(), is(car.getDescription()));
		
		//containsString
		//equalToIgnoringCase
	}

}
