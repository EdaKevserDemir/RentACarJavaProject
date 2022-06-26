package com.kodlamaio.rentACar.business.response.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCorporateCustomerResponse {
	private int id;
	private String companyName;

	private String taxNumber;
}
