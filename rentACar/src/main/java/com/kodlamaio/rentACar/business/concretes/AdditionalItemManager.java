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
import com.kodlamaio.rentACar.business.response.additionalItems.AdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.additionalItems.ListAdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.additionals.AdditionalResponse;
import com.kodlamaio.rentACar.business.response.additionals.ListAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.entitites.concretes.Additional;
import com.kodlamaio.rentACar.entitites.concretes.AdditionalItem;

@Service
public class AdditionalItemManager implements AdditionalItemService {
	@Autowired
	AdditionalItemRepository additionalItemRepository;
	@Autowired
	ModelMapperService modelMapperService;

	@Override
	public Result add(CreateAddionalItemRequest createAddionalItemRequest) {
	
		AdditionalItem additionalItem=this.modelMapperService.forRequest().map(createAddionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("AdditionalItem.ADDED");
	}

	@Override
	public Result update( UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		AdditionalItem additionalItem=this.modelMapperService.forRequest().map(updateAdditionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("AdditionalItem.Updated");
		
	}

	@Override
	public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		this.additionalItemRepository.deleteById(deleteAdditionalItemRequest.getId());
		return new SuccessResult("AdditionalItem.DELETED");
	}

	@Override
	public DataResult<AdditionalItemResponse> getById(int id) {
		AdditionalItem additionalItem = this.additionalItemRepository.findById(id);
		AdditionalItemResponse response = this.modelMapperService.forResponse().map(additionalItem, AdditionalItemResponse.class);
		return new SuccessDataResult<AdditionalItemResponse>(response);
		
	}

	@Override
	public DataResult<List<ListAdditionalItemResponse>>getAll() {
		List<AdditionalItem> additionalItems=this.additionalItemRepository.findAll();
		List<ListAdditionalItemResponse>response=additionalItems.stream()
				.map(additionalItem -> this.modelMapperService.forResponse().map(additionalItem, ListAdditionalItemResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListAdditionalItemResponse>>(response);
	}

}
