package com.mitchell.crudvehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitchell.crudvehicle.dao.VehicleRepository;
import com.mitchell.crudvehicle.model.Vehicle;

//This Java file contains function to get vehicle by attributes

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository repo;
	
	
	public List<Vehicle> listAll(){
		
		return repo.findAll();
	}
	
	
	public Vehicle save(Vehicle vehicle) {
		Vehicle vehiclereturned = repo.save(vehicle);
		
		return vehiclereturned;
	}
	
	
	public Vehicle getById(Integer Id) {
		Vehicle vehicle=null;
		try {
			vehicle=repo.findById(Id).get();
			return vehicle;
	
		}
		
		catch(Exception e) {
			return vehicle;
		}
	}
	
	
	public Vehicle getByMake(String Make) {
		Vehicle vehicle=null;
		try {
			vehicle=repo.findByMake(Make);
			return vehicle;
	
		}
		
		catch(Exception e) {
			return vehicle;
		}
	}
	
	
	public Vehicle getByModel(String Model) {
		Vehicle vehicle=null;
		try {
			vehicle=repo.findByModel(Model);
			return vehicle;
	
		}
		
		catch(Exception e) {
			return vehicle;
		}
	}
	
	
	public Vehicle getByYear(Integer Year) {
		Vehicle vehicle=null;
		try {
			vehicle=repo.findByYear(Year);
			return vehicle;
	
		}
		
		catch(Exception e) {
			return vehicle;
		}
	}
	
	
	public void delete(Integer Id) {
		repo.deleteById(Id);
	}
}
