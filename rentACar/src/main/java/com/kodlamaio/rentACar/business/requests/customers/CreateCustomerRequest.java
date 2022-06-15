package com.kodlamaio.rentACar.business.requests.customers;

import javax.persistence.Column;
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
public class CreateCustomerRequest {
	private int id;
	private String firstName;
	private String lastName;
	private String identity;
	private String eMail;
	private String password;
	private int birthYear;
	private int minfindeksScore;

}
