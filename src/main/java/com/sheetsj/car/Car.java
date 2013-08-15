package com.sheetsj.car;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sheetsj.account.Account;
import com.sheetsj.manufacturer.Manufacturer;

@SuppressWarnings("serial")
@Entity
@Table(name = "car")
public class Car implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private Integer year;
	
	@ManyToOne(optional = false)
	@JoinColumn
	private Manufacturer make;
	
	private String model;
	private String description;
	
	public Car(Integer year, Manufacturer make, String model, String description) {
		this.year = year;
		this.make = make;
		this.model = model;
		this.description = description;
	}
	
	public Car() {
		//default
	}

	public Long getId() {
		return id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Manufacturer getMake() {
		return make;
	}

	public void setMake(Manufacturer make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
