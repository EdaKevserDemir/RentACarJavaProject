package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import com.kodlamaio.rentACar.business.requests.individualCustomers.CreateIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.DeleteIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.UpdateIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.ReadIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	Result add(CreateIndividualCustomersRequest createIndividualCustomersRequest)
			throws NumberFormatException, RemoteException;

	Result update(UpdateIndividualCustomersRequest updateIndividualCustomersRequest);

	Result delete(DeleteIndividualCustomersRequest deleteIndividualCustomersRequest);

	DataResult<ReadIndividualCustomerResponse> getById(int id);

	DataResult<List<GetAllIndividualCustomerResponse>> getAll();

	DataResult<List<GetAllIndividualCustomerResponse>> getAll(int pageNo, int pageSize);
}
