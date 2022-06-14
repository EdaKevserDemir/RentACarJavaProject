package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.maintenances.ListMaintenanceResponse;
import com.kodlamaio.rentACar.business.response.maintenances.MaintenanceResponse;
import com.kodlamaio.rentACar.business.response.rentals.ListRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.RentalResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entitites.concretes.Car;
import com.kodlamaio.rentACar.entitites.concretes.Maintenance;
import com.kodlamaio.rentACar.entitites.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	RentalRepository rentalRepository;

	CarRepository carRepository;

	ModelMapperService modelMapperService;

	public RentalManager(RentalRepository rentalRepository, CarRepository carRepository,
			ModelMapperService modelMapperService) {

		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;

	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
	if (checkIfCarState(createRentalRequest.getCarId())) {

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
	
		if (this.checkIfDatesAreCorrect(rental.getPickupDate(), rental.getReturnDate())) {
			long dayDifference = (rental.getReturnDate().getTime()
					- rental.getPickupDate().getTime());
			long time = TimeUnit.DAYS.convert(dayDifference, TimeUnit.MILLISECONDS);
			Car car = this.carRepository.findById(createRentalRequest.getCarId());
			car.setState(3);
			rental.setTotalDays((int)time);
			double totalPrice = car.getDailyPrice() * time;
			rental.setTotalPrice(totalPrice);

			this.rentalRepository.save(rental);
			return new SuccessResult("CAR.RENTED");
		}

	}
	return new ErrorResult("CAR.NOT.RENT");

}
	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rentalToupdate = rentalRepository.findById(updateRentalRequest.getId());

		Car car = carRepository.findById(updateRentalRequest.getCarId());
		car.setId(updateRentalRequest.getCarId());
//		rentalToupdate.setCar(car);
//
		LocalDate date = LocalDate.now();
//
//		if (date.equals(rentalToupdate.getReturnDate()) || date.isBefore(rentalToupdate.getPickupDate())
//				|| date.isAfter(rentalToupdate.getReturnDate())) {
//			car.setState(1);
//
//		} else {
//			car.setState(3);
//		}

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

	private boolean checkIfDatesAreCorrect(Date pickupDate, Date returnDate) {
		if (!pickupDate.before(returnDate) )  {
			return false;
		} 
		return true;
		
	}

	private boolean checkIfCarState(int id) {
		Car car = this.carRepository.findById(id);
		if (car.getState() == 1) {
			return true;
		}
		return false;

	}

	public double calculateTotalPrice(CreateCarRequest createCarRequest, int days) {
		double totalPrice = 0;

		return totalPrice = createCarRequest.getDailyPrice() * days;
	}

}
