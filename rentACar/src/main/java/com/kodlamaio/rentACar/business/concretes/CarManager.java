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

	@Autowired
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;

	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService) {

		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		if (!checkBrandCount(createCarRequest.getBrandId())) {

//			Car car = new Car();
//
//			car.setDescription(createCarRequest.getDescription());
//			car.setDailyPrice(createCarRequest.getDailyPrice());
//			car.setKilometer(createCarRequest.getKilometer());
//			car.setCarPlate(createCarRequest.getCarPlate());
//
//			car.setState(1);
//			// car.setMaintenances(car.getMaintenances());
//			// car.setState(car.getState());
//
//			Brand brand = new Brand();
//			brand.setId(createCarRequest.getBrandId());
//			car.setBrand(brand);
//			Color color = new Color();
//			color.setId(createCarRequest.getColorId());
//			car.setColor(color);

			Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);

			// Car car2=carRepository.getById(0)
			this.carRepository.save(car);

			return new SuccessResult("ADDED");
		}
		return new ErrorResult("eklenemedi");

	}

	private boolean checkBrandCount(int id) {
		List<Car> cars = carRepository.getByBrandId(id);
		if (cars.size() < 5) {
			return false;

		}
		return true;
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
//		Car car = new Car();
//		car.setDailyPrice(updateCarRequest.getDailyPrice());
//		car.setDescription(updateCarRequest.getDescription());
//
//		Brand brand = new Brand();
//		brand.setId(updateCarRequest.getBrandId());
//		car.setBrand(brand);
//
//		Color color = new Color();
//		color.setId(updateCarRequest.getColorId());
//		car.setColor(color);

		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);

		this.carRepository.save(car);
		return new SuccessResult("updated");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {

		this.carRepository.delete(carRepository.findById(deleteCarRequest.getId()));
		return new SuccessResult("deleted");

	}

	@Override
	public DataResult<List<ListCarResponse>> getAll() {

		// return carRepository.findAll();
		List<Car> cars = this.carRepository.findAll();

		List<ListCarResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, ListCarResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarResponse>>(response);
	}

	@Override
	public DataResult<CarResponse> getById(int id) {

		return new SuccessDataResult<CarResponse>(this.modelMapperService.forResponse().map(id, CarResponse.class));
	}

}
