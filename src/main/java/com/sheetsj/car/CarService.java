package com.sheetsj.car;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@PostConstruct	
	protected void initialize() {
		if (carRepository.findMakeByName("Chevrolet") == null) {
			carRepository.save(new Make("Chevrolet"));
			carRepository.save(new Make("BMW"));
		}
	}

}
