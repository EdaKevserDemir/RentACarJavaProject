package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.OrderedAdditionalItemsService;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.CreateOrderedAdditionalItemsRequest;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.DeleteOrderedAdditionalItemsRequest;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.UpdateOrderedAdditionalItemsRequest;
import com.kodlamaio.rentACar.business.response.orderedAdditionalItemsResponse.GetAllOrderedAdditionalItemsResponse;
import com.kodlamaio.rentACar.business.response.orderedAdditionalItemsResponse.ReadOrderedAdditionalItemsResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.OrderedAdditionalItemsRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entitites.concretes.AdditionalItem;
import com.kodlamaio.rentACar.entitites.concretes.Car;
import com.kodlamaio.rentACar.entitites.concretes.OrderedAdditionalItems;
import com.kodlamaio.rentACar.entitites.concretes.Rental;
//check AdditionalId
//check rentalId
@Service
public class OrderedAdditionalItemsManager implements OrderedAdditionalItemsService {

	OrderedAdditionalItemsRepository orderedAdditionalItemsRepository;

	ModelMapperService modelMapperService;

	AdditionalItemRepository additionalItemRepository;
	
	RentalRepository rentalRepository;

	@Autowired
	public OrderedAdditionalItemsManager(OrderedAdditionalItemsRepository orderedAdditionalItemsRepository,
			ModelMapperService modelMapperService, AdditionalItemRepository additionalItemRepository) {
		super();
		this.orderedAdditionalItemsRepository = orderedAdditionalItemsRepository;
		this.modelMapperService = modelMapperService;
		this.additionalItemRepository = additionalItemRepository;
	}

	@Override
	public Result add(CreateOrderedAdditionalItemsRequest createOrderedAdditionalItemsRequest) {
		checkIfAdditionalItemId(createOrderedAdditionalItemsRequest.getAdditionalItemId());
		checkIfExistRentalId(createOrderedAdditionalItemsRequest.getAdditionalItemId());

		OrderedAdditionalItems orderedAdditionalItems = this.modelMapperService.forRequest()
				.map(createOrderedAdditionalItemsRequest, OrderedAdditionalItems.class);
		AdditionalItem additionalItem = this.additionalItemRepository
				.findById(createOrderedAdditionalItemsRequest.getAdditionalItemId());

		orderedAdditionalItems.setTotalPrice(calculateTotalPrice(orderedAdditionalItems,additionalItem ));
		this.orderedAdditionalItemsRepository.save(orderedAdditionalItems);
		return new SuccessResult("ADDITIONAL.ADDED");
	}

	@Override
	public Result delete(DeleteOrderedAdditionalItemsRequest deleteOrderedAdditionalItemsRequest) {
		checkIfOrderedAdditionalItemsId(deleteOrderedAdditionalItemsRequest.getId());
		this.orderedAdditionalItemsRepository.deleteById(deleteOrderedAdditionalItemsRequest.getId());
		return new SuccessResult("ADDITIONAL.DELETED");
	}

	@Override
	public Result update(UpdateOrderedAdditionalItemsRequest updateOrderedAdditionalItemsRequest) {
		checkIfOrderedAdditionalItemsId(updateOrderedAdditionalItemsRequest.getId());
		checkIfAdditionalItemId(updateOrderedAdditionalItemsRequest.getAdditionalItemId());
		checkIfExistRentalId(updateOrderedAdditionalItemsRequest.getRentalId());
		OrderedAdditionalItems additional = this.modelMapperService.forRequest()
				.map(updateOrderedAdditionalItemsRequest, OrderedAdditionalItems.class);
		this.orderedAdditionalItemsRepository.save(additional);
		return new SuccessResult("ADDITIONAL.UPDATED");
	}

	@Override
	public DataResult<ReadOrderedAdditionalItemsResponse> getById(int id) {
		checkIfOrderedAdditionalItemsId(id);
		OrderedAdditionalItems orderedAdditionalItems = this.orderedAdditionalItemsRepository.findById(id);
		ReadOrderedAdditionalItemsResponse response = this.modelMapperService.forResponse().map(orderedAdditionalItems,
				ReadOrderedAdditionalItemsResponse.class);
		return new SuccessDataResult<ReadOrderedAdditionalItemsResponse>(response);

	}

	@Override
	public DataResult<List<GetAllOrderedAdditionalItemsResponse>> getAll() {
		List<OrderedAdditionalItems> additionals = this.orderedAdditionalItemsRepository.findAll();
		List<GetAllOrderedAdditionalItemsResponse> response = additionals.stream()
				.map(additional -> this.modelMapperService.forResponse().map(additional,
						GetAllOrderedAdditionalItemsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllOrderedAdditionalItemsResponse>>(response);
	}

	private long calculateTotalDay(OrderedAdditionalItems orderedAdditionalItems) {

		long dayDifference = (orderedAdditionalItems.getReturnDate().getTime() - orderedAdditionalItems.getPickupDate().getTime());

		if (dayDifference < 0) {
			throw new BusinessException("Invalid date");

		}
		long time = TimeUnit.DAYS.convert(dayDifference, TimeUnit.MILLISECONDS);
		orderedAdditionalItems.setTotalDays((int) time);
		return time;

	}

	private double calculateTotalPrice(OrderedAdditionalItems orderedAdditionalItems, AdditionalItem additionalItem) {
		long time = calculateTotalDay(orderedAdditionalItems);
		orderedAdditionalItems.setTotalDays((int) time);
		double totalPrice = additionalItem.getDailyPrice() * time;

		return totalPrice;
	}

	private void checkIfOrderedAdditionalItemsId(int id) {
		OrderedAdditionalItems orderedAdditionalItems = this.orderedAdditionalItemsRepository.findById(id);
		if (orderedAdditionalItems == null) {
			throw new BusinessException("ID.NOT.FOUND");
		}
	}

	private void checkIfAdditionalItemId(int id) {
		AdditionalItem additionalItem = this.additionalItemRepository.findById(id);
		if (additionalItem == null) {
			throw new BusinessException("ADDITIONAL.ID.NOT.FOUND");
		}
	}

	private void checkIfExistRentalId(int id) {
	}
}
