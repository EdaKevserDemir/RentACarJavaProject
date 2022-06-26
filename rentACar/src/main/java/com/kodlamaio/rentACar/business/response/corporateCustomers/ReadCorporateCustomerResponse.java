package com.kodlamaio.rentACar.business.response.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadCorporateCustomerResponse {
	private int id;
	private String companyName;
	private String taxNumber;
}
