package com.kodlamaio.rentACar.business.response.individualCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadIndividualCustomerResponse {
	private int id;
	private String firstName;
	private String lastName;
	private String identity;
	private String email;
	private String password;
	private int birthYear;
	private int minfindeksScore;
}
