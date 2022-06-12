package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.response.brands.ListBrandResponse;
import com.kodlamaio.rentACar.business.response.cars.CarResponse;
import com.kodlamaio.rentACar.business.response.cars.ListCarResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.entitites.concretes.Brand;
import com.kodlamaio.rentACar.entitites.concretes.Car;
import com.kodlamaio.rentACar.entitites.concretes.Color;
import com.kodlamaio.rentACar.entitites.concretes.Maintenance;

@Service
public class CarManager implements CarService {



	private CarRepository carRepository;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService) {
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;

	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		checkIfBrandCount(createCarRequest.getBrandId());

		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setState(1);

		this.carRepository.save(car);
		return new SuccessResult("CAR.ADDED");

	}

	private void checkIfBrandCount(int id) {

		List<Car> cars = carRepository.getByBrandId(id);
		if (cars.size() > 4) {
			throw new BusinessException("ERROR:CAR.ADDED");
		}

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {

		Car carToUpdate = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carRepository.save(carToUpdate);
		return new SuccessResult("CAR.UPDATED");
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		carRepository.deleteById(deleteCarRequest.getId());
		return new SuccessResult("CAR.DELETED");
	}

	@Override
	public DataResult<List<ListCarResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<ListCarResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, ListCarResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarResponse>>(response);
	}

	@Override
	public DataResult<CarResponse> getById(int id) {
		Car car = this.carRepository.findById(id);
		CarResponse response = this.modelMapperService.forResponse().map(car,CarResponse.class);
		return new SuccessDataResult<CarResponse>(response);
	}

}