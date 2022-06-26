package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entitites.concretes.Brand;
import com.kodlamaio.rentACar.entitites.concretes.Customer;
import com.kodlamaio.rentACar.entitites.concretes.IndividualCustomer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	


}
