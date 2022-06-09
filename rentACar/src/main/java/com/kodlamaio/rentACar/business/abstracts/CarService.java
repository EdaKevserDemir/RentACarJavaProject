package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entitites.concretes.Car;

public interface CarService {
	
	Result add(CreateCarRequest createCarRequest);
	void update(CreateCarRequest createCarRequest);
	void delete(CreateCarRequest createCarRequest);
	List<Car>getAll();
	Car getById(int id);

}
