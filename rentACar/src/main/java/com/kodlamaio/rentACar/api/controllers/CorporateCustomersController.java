package com.kodlamaio.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.CreateCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.DeleteCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.UpdateCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.response.corporateCustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporatecustomers")
public class CorporateCustomersController {
	CorporateCustomerService corporateCustomerService;

	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}

	@PostMapping("/add")
	private Result add(@RequestBody @Valid CreateCorporateCustomersRequest createCorporateCustomersRequest)
			throws NumberFormatException, RemoteException {
		return this.corporateCustomerService.add(createCorporateCustomersRequest);
	}

	@PostMapping("/update")
	private Result update(@RequestBody @Valid UpdateCorporateCustomersRequest updateCorporateCustomersRequest) {
		return this.corporateCustomerService.update(updateCorporateCustomersRequest);
	}

	@PostMapping("/delete")
	private Result delete(@RequestBody @Valid DeleteCorporateCustomersRequest deleteCorporateCustomersRequest) {
		return this.corporateCustomerService.delete(deleteCorporateCustomersRequest);
	}

	@GetMapping("/getall")
	private DataResult<List<GetAllCorporateCustomerResponse>> getAll() {
		return this.corporateCustomerService.getAll();
	}

	@GetMapping("/getbyid")
	private DataResult<ReadCorporateCustomerResponse> getById(int id) {
		return this.corporateCustomerService.getById(id);
	}

}
