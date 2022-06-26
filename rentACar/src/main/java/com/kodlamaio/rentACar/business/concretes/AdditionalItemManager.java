package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.requests.additionalItems.CreateAddionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.additionalItems.ReadAdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.orderedAdditionalItemsResponse.GetAllOrderedAdditionalItemsResponse;
import com.kodlamaio.rentACar.business.response.orderedAdditionalItemsResponse.ReadOrderedAdditionalItemsResponse;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAllAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.entitites.concretes.OrderedAdditionalItems;
import com.kodlamaio.rentACar.entitites.concretes.Rental;
import com.kodlamaio.rentACar.entitites.concretes.AdditionalItem;

//Id,name

@Service
public class AdditionalItemManager implements AdditionalItemService {
	@Autowired
	AdditionalItemRepository additionalItemRepository;
	@Autowired
	ModelMapperService modelMapperService;

	@Override
	public Result add(CreateAddionalItemRequest createAddionalItemRequest) {
		checkIfAdditionalItemExistByName(createAddionalItemRequest.getName());
		AdditionalItem additionalItem=this.modelMapperService.forRequest().map(createAddionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("AdditionalItem.ADDED");
	}

	@Override
	public Result update( UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		checkIfExistAdditionalItemId(updateAdditionalItemRequest.getId());
		checkIfAdditionalItemExistByName(updateAdditionalItemRequest.getName());
		AdditionalItem additionalItem=this.modelMapperService.forRequest().map(updateAdditionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("AdditionalItem.Updated");
		
	}

	@Override
	public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		checkIfExistAdditionalItemId(deleteAdditionalItemRequest.getId());
		this.additionalItemRepository.deleteById(deleteAdditionalItemRequest.getId());
		return new SuccessResult("AdditionalItem.DELETED");
	}

	@Override
	public DataResult<ReadAdditionalItemResponse> getById(int id) {
		checkIfExistAdditionalItemId(id);
		AdditionalItem additionalItem = this.additionalItemRepository.findById(id);
		ReadAdditionalItemResponse response = this.modelMapperService.forResponse().map(additionalItem, ReadAdditionalItemResponse.class);
		return new SuccessDataResult<ReadAdditionalItemResponse>(response);
		
	}

	@Override
	public DataResult<List<GetAllAdditionalItemResponse>>getAll() {
		List<AdditionalItem> additionalItems=this.additionalItemRepository.findAll();
		List<GetAllAdditionalItemResponse>response=additionalItems.stream()
				.map(additionalItem -> this.modelMapperService.forResponse().map(additionalItem, GetAllAdditionalItemResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalItemResponse>>(response);
	}

	//name Control
	private void checkIfAdditionalItemExistByName(String name) {
		AdditionalItem additionalItem=this.additionalItemRepository.findByName(name);
		if(additionalItem!=null) {
			throw new BusinessException("Additional item exist");
		}
		
	}
	//Id control
	private void checkIfExistAdditionalItemId(int id) {
		AdditionalItem additionalItem=this.additionalItemRepository.findById(id);
		if(additionalItem==null) {
			throw new BusinessException("ID.NOT.FOUND");
		}

	}
}
