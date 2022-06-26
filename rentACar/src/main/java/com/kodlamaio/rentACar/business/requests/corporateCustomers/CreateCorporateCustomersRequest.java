package com.kodlamaio.rentACar.business.requests.corporateCustomers;

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
public class CreateCorporateCustomersRequest {
	@NotBlank
	@NotEmpty
	@Size(min = 2, max = 50)
	private String companyName;
	@NotNull
	private String taxNumber;

}
