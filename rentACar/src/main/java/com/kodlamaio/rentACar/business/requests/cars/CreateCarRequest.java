package com.kodlamaio.rentACar.business.requests.cars;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
	private int id;
	@NotBlank
	
	private String description;
	
	private double dailyPrice;
	private int brandId;
	private int colorId;
	private double kilometer;
	private String carPlate;
	private int maintenanceId;
	private int state;

}
