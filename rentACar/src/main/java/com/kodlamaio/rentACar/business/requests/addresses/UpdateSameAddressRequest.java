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
public class UpdateSameAddressRequest {

		@NotNull
		private int id;
		@NotNull
		@NotBlank
		@NotEmpty
		@Size(min = 2, max = 100)
		private String contactAddress;
		
		@NotNull
		private int customerId;
}
