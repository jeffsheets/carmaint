package com.sheetsj.manufacturer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "manufacturer")
public class Manufacturer implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	public Manufacturer() {
		//default
	}
	
	public Manufacturer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

}
