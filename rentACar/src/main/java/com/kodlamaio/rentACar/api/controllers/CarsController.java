package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entitites.concretes.Car;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	@Autowired
	private CarService carService;

	public CarsController(CarService carService) {
		
		this.carService = carService;
	}
	
	@GetMapping("/cars")
	public String cars() {
		return "cars";
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
		
	}
	
	@PostMapping("/update")
	private void update(@RequestBody CreateCarRequest createCarRequest) {
		this.carService.update(createCarRequest);
	}
	@PostMapping("/delete")
	private void delete(@RequestBody CreateCarRequest createCarRequest) {
		this.carService.delete(createCarRequest);
	}

	@GetMapping("/getall")
	private List<Car> getAll(@RequestBody CreateCarRequest createCarRequest) {
		return carService.getAll();
	
	}
	@GetMapping("/getbyid")
	private Car getById(@RequestBody CreateCarRequest createCarRequest) {
		return carService.getById(createCarRequest.getId());
	}
}
