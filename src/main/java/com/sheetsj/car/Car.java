package com.sheetsj.car;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sheetsj.account.Account;

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
	private Make make;
	
	private String model;
	private String description;

	public Long getId() {
		return id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
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
