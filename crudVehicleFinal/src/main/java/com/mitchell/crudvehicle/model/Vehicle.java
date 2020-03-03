package com.mitchell.crudvehicle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
	
@Entity
public class Vehicle {
		
		@Id
		@Column(name = "Id", nullable = false)
		private int Id;
		@Column(name = "Year", nullable = false)
		private int year;
		@Column(name = "Make", nullable = false)
		private String make;
		@Column(name = "Model", nullable = false)
		private String model;
		
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public String getMake() {
			return make;
		}
		public void setMake(String make) {
			this.make = make;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		@Override
		public String toString() {
			return "Vehicle [Id=" + Id + ", Year=" + year + ", Make=" + make + ", Model=" + model + "]";
		}
}
