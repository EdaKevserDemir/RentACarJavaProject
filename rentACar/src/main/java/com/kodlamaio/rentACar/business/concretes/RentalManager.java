package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
//		Rental rental = new Rental();
//		Car car = this.carRepository.findById(createRentalRequest.getCarId());
//		car.setId(createRentalRequest.getCarId());
//		rental.setPickupDate(createRentalRequest.getPickupDate());
//		rental.setReturnDate(createRentalRequest.getReturnDate());
//		// rental.setTotalDays(createRentalRequest.getTotalDays());
//		rental.setTotalPrice(createRentalRequest.getTotalPrice());
		LocalDate start = createRentalRequest.getPickupDate();
		LocalDate end = createRentalRequest.getReturnDate();

		Rental rental = this.rentalRepository.findById(createRentalRequest.getId());
		Car car = this.carRepository.findById(createRentalRequest.getCarId());
		car.setState(3);

		rental.setTotalDays(calculateTotalDays(start, end));

		int days = rental.getTotalDays();

		double totalPrice = car.getDailyPrice() * days;

		// rental.setTotalPrice(calculateTotalPrice(carRepository.findById(createCarRequest.getId()),
		// days));
		rental.setTotalPrice(totalPrice);
		rental.setCar(car);

		this.rentalRepository.save(rental);
		return new SuccessResult("Rental eklendi");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rentalToupdate = rentalRepository.findById(updateRentalRequest.getId());
//		rentalToupdate.setPickupDate(updateRentalRequest.getPickupDate());
//		rentalToupdate.setReturnDate(updateRentalRequest.getReturnDate());
//		rentalToupdate.setTotalDays(updateRentalRequest.getTotalDays());
//		rentalToupdate.setTotalPrice(updateRentalRequest.getTotalPrice());
		Car car = carRepository.findById(updateRentalRequest.getCarId());
		car.setId(updateRentalRequest.getCarId());
//		rentalToupdate.setCar(car);
//
		LocalDate date = LocalDate.now();

		if (date.equals(rentalToupdate.getReturnDate()) || date.isBefore(rentalToupdate.getPickupDate())
				|| date.isAfter(rentalToupdate.getReturnDate())) {
			car.setState(1);

		} else {
			car.setState(3);
		}

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

	public Integer calculateTotalDays(LocalDate pickupDate, LocalDate returnDate) {
		int totalDay = 0;

		return totalDay = Period.between(pickupDate, returnDate).getDays();

	}

	public double calculateTotalPrice(CreateCarRequest createCarRequest, int days) {
		double totalPrice = 0;

		return totalPrice = createCarRequest.getDailyPrice() * days;
	}

}
