package com.kodlamaio.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CustomerService;
import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.response.customers.ReadCustomerResponse;
import com.kodlamaio.rentACar.business.response.customers.GetAllCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/customers")

public class CustomersController {

	CustomerService customerService;

	public CustomersController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateCustomerRequest createCustomerRequest) throws NumberFormatException, RemoteException {
		return this.customerService.add(createCustomerRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
		return this.customerService.update(updateCustomerRequest);

	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCustomerRequest deleteCustomerRequest) {
		return this.customerService.delete(deleteCustomerRequest);

	}

	@GetMapping("/getbyid")
	public DataResult<ReadCustomerResponse>getById(@RequestParam int id){
		return this.customerService.getById(id);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllCustomerResponse>>getAll(){
		return this.customerService.getAll();
	}
	
	@GetMapping("/getallbypage")
	public DataResult<List<GetAllCustomerResponse>>getAll(@RequestParam int pageNo,int pageSize){
		return this.customerService.getAll(pageNo,pageSize);
	}
	
	
}
