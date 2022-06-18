package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.response.cars.CarResponse;
import com.kodlamaio.rentACar.business.response.cars.GetAllCarResponse;
import com.kodlamaio.rentACar.business.response.cars.ListCarResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
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
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
		
	}
	
	@PostMapping("/update")
	private Result update(@RequestBody UpdateCarRequest updateCarRequest) {
	return	this.carService.update(updateCarRequest);
	}
	@PostMapping("/delete")
	private Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}

	@GetMapping("/getall")
	private DataResult<List<ListCarResponse>> getAll() {
		return carService.getAll();
	
	}
	@GetMapping("/getbyid")
	private DataResult<CarResponse> getById(@RequestParam int id) {
		return carService.getById(id);
	}

}
