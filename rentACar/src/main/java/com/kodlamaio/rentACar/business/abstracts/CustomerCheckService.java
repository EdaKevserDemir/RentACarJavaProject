package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;

import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.CreateIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.UpdateIndividualCustomersRequest;
import com.kodlamaio.rentACar.entitites.concretes.Customer;
import com.kodlamaio.rentACar.entitites.concretes.IndividualCustomer;

public interface CustomerCheckService {

	boolean checkIfRealPerson(CreateIndividualCustomersRequest createIndividualCustomersRequest)
			throws NumberFormatException, RemoteException;

	boolean checkIfRealPerson(UpdateIndividualCustomersRequest updateIndividualCustomersRequest)
			throws NumberFormatException, RemoteException;

}
