package com.kodlamaio.rentACar.business.requests.colors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateColorRequest {

	private int id;
	@NotBlank
	@Size(min=2,max=50)
	private String name;
	
}
