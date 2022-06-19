package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceResponse;
import com.kodlamaio.rentACar.business.response.invoices.ListInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface InvoiceService {

	Result add(CreateInvoiceRequest createInvoiceRequest);
	Result update(UpdateInvoiceRequest updateInvoiceRequest);
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
	DataResult<List<ListInvoiceResponse>> getAll();
	DataResult<InvoiceResponse> getById(int id);
	
	
	
}
