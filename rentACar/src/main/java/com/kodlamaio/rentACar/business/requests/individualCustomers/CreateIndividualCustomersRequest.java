package com.kodlamaio.rentACar.business.requests.individualCustomers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIndividualCustomersRequest {
	@NotNull
	@Size(min = 2, max = 50)
	private String firstName;
	@NotNull
	@Size(min = 2, max = 50)
	private String lastName;
//	@Pattern(regexp = "[0-9]{11}", message = "Length must be 11")
	private String identity;
//	@Pattern(regexp =  "^(?=.{1,64}@)[\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)@" 
//	        + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)(\\.[\\p{L}]{2,})$", message="Write according to the rules name@domain.com")
	private String email;
	  //en az bir büyük harf, bir küçük harf ve sayıdan oluşan parola için
//	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=]).{8,}$", message = "Conditions do not meet")
  	private String password;
	@NotNull
	private int birthYear;
	@NotNull
	private int minfindeksScore;

	private String customerNumber;
	
//	@Pattern(regexp="^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
	private String phoneNumber;
}
