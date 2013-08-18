package com.sheetsj.workitem;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sheetsj.car.Car;
import com.sheetsj.car.CarRepository;
import com.sheetsj.manufacturer.Manufacturer;
import com.sheetsj.manufacturer.ManufacturerRepository;
import com.sheetsj.provider.Provider;
import com.sheetsj.provider.ProviderRepository;
import com.sheetsj.test.IntegrationTestBaseClass;

public class WorkItemRepositoryIT extends IntegrationTestBaseClass {
	@Autowired
	private ProviderRepository providerRepository;

	@Autowired
	private WorkItemRepository workItemRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	private CarRepository carRepository;

	@Test
	public void testFindAllAndSave() {
		List<WorkItem> original = workItemRepository.findAll();

		Manufacturer make = manufacturerRepository.save(new Manufacturer("JUnitMake"));
		Car car = carRepository.save(new Car(2013, make, "JunitModel", "LT FWD 3.6L V6 DOHC 24V"));
		Provider provider = providerRepository.save(new Provider("Junit Tire Shop", "Shadow Lake"));
		
		WorkItem workItem = new WorkItem(car, new Date(), "Oil Change", provider, 18123L, 60.12, "Took 1:45 so got free oil change coupon");
		workItem = workItemRepository.save(workItem);
		
		List<WorkItem> result = workItemRepository.findAll();
		
		assertThat(result, not(nullValue()));
		assertThat(result, hasSize(original.size() + 1));
		
		assertThat(result, hasItem(workItem));
	}

}
