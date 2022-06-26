package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entitites.concretes.Color;

public interface ColorRepository extends JpaRepository<Color, Integer> {

	Color findByName(String name);
	Color findById(int id);
}
