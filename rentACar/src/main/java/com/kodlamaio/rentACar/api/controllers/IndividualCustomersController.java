package com.kodlamaio.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.individualCustomers.CreateIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.DeleteIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.UpdateIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.ReadIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomersController {

	IndividualCustomerService individualCustomerService;

	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		
		this.individualCustomerService = individualCustomerService;
	}

	@PostMapping("/add")
	private Result add(@RequestBody @Valid CreateIndividualCustomersRequest createIndividualCustomersRequest)
			throws NumberFormatException, RemoteException {
		return this.individualCustomerService.add(createIndividualCustomersRequest);
	}

	@PostMapping("/update")
	private Result update(@RequestBody @Valid UpdateIndividualCustomersRequest updateIndividualCustomersRequest)
			throws NumberFormatException, RemoteException {
		return this.individualCustomerService.update(updateIndividualCustomersRequest);
	}

	@PostMapping("/delete")
	private Result delete(@RequestBody  DeleteIndividualCustomersRequest deleteIndividualCustomersRequest) {
		return this.individualCustomerService.delete(deleteIndividualCustomersRequest);
	}
	
	@RequestMapping(path="individualcustomerss",method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE} )
	
	
	private DataResult<List<GetAllIndividualCustomerResponse>> getAll() {
		return this.individualCustomerService.getAll();
	}

	@GetMapping("/getbyid")
	private DataResult<ReadIndividualCustomerResponse> getById(@RequestParam int id) {
		return this.individualCustomerService.getById(id);
	}

}
