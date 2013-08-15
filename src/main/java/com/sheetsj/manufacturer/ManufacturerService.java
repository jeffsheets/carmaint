package com.sheetsj.manufacturer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManufacturerService {
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@PostConstruct	
	protected void initialize() {
		//Bootstrap a bunch of data
		if (manufacturerRepository.findByName("Chevrolet") == null) {
			manufacturerRepository.save(new Manufacturer("Chevrolet"));
			manufacturerRepository.save(new Manufacturer("BMW"));
		}
	}

}
