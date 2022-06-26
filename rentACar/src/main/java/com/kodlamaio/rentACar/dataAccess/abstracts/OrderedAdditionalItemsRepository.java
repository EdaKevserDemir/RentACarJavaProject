package com.kodlamaio.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entitites.concretes.OrderedAdditionalItems;
import com.kodlamaio.rentACar.entitites.concretes.AdditionalItem;

public interface OrderedAdditionalItemsRepository extends JpaRepository<OrderedAdditionalItems,Integer>{

	List<OrderedAdditionalItems> findByRentalId(int id);
	OrderedAdditionalItems findById(int id);

}
