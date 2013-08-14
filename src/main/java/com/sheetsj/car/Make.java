package com.sheetsj.car;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "make")
public class Make implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	public Make() {
		//default
	}
	
	public Make(String name) {
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
