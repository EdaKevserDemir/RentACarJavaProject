package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.response.cars.GetAllCarResponse;
import com.kodlamaio.rentACar.business.response.cars.ReadCarResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entitites.concretes.Brand;
import com.kodlamaio.rentACar.entitites.concretes.Car;
import com.kodlamaio.rentACar.entitites.concretes.Color;

//Id,brandCount

//TODO: StateControl

@Service
public class CarManager implements CarService {

	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private BrandRepository brandRepository;
	private ColorRepository colorRepository;

	@Autowired
	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService,
			BrandRepository brandRepository, ColorRepository colorRepository) {
		super();
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
		this.brandRepository = brandRepository;
		this.colorRepository = colorRepository;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		checkIfBrandCount(createCarRequest.getBrandId());
		checkIfExistColorId(createCarRequest.getColorId());
		checkIfExistBrandId(createCarRequest.getBrandId());
		checkIfExistCarPlate(createCarRequest.getCarPlate());

		// Car car = this.modelMapperService.forRequest().map(createCarRequest,
		// Car.class);
		Car car = Car.builder().carPlate(createCarRequest.getCarPlate()).dailyPrice(createCarRequest.getDailyPrice())
				.build();
		car.setState(1);
		this.carRepository.save(car);
		return new SuccessResult("CAR.ADDED");

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {

		checkIfExistCarId(updateCarRequest.getId());
		checkIfExistBrandId(updateCarRequest.getBrandId());
		// updateIfSameBrandId(updateCarRequest.getId(), updateCarRequest.getBrandId());
		checkIfExistColorId(updateCarRequest.getColorId());

		Car updateToCar = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		checkIfExistCarPlate(updateToCar.getCarPlate());
		updateToCar.setState(1);

		this.carRepository.save(updateToCar);
		return new SuccessResult("CAR.UPDATED");
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		checkIfExistCarId(deleteCarRequest.getId());
		carRepository.deleteById(deleteCarRequest.getId());
		return new SuccessResult("CAR.DELETED");
	}

	@Override
	public DataResult<List<GetAllCarResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, GetAllCarResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCarResponse>>(response);
	}

	@Override
	public DataResult<ReadCarResponse> getById(int id) {
		checkIfExistCarId(id);
		Car car = this.carRepository.findById(id);
		ReadCarResponse response = this.modelMapperService.forResponse().map(car, ReadCarResponse.class);
		return new SuccessDataResult<ReadCarResponse>(response);
	}

	// Count brand,If max 5 not add
	private void checkIfBrandCount(int id) {

		List<Car> cars = carRepository.findByBrandId(id);
		if (cars.size() > 4) {
			throw new BusinessException("ERROR.CAR.ADDED");
		}

	}

//	private void updateIfSameBrandId(int carId, int brandId) {
//		Car car = this.carRepository.findById(carId);
//		Brand brand = this.brandRepository.findById(brandId);
//		if (car.getBrand() == brand) {
//			checkIfBrandCount(brandId);
//
//		}
//	}

	// checking for brandId
	private void checkIfExistCarId(int id) {
		Car car = this.carRepository.findById(id);
		if (car == null) {
			throw new BusinessException("CAR.ID.NOT.FOUND");
		}

	}

	private void checkIfExistBrandId(int id) {
		Brand brand = this.brandRepository.findById(id);
		if (brand == null) {
			throw new BusinessException("BRAND.ID.NOT.FOUND");
		}

	}

	private void checkIfExistColorId(int id) {
		Color color = this.colorRepository.findById(id);
		if (color == null) {
			throw new BusinessException("COLOR.ID.NOT.FOUND");
		}
	}

	private void checkIfExistCarPlate(String carPlate) {
		Car car = this.carRepository.findByCarPlate(carPlate);
		if (car.getCarPlate() == carPlate) {
			throw new BusinessException(carPlate);
		} else {

			car.setCarPlate(carPlate);
		}
	}

}