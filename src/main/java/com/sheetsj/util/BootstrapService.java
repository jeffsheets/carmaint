package com.sheetsj.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheetsj.car.Car;
import com.sheetsj.car.CarRepository;
import com.sheetsj.manufacturer.Manufacturer;
import com.sheetsj.manufacturer.ManufacturerRepository;
import com.sheetsj.provider.Provider;
import com.sheetsj.provider.ProviderRepository;
import com.sheetsj.workitem.WorkItem;
import com.sheetsj.workitem.WorkItemRepository;


@Service
public class BootstrapService {
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private WorkItemRepository workItemRepository;
	
	@PostConstruct	
	protected void initialize() throws ParseException {
		//Bootstrap a bunch of data for this sample app
		if (manufacturerRepository.findByName("Chevrolet") == null) {
			Manufacturer chevy = manufacturerRepository.save(new Manufacturer("Chevrolet"));
			manufacturerRepository.save(new Manufacturer("BMW"));
			manufacturerRepository.save(new Manufacturer("Tesla"));

			Car traverse = carRepository.save(new Car(2011, chevy, "Traverse", "LT FWD 3.6L V6 DOHC 24V"));
			Car malibu = carRepository.save(new Car(2012, chevy, "Malibu", "1LT 2.4L L4 DOHC 16V FFV"));
			
			Provider tiresPlus = providerRepository.save(new Provider("Tires Plus", "Papillion"));
			Provider jiffyLube = providerRepository.save(new Provider("Jiffy Lube", "Omaha"));
			
			DateFormat df = new SimpleDateFormat("MM/DD/yyyy");
			workItemRepository.save(new WorkItem(malibu, df.parse("12/23/2011"), "Oil Change", jiffyLube, 13049L, 55.38, ""));
			workItemRepository.save(new WorkItem(traverse, df.parse("02/02/2012"), "New Tires", tiresPlus, 39123L, 523.99, "Includes free tire changes for life"));
		}
	}

}
