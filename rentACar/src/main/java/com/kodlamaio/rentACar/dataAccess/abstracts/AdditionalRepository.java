package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entitites.concretes.Additional;

public interface AdditionalRepository extends JpaRepository<Additional,Integer>{
	Additional findById(int id);

}
