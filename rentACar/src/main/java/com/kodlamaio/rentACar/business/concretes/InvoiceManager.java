package com.kodlamaio.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoiceResponse;
import com.kodlamaio.rentACar.business.response.invoices.ReadInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.OrderedAdditionalItemsRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entitites.concretes.AdditionalItem;
import com.kodlamaio.rentACar.entitites.concretes.Invoice;
import com.kodlamaio.rentACar.entitites.concretes.OrderedAdditionalItems;
import com.kodlamaio.rentACar.entitites.concretes.Rental;

@Service
public class InvoiceManager implements InvoiceService {

	private InvoiceRepository invoiceRepository;

	private	ModelMapperService modelMapperService;

	private	OrderedAdditionalItemsRepository additionalRepository;

	private AdditionalItemRepository additionalItemRepository;

	private RentalRepository rentalRepository;
	


	public InvoiceManager(InvoiceRepository invoiceRepository, ModelMapperService modelMapperService,
			OrderedAdditionalItemsRepository additionalRepository, RentalRepository rentalRepository) {

		this.invoiceRepository = invoiceRepository;
		this.modelMapperService = modelMapperService;
		this.additionalRepository = additionalRepository;
		this.rentalRepository = rentalRepository;
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		// checkIfInvoiceNumber(createInvoiceRequest.getInvoice());

		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setInvoiceDate(invoice.getInvoiceDate());
		invoice.setSumTotalPrice(calculateTotalPrice(createInvoiceRequest.getRentalId()));
		// invoice.setInvoiceNumber("123plk");

		invoice.setState(1);
		this.invoiceRepository.save(invoice);

		return new SuccessResult("INVOICE.ADDED");

	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest, Invoice.class);
		invoice.setState(0);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("invoice is canceled");
	}

	@Override
	public DataResult<List<GetAllInvoiceResponse>> getAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();

		List<GetAllInvoiceResponse> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse().map(invoice, GetAllInvoiceResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllInvoiceResponse>>(response, "All Invoices");
	}

	@Override
	public DataResult<ReadInvoiceResponse> getById(int id) {

		Invoice invoice = this.invoiceRepository.findById(id).get();
		ReadInvoiceResponse invoiceResponse = this.modelMapperService.forResponse().map(invoice,
				ReadInvoiceResponse.class);
		return new SuccessDataResult<ReadInvoiceResponse>(invoiceResponse);
	}

	private double calculateTotalPrice(int rentalId) {
		Rental rental = this.rentalRepository.findById(rentalId);
		double totalPrice = rental.getTotalPrice() + allRentalAdditionalTotalPrice(rentalId);
		return totalPrice;
	}

	private double allRentalAdditionalTotalPrice(int id) {
		double totalAdditionalPrice = 0;
		List<OrderedAdditionalItems> additionals = this.additionalRepository.findByRentalId(id);
		for (OrderedAdditionalItems additional : additionals) {
			totalAdditionalPrice += additional.getTotalPrice();
		}
		return totalAdditionalPrice;
	}

	@Override
	public DataResult<List<AdditionalItem>> getAllAdditionalItems(int rentalId) {
		List<OrderedAdditionalItems> additionals = this.additionalRepository.findByRentalId(rentalId);
		List<AdditionalItem> additionalItems = new ArrayList<AdditionalItem>();

		for (OrderedAdditionalItems additionalService : additionals) {
			AdditionalItem additionalItem = this.additionalItemRepository
					.findById(additionalService.getAdditionalItem().getId());
			additionalItems.add(additionalItem);
		}
		return new SuccessDataResult<List<AdditionalItem>>(additionalItems);
	}

}
