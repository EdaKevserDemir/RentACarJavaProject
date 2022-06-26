package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.kodlamaio.rentACar.business.abstracts.CustomerCheckService;
import com.kodlamaio.rentACar.business.abstracts.CustomerService;
import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.response.customers.GetAllCustomerResponse;
import com.kodlamaio.rentACar.business.response.customers.ReadCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.entitites.concretes.Customer;

@Service
public class CustomerManager implements CustomerService {

	CustomerRepository customerRepository;
	ModelMapperService modelMapperService;
	CustomerCheckService customerCheckService;

	public CustomerManager(CustomerRepository customerRepository, ModelMapperService modelMapperService,
			CustomerCheckService customerCheckService) {

		this.customerRepository = customerRepository;
		this.modelMapperService = modelMapperService;
		this.customerCheckService = customerCheckService;
	}

	@Override
	public Result add(@RequestBody CreateCustomerRequest createCustomerRequest) throws NumberFormatException, RemoteException {
		Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
		
		if(customerCheckService.checkIfRealPerson(createCustomerRequest)){
			this.customerRepository.save(customer);
			return new SuccessResult("CUSTOMER.ADDED");
		}
		
		else {
			return new ErrorResult("CUSTOMER.NOT.ADDED");
			}
	}

	@Override
	public Result update(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
		Customer customer = this.modelMapperService.forRequest().map(updateCustomerRequest, Customer.class);
		this.customerRepository.save(customer);
		return new SuccessResult("CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(@RequestBody DeleteCustomerRequest deleteCustomerRequest) {
		this.customerRepository.deleteById(deleteCustomerRequest.getId());

		return new SuccessResult("CUSTOMER.DELETED");

	}

	@Override
	public DataResult<ReadCustomerResponse> getById(int id) {
		Customer customer = this.customerRepository.findById(id).get();
		ReadCustomerResponse response = this.modelMapperService.forResponse().map(customer, ReadCustomerResponse.class);
		return new SuccessDataResult<ReadCustomerResponse>(response);
	}

	@Override
	public DataResult<List<GetAllCustomerResponse>> getAll() {
		List<Customer> customers = this.customerRepository.findAll();
		List<GetAllCustomerResponse> response = customers.stream()
				.map(customer -> this.modelMapperService.forResponse().map(customer, GetAllCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCustomerResponse>>(response);

	}

	@Override
	public DataResult<List<GetAllCustomerResponse>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Customer> customers = this.customerRepository.findAll(pageable).getContent();
		List<GetAllCustomerResponse> response = customers.stream()
				.map(customer -> this.modelMapperService.forResponse().map(customer, GetAllCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCustomerResponse>>(response);
	}

}
