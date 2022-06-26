package com.kodlamaio.rentACar.business.requests.additionalItems;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddionalItemRequest {

	private int id;
	@NotBlank
	@NotNull
	@NotEmpty
	private String description;
	@NotBlank
	@NotNull
	@NotEmpty
	private String name;
	@Min(20)
	private double dailyPrice;

}
