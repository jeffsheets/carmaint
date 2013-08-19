package com.sheetsj.workitem;

import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat;
import static spock.util.matcher.HamcrestSupport.*

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import org.springframework.transaction.annotation.Transactional

import spock.lang.Ignore;
import spock.lang.Specification
import spock.lang.Unroll

import com.sheetsj.car.Car
import com.sheetsj.car.CarRepository;
import com.sheetsj.config.PersistenceConfig
import com.sheetsj.config.RootConfig
import com.sheetsj.manufacturer.Manufacturer
import com.sheetsj.manufacturer.ManufacturerRepository;
import com.sheetsj.provider.Provider
import com.sheetsj.provider.ProviderRepository
import com.sheetsj.test.IntegrationSpecBaseClass;


class WorkItemRepositorySpecIT extends IntegrationSpecBaseClass {
	@Autowired
	private ProviderRepository providerRepository

	@Autowired
	private WorkItemRepository workItemRepository

	@Autowired
	private ManufacturerRepository manufacturerRepository

	@Autowired
	private CarRepository carRepository

	def "findAll workItems and Save"() {
		given: 'keep track of original value'
		List<WorkItem> original = workItemRepository.findAll();

		and: 'setup make, car, and provider'
		Manufacturer make = manufacturerRepository.save(new Manufacturer("JUnitMake"));
		Car car = carRepository.save(new Car(2013, make, "JunitModel", "LT FWD 3.6L V6 DOHC 24V"));
		Provider provider = providerRepository.save(new Provider("Junit Tire Shop", "Shadow Lake"));
		
		when: 'workItem is created and saved'
		WorkItem workItem = new WorkItem(car, new Date(), "Oil Change", provider, 18123L, 60.12, "Took 1:45 so got free oil change coupon");
		workItem = workItemRepository.save(workItem);
		
		and: 'all results are retrieved'
		List<WorkItem> result = workItemRepository.findAll();
		
		then: 'results are retrieved correctly'
		result.contains workItem
		
		and: 'size was incremented'
		result.size() == original.size() + 1
	}

	def "findAll workItems and Save showing how the old method works"() {
		given: 'setup make, car, and provider'
		Manufacturer make = manufacturerRepository.save(new Manufacturer("JUnitMake"));
		Car car = carRepository.save(new Car(2013, make, "JunitModel", "LT FWD 3.6L V6 DOHC 24V"));
		Provider provider = providerRepository.save(new Provider("Junit Tire Shop", "Shadow Lake"));
		
		when: 'workItem is created and saved'
		WorkItem workItem = new WorkItem(car, new Date(), "Oil Change", provider, 18123L, 60.12, "Took 1:45 so got free oil change coupon");
		workItem = workItemRepository.save(workItem);
		
		and: 'all results are retrieved'
		List<WorkItem> result = workItemRepository.findAll();
		
		then: 'results are retrieved correctly'
		result.contains workItem
		
		and: 'size was incremented'
		result.size() == old(workItemRepository.findAll()).size() + 1
	}

}
