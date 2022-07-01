package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.requests.addresses.CreateDifferentAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.CreateSameAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateDifferentAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateSameAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.GetAllAddressResponse;
import com.kodlamaio.rentACar.business.response.addresses.ReadAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {

	@Autowired
	private AddressService addressService;

	private AddressesController(AddressService addressService) {
		
		this.addressService = addressService;
	}

	@PostMapping("/addsameaddress")
	private Result addSameAddress(@RequestBody @Valid CreateSameAddressRequest createSameAddressRequest) {
		return this.addressService.addSameAddress(createSameAddressRequest);
	}

	@PostMapping("/adddifferentaddress")
	private Result addDifferentAddress(
			@RequestBody @Valid CreateDifferentAddressRequest createDifferentAddressRequest) {
		return this.addressService.addDifferentAddress(createDifferentAddressRequest);
	}

	@PostMapping("/updatesameaddress")
	private Result updateSameAddress(@RequestBody @Valid UpdateSameAddressRequest updateSameAddressRequest) {

		return this.addressService.updateSameAddress(updateSameAddressRequest);

	}

	@PostMapping("/updatedifferentaddress")
	private Result updateDifferentAddress(
			@RequestBody @Valid UpdateDifferentAddressRequest updateDifferentAddressRequest) {
		return this.addressService.updateDifferentAddress(updateDifferentAddressRequest);

	}

	@PostMapping("/delete")
	private Result delete(@RequestBody DeleteAddressRequest deleteAddressRequest) {

		return this.addressService.delete(deleteAddressRequest);
	}

	@GetMapping("/getbyid")
	private DataResult<ReadAddressResponse> getById(int id) {

		return addressService.getById(id);

	}
	@GetMapping("/getall")
	private DataResult<List<GetAllAddressResponse>> getAll() {

		return this.addressService.getAll();

	}
}