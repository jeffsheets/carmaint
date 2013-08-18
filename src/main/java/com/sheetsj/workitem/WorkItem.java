package com.sheetsj.workitem;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sheetsj.car.Car;
import com.sheetsj.provider.Provider;

@SuppressWarnings("serial")
@Entity
@Table(name = "work_item")
public class WorkItem implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private Car car;
	private Date workDate;
	private String description;
	private Provider provider;
	private Long mileage;
	private Double cost;
	private String notes;

	public WorkItem(Car car, Date workDate, String description, Provider provider, Long mileage, Double cost, String notes) {
		this.car = car;
		this.workDate = workDate;
		this.description = description;
		this.provider = provider;
		this.mileage = mileage;
		this.cost = cost;
		this.notes = notes;
	}
	
	public WorkItem() {
		//default constructor
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkItem other = (WorkItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Long getMileage() {
		return mileage;
	}

	public void setMileage(Long mileage) {
		this.mileage = mileage;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getId() {
		return id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
