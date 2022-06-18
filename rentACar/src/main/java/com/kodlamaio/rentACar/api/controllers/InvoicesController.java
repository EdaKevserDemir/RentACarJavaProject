package com.kodlamaio.rentACar.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;
@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {
	
	@Autowired
	InvoiceService invoiceService; 
	
	@PostMapping("/add")
	private Result add(@RequestBody CreateInvoiceRequest createInvoiceRequest ) {
		return this.invoiceService.add(createInvoiceRequest);
		
		
		
	}

}
