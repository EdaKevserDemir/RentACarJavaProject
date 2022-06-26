package com.kodlamaio.rentACar.business.response.invoices;

import java.util.Date;
import java.util.List;

import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.entitites.concretes.AdditionalItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoiceResponse {
	private int id;
	private String invoiceNumber;
	private int rentalId;
	private int additionalId;
	private boolean state;
	private Date invoiceDate;
	private String carNumberPlate;
	private String carDescription;
	private List<AdditionalItem> additionalItems;	
	private String userFirstName;	
	private double totalPrice;

}
