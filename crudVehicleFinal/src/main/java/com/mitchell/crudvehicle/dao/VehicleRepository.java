package com.mitchell.crudvehicle.dao;


//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.mitchell.crudvehicle.model.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{
	
	 Vehicle findByMake(String Make);
	 
	 Vehicle findByModel(String Model);
	 
	 Vehicle findByYear(Integer Year);
}
