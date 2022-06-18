package com.kodlamaio.rentACar.business.requests.invoices;

import com.kodlamaio.rentACar.entitites.concretes.RentalDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
	private int id;
	private String invoiceNumber;
	private RentalDetail rentalDetail;

}
