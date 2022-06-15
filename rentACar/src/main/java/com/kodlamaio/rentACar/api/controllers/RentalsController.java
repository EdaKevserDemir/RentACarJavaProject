package com.kodlamaio.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.ListRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.RentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entitites.concretes.Rental;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

	RentalService rentalService;

	public RentalsController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateRentalRequest createRentalRequest) throws NumberFormatException, RemoteException {
		return this.rentalService.add(createRentalRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);

	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteRentalRequest deleteRentalRequest) {
		return this.rentalService.delete(deleteRentalRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<ListRentalResponse>> getAll() {
		return this.rentalService.getAll();

	}
	
	@GetMapping("/getbyid")
	public DataResult<RentalResponse>getId(@RequestBody int  id){
		return this.rentalService.getById(id);
	}
			
}
