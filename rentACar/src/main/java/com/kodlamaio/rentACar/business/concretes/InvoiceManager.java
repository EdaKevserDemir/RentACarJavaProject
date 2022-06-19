package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceResponse;
import com.kodlamaio.rentACar.business.response.invoices.ListInvoiceResponse;
import com.kodlamaio.rentACar.business.response.rentalDetails.ListRentalDetailsResponse;
import com.kodlamaio.rentACar.business.response.rentalDetails.RentalDetailResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalDetailRepository;
import com.kodlamaio.rentACar.entitites.concretes.Invoice;
import com.kodlamaio.rentACar.entitites.concretes.RentalDetail;
@Service
public class InvoiceManager implements InvoiceService {
	@Autowired
    InvoiceRepository invoiceRepository;
	@Autowired
	ModelMapperService modelMapperService;
	
	@Autowired
	RentalDetailRepository rentalDetailRepository; 
	
	
	
	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		RentalDetail rentalDetail=this.rentalDetailRepository.findById(createInvoiceRequest.getRentalDetailId());
		invoice.setRentalDetail(rentalDetail);
	
		invoiceRepository.save(invoice);
	    
		return  new SuccessResult("Invoice added");
	
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		this.invoiceRepository.deleteById(deleteInvoiceRequest.getId());
		return new SuccessResult("deleted");
	}

	@Override
	public DataResult<List<ListInvoiceResponse>> getAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();

		List<ListInvoiceResponse> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse().map(invoice, ListInvoiceResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListInvoiceResponse>>(response);
	}

	@Override
	public DataResult<InvoiceResponse> getById(int id) {
		
		  Invoice invoice = this.invoiceRepository.findById(id);
		  InvoiceResponse invoiceResponse = this.modelMapperService.forResponse().map(invoice, InvoiceResponse.class);
		  return new SuccessDataResult<InvoiceResponse>(invoiceResponse);
	}

}