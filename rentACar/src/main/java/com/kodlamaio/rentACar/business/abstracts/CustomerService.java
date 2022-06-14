package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.response.customers.CustomerResponse;
import com.kodlamaio.rentACar.business.response.customers.ListCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CustomerService {
	Result add(CreateCustomerRequest createCustomerRequest);

	Result update(UpdateCustomerRequest updateCustomerRequest);

	Result delete(DeleteCustomerRequest deleteCustomerRequest);

	DataResult<CustomerResponse> getById(int id);
	
	DataResult<List<ListCustomerResponse>> getAll();

}
