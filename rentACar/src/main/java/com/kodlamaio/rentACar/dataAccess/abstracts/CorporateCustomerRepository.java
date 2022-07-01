package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entitites.concretes.CorporateCustomer;
import com.kodlamaio.rentACar.entitites.concretes.IndividualCustomer;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, Integer>{
	CorporateCustomer findById(int id);
	CorporateCustomer findByEmail(String email);
	CorporateCustomer findByTaxNumber(String taxNumber);
	CorporateCustomer findByCompanyName(String companyName);

	
}
