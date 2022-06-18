package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.FindeksScoreCheckService;
import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.cars.GetAllCarResponse;
import com.kodlamaio.rentACar.business.response.maintenances.ListMaintenanceResponse;
import com.kodlamaio.rentACar.business.response.maintenances.MaintenanceResponse;
import com.kodlamaio.rentACar.business.response.rentals.ListRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.RentalResponse;
import com.kodlamaio.rentACar.core.adapters.FindeksServiceAdapter;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entitites.concretes.Car;
import com.kodlamaio.rentACar.entitites.concretes.Customer;
import com.kodlamaio.rentACar.entitites.concretes.Maintenance;
import com.kodlamaio.rentACar.entitites.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	@Autowired
	RentalRepository rentalRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	ModelMapperService modelMapperService;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	FindeksScoreCheckService findeksScoreCheckService;

	@Override
	public Result add(CreateRentalRequest createRentalRequest) throws NumberFormatException, RemoteException {

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		Customer customer = this.customerRepository.findByid(createRentalRequest.getCustomerId());
		Car car = this.carRepository.findById(createRentalRequest.getCarId());
		if (checkFindexMinValue(car.getMinFindeksScore(), customer.getIdentity())) {
			car.setState(3);
			long time = calculateTotalDay(rental);
			rental.setTotalDays((int) time);
			double totalPrice = car.getDailyPrice() * time;
			if (rental.getPickCity().getId() != rental.getReturnCity().getId()) {
				totalPrice = totalPrice + 750;
			}
			rental.setTotalPrice(totalPrice);
			rental.setCar(car);

			rentalRepository.save(rental);
			return new Result(true, "CAR.RENTED");
		} else {
			return new Result(false, "findex puanı yetersiz");
		}

	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rentalToupdate = rentalRepository.findById(updateRentalRequest.getId());

		Car car = carRepository.findById(updateRentalRequest.getCarId());
		car.setId(updateRentalRequest.getCarId());

		this.rentalRepository.save(rentalToupdate);
		return new SuccessResult("update edildi");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		this.rentalRepository.delete(rentalRepository.findById(deleteRentalRequest.getId()));
		return new SuccessResult("silindi");
	}

	@Override
	public DataResult<List<ListRentalResponse>> getAll() {

		List<Rental> rentals = this.rentalRepository.findAll();

		List<ListRentalResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, ListRentalResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListRentalResponse>>(response);
	}

	@Override
	public DataResult<RentalResponse> getById(int id) {

		return new SuccessDataResult<RentalResponse>(
				this.modelMapperService.forResponse().map(id, RentalResponse.class));
	}



	private long calculateTotalDay(Rental rental) {
		long dayDifference = (rental.getReturnDate().getTime() - rental.getPickupDate().getTime());

		long time = TimeUnit.DAYS.convert(dayDifference, TimeUnit.MILLISECONDS);
		rental.setTotalDays((int) time);
		return time;

	}

	public boolean checkFindexMinValue(int score, String tc) throws NumberFormatException, RemoteException {
		boolean state = false;
		if (findeksScoreCheckService.checkIfFindeksScore(tc) > score) {
			state = true;
		} else {
			state = false;
		}
		return state;
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
