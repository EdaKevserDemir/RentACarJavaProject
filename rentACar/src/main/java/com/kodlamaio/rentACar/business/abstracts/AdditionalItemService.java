package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.additionalItems.CreateAddionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.additionalItems.ReadAdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAllAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalItemService {
	
	Result add(CreateAddionalItemRequest createAddionalItemRequest);

	Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest);

	Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest);

	DataResult<ReadAdditionalItemResponse> getById(int id);

	DataResult<List<GetAllAdditionalItemResponse>> getAll();

}
