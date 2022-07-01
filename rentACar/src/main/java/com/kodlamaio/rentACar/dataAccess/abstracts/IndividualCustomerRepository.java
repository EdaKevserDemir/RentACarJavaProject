package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.business.requests.individualCustomers.CreateIndividualCustomersRequest;
import com.kodlamaio.rentACar.entitites.concretes.IndividualCustomer;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer>{

	IndividualCustomer findById(int id);

	IndividualCustomer findByIdentity(String identity);
	
	IndividualCustomer findByEmail(String email);

	
	

}
