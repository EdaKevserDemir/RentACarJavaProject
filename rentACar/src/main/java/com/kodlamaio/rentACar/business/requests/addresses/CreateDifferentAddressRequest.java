package com.kodlamaio.rentACar.business.requests.addresses;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDifferentAddressRequest {

	
	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min=2,max=100)
	private String contactAddress;
	@NotBlank
	@NotNull
	@NotEmpty
	@Size(min=2,max=100)
	private String invoiceAddress;
	@NotNull
	private int customerId;
}
