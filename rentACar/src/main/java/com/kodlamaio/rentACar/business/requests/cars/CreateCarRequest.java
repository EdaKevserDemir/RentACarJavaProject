package com.kodlamaio.rentACar.business.requests.cars;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
	@NotNull
	private int id;
	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min=2,max=15)
	private String description;
	private double dailyPrice;
	private int brandId;
	private int colorId;
	private double kilometer;
	private String carPlate;
	private int maintenanceId;
	private int state;
	private int minFindeksScore;

}
