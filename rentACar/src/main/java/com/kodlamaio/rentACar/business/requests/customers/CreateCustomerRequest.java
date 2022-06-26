package com.kodlamaio.rentACar.business.requests.customers;

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
	private String email;
	private String password;
	private int birthYear;
	private int minfindeksScore;

}
