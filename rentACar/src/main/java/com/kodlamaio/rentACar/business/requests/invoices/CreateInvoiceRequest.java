package com.kodlamaio.rentACar.business.requests.invoices;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
	@NotNull
	@Size(min=4,max=10,message="Must be least 4")
	private String invoiceNumber;
	private int rentalId;
	@DateTimeFormat
	private Date invoiceDate;
	



}
