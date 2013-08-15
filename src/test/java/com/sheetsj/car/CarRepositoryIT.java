package com.sheetsj.car;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.sheetsj.config.PersistenceConfig;
import com.sheetsj.config.RootConfig;
import com.sheetsj.manufacturer.Manufacturer;
import com.sheetsj.manufacturer.ManufacturerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={RootConfig.class, PersistenceConfig.class})
@ActiveProfiles("test")
public class CarRepositoryIT {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
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
