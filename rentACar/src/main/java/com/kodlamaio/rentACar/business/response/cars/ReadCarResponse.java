package com.kodlamaio.rentACar.business.response.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadCarResponse {
	private int id;
	private String description;
	
	private double dailyPrice;
	private int brandId;
	private int colorId;
	private double kilometer;
	private String carPlate;
	private int maintenanceId;
	private int state;

}
