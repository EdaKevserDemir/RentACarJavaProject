package com.kodlamaio.rentACar.api.controllers;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.AddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {

	@Autowired
	AddressService addressService;

	@PostMapping("/addsameaddress")
	private Result addSameAddress(@RequestBody CreateAddressRequest createAddressRequest) {
		return this.addressService.addSameAddress(createAddressRequest);
	}
	@PostMapping("/adddifferentaddress")
	private Result addDifferentAddress(@RequestBody CreateAddressRequest createAddressRequest) {
		return this.addressService.addDifferentAddress(createAddressRequest);
	}

	@PostMapping("/updatedifferentaddress")
	private Result updateDifferentAddress(@RequestBody UpdateAddressRequest updateAddressRequest) {

		return this.addressService.updateDifferentAddress(updateAddressRequest);

	}
	@PostMapping("/updatesameaddress")
	private Result updateSameAddress(@RequestBody UpdateAddressRequest updateAddressRequest) {

		return this.addressService.updateSameAddress(updateAddressRequest);

	}

	@PostMapping("/delete")
	private Result delete(@RequestBody DeleteAddressRequest deleteAddressRequest) {

		return this.addressService.delete(deleteAddressRequest);
	}
	
	@GetMapping("/getbyid")
	private DataResult<AddressResponse>getById(int id){
		
		return null;
		
	}
}