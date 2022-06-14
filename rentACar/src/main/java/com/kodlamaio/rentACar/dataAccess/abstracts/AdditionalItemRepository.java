package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.business.requests.additionalItems.CreateAddionalItemRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entitites.concretes.AdditionalItem;

public interface AdditionalItemRepository extends JpaRepository<AdditionalItem, Integer>{

	AdditionalItem findById(int id);

}
