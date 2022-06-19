package com.kodlamaio.rentACar.business.response.invoices;

import com.kodlamaio.rentACar.entitites.concretes.RentalDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
	private int id;
	private String invoiceNumber;
	private int rentalDetailId;
}
