package com.kodlamaio.rentACar.business.response.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
	private int id;
	private String contactAddress;
	private String invoiceAddress;
	private int customerId;
}
