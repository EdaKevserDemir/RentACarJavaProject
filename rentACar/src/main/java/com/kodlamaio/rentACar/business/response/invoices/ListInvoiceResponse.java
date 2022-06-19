package com.kodlamaio.rentACar.business.response.invoices;

import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListInvoiceResponse {
	private int id;
	private String invoiceNumber;
	private int rentalDetailId;
}
