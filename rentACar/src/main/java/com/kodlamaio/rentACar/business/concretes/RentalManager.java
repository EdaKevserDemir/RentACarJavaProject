package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.FindeksScoreCheckService;
import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.cars.GetAllCarResponse;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entitites.concretes.Brand;
import com.kodlamaio.rentACar.entitites.concretes.Car;
import com.kodlamaio.rentACar.entitites.concretes.Customer;
import com.kodlamaio.rentACar.entitites.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	RentalRepository rentalRepository;

	CarRepository carRepository;

	ModelMapperService modelMapperService;

	CustomerRepository customerRepository;

	FindeksScoreCheckService findeksScoreCheckService;
	double totalPrice = 0;

	public RentalManager(RentalRepository rentalRepository, CarRepository carRepository,
			ModelMapperService modelMapperService, CustomerRepository customerRepository,
			FindeksScoreCheckService findeksScoreCheckService) {
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
		this.customerRepository = customerRepository;
		this.findeksScoreCheckService = findeksScoreCheckService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) throws NumberFormatException, RemoteException {
		checkIfCarId(createRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		Customer customer = this.customerRepository.findById(createRentalRequest.getCustomerId()).get();
		Car car = this.carRepository.findById(createRentalRequest.getCarId());
		checkIfAvailableState(rental);
		checkFindexMinValue(car.getMinFindeksScore(), customer.getIdentity());
		rental.setTotalPrice(calculateTotalPrice(rental, car));
		rentalRepository.save(rental);
		return new Result(true, "CAR.RENTED");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) throws NumberFormatException, RemoteException {

		checkIfExistRentalId(updateRentalRequest.getId());

		checkIfCarId(updateRentalRequest.getCarId());

		Rental updateToRental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

		Car car = this.carRepository.findById(updateRentalRequest.getCarId());

		Customer customer = this.customerRepository.findById(updateRentalRequest.getCustomerId()).get();

		checkFindexMinValue(car.getMinFindeksScore(), customer.getIdentity());

		updateToRental.setTotalPrice(calculateTotalPrice(updateToRental, car));

		this.rentalRepository.save(updateToRental);
		return new SuccessResult("update edildi");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {

		checkIfExistRentalId(deleteRentalRequest.getId());
		this.rentalRepository.deleteById(deleteRentalRequest.getId());

		return new SuccessResult("silindi");
	}

	@Override
	public DataResult<List<GetAllRentalResponse>> getAll() {

		List<Rental> rentals = this.rentalRepository.findAll();

		List<GetAllRentalResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllRentalResponse>>(response);
	}

	@Override
	public DataResult<ReadRentalResponse> getById(int id) {
		checkIfExistRentalId(id);
		return new SuccessDataResult<ReadRentalResponse>(
				this.modelMapperService.forResponse().map(id, ReadRentalResponse.class));
	}

	// CheckDatecontrol

	private long calculateTotalDay(Rental rental) {

		long dayDifference = (rental.getReturnDate().getTime() - rental.getPickupDate().getTime());

		if (dayDifference < 0) {
			throw new BusinessException("Invalid date");

		}
		long time = TimeUnit.DAYS.convert(dayDifference, TimeUnit.MILLISECONDS);
		rental.setTotalDays((int) time);
		return time;

	}

	private double calculateTotalPrice(Rental rental, Car car) {
		long time = calculateTotalDay(rental);
		rental.setTotalDays((int) time);
		double totalPrice = car.getDailyPrice() * time;

		if (rental.getPickCity().getId() != rental.getReturnCity().getId()) {
			totalPrice = totalPrice + 750;
		}
		return totalPrice;
	}

	// FindeksControl
	private boolean checkFindexMinValue(int score, String tc) throws NumberFormatException, RemoteException {
		boolean state = false;
		if (findeksScoreCheckService.checkIfFindeksScore(tc) > score) {
			state = true;
		} else {
			state = false;
			throw new BusinessException("Insufficient.Score");
		}
		return state;
	}

//checkMaintenanceControl
	private void checkIfAvailableState(Rental rental) {

		Car currentCar = this.carRepository.findById(rental.getCar().getId());
		if (currentCar.getState() == 1) {
			currentCar.setState(3);

		} else {
			throw new BusinessException("NOT.AVAILABLE");
		}
	}

//CheckRentalControl
	private void checkIfExistRentalId(int id) {
		Rental rental = this.rentalRepository.findById(id);
		if (rental == null) {
			throw new BusinessException("ID.NOT.FOUND");
		}

	}

	private void checkIfCarId(int id) {
		Car car = this.carRepository.findById(id);
		if (car == null) {
			throw new BusinessException("ID.NOT.FOUND");
		}

	}

	@Override
	public DataResult<List<GetAllCarResponse>> getAllByFindeksScore() {
		List<Rental> rentals = this.rentalRepository.findAll();

		List<GetAllCarResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllCarResponse.class)).sorted()
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllCarResponse>>(response);
	}

}
