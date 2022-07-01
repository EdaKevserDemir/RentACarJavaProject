package com.kodlamaio.rentACar.business.requests.corporateCustomers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomersRequest {
//	@Pattern(regexp =  "^(?=.{1,64}@)[\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)@" 
//	        + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)(\\.[\\p{L}]{2,})$", message="Write according to the rules name@domain.com")
	private String email;
	
//	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=]).{8,}$", message = "Conditions do not meet")
  	private String password;
	
	private String customerNumber;
	
	@NotBlank
	@NotEmpty
	@Size(min = 2, max = 50)
	private String companyName;
	
	@NotNull
	private String taxNumber;

	private String phoneNumber;
}
