package com.kodlamaio.rentACar.business.response.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarResponse {
	private int id;
	private String description;
	private int minFindeksScore;
}
