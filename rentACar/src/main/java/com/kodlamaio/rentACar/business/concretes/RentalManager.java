package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.RentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entitites.concretes.Car;
import com.kodlamaio.rentACar.entitites.concretes.Rental;
@Service
public class RentalManager implements RentalService {

	RentalRepository rentalRepository;

	CarRepository carRepository;
	public RentalManager(RentalRepository rentalRepository,CarRepository carRepository) {

		this.rentalRepository = rentalRepository;
		this.carRepository=carRepository;
		
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Rental rental=new Rental();
		
		rental.setPickupDate(createRentalRequest.getPickupDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setTotalDays(createRentalRequest.getTotalDays());
		rental.setTotalPrice(createRentalRequest.getTotalPrice());
		Car car=new Car();
		car.setId(createRentalRequest.getCarId());
		rental.setCar(car);
		this.rentalRepository.save(rental);
		return new SuccessResult("Rental eklendi");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rentalToupdate=rentalRepository.findById(updateRentalRequest.getId());
		rentalToupdate.setPickupDate(updateRentalRequest.getPickupDate());
		rentalToupdate.setReturnDate(updateRentalRequest.getReturnDate());
		rentalToupdate.setTotalDays(updateRentalRequest.getTotalDays());
		rentalToupdate.setTotalPrice(updateRentalRequest.getTotalPrice());
		Car car=carRepository.findById(updateRentalRequest.getCarId());
		car.setId(updateRentalRequest.getCarId());
		rentalToupdate.setCar(car);
		this.rentalRepository.save(rentalToupdate);
		return new SuccessResult("update edildi");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		this.rentalRepository.delete(rentalRepository.findById(deleteRentalRequest.getId()));
		return new SuccessResult("silindi");
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		
		return new SuccessDataResult<List<Rental>>((this.rentalRepository.findAll()),"listelendi");
	}

	@Override
	public DataResult<Rental> getById(RentalResponse rentalResponse) {
		
		return new SuccessDataResult<Rental>(this.rentalRepository.findById(rentalResponse.getId()));
	}

}