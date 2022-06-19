package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceResponse;
import com.kodlamaio.rentACar.business.response.invoices.ListInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
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
	@PostMapping("/delete")
	private Result delete(@RequestBody DeleteInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.delete(deleteInvoiceRequest);
	}
	@GetMapping("/getById")
	private DataResult<InvoiceResponse> getById(@RequestParam int id) {
		return this.invoiceService.getById(id);
	}
	
	@GetMapping("/getAll")
	private DataResult<List<ListInvoiceResponse>> getAll() {
		return this.invoiceService.getAll();
	}

}
