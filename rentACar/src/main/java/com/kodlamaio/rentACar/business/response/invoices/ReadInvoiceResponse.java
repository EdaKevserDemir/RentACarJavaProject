package com.kodlamaio.rentACar.business.response.invoices;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadInvoiceResponse {
	private int id;
	private int rentalId;
	private int invoiceNumber;
	private String carNumberPlate;
	private String carDescription;
	private int additionalId;
	private String customerFirstName;
	private Date invoiceDate;
	private double totalPrice;
}
