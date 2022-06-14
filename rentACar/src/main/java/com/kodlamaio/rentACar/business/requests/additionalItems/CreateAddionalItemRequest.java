package com.kodlamaio.rentACar.business.requests.additionalItems;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import com.kodlamaio.rentACar.entitites.concretes.Additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddionalItemRequest {

	private int id;
	private String description;
	private String name;
	private double dailyPrice;

}
