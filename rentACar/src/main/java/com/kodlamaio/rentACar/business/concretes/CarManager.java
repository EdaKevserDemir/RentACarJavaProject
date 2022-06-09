package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
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

	public CarManager(CarRepository carRepository) {

		this.carRepository = carRepository;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		if (!checkBrandCount(createCarRequest.getBrandId())) {

			Car car = new Car();

			
		
			
			car.setDescription(createCarRequest.getDescription());
			car.setDailyPrice(createCarRequest.getDailyPrice());
			car.setKilometer(createCarRequest.getKilometer());
			car.setCarPlate(createCarRequest.getCarPlate());
			
			
			car.setState(1);
		//	car.setMaintenances(car.getMaintenances());
			car.setState(car.getState());
			
			Brand brand = new Brand();
			brand.setId(createCarRequest.getBrandId());
			car.setBrand(brand);
			Color color = new Color();
			color.setId(createCarRequest.getColorId());
			car.setColor(color);
			
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
	public void update(CreateCarRequest createCarRequest) {
		Car car = new Car();
		car.setDailyPrice(createCarRequest.getDailyPrice());
		car.setDescription(createCarRequest.getDescription());

		Brand brand = new Brand();
		brand.setId(createCarRequest.getBrandId());
		car.setBrand(brand);

		Color color = new Color();
		color.setId(createCarRequest.getColorId());
		car.setColor(color);

		new SuccessResult("BRAND.UPDATED");

	}

	@Override
	public void delete(CreateCarRequest createCarRequest) {

		this.carRepository.delete(getById(createCarRequest.getId()));

	}

	@Override
	public List<Car> getAll() {

		return carRepository.findAll();
	}

	@Override
	public Car getById(int id) {

		return carRepository.getById(id);
	}

}
