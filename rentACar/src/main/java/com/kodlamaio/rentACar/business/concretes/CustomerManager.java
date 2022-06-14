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
import com.kodlamaio.rentACar.business.response.additionalItems.ListAdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.cars.ListCarResponse;
import com.kodlamaio.rentACar.business.response.customers.CustomerResponse;
import com.kodlamaio.rentACar.business.response.customers.ListCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.entitites.concretes.Car;
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
	public DataResult<CustomerResponse> getById(int id) {
		Customer customer = this.customerRepository.findByid(id);
		CustomerResponse response = this.modelMapperService.forResponse().map(customer, CustomerResponse.class);
		return new SuccessDataResult<CustomerResponse>(response);
	}

	@Override
	public DataResult<List<ListCustomerResponse>> getAll() {
		List<Customer> customers = this.customerRepository.findAll();
		List<ListCustomerResponse> response = customers.stream()
				.map(customer -> this.modelMapperService.forResponse().map(customer, ListCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListCustomerResponse>>(response);

	}

	@Override
	public DataResult<List<ListCustomerResponse>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Customer> customers = this.customerRepository.findAll(pageable).getContent();
		List<ListCustomerResponse> response = customers.stream()
				.map(customer -> this.modelMapperService.forResponse().map(customer, ListCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListCustomerResponse>>(response);
	}

}
