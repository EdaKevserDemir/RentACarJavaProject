package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entitites.concretes.CorporateCustomer;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, Integer>{
	CorporateCustomer findById(int id);
}
