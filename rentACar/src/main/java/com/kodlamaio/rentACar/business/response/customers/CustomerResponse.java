package com.kodlamaio.rentACar.business.response.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
	private int id;
	private String firstName;
	private String lastName;
	private String identity;
	private String eMail;
	private String password;
	private int birthYear;
}
