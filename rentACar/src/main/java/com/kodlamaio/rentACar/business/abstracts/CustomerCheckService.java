package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;

import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.entitites.concretes.Customer;

public interface CustomerCheckService {

	boolean checkIfRealPerson(CreateCustomerRequest createCustomerRequest) throws NumberFormatException, RemoteException;
	
}
