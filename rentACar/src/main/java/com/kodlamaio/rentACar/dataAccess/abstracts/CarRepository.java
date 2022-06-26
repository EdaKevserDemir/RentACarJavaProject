package com.kodlamaio.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entitites.concretes.Car;
import com.kodlamaio.rentACar.entitites.concretes.Rental;

public interface CarRepository extends JpaRepository<Car, Integer>{

	List<Car> findByBrandId(int brandId);
	Car findById(int id);
	Car findByCarPlate(String carPlate);
	

}
