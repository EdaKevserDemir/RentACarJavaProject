package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.CreateOrderedAdditionalItemsRequest;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.DeleteOrderedAdditionalItemsRequest;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.UpdateOrderedAdditionalItemsRequest;
import com.kodlamaio.rentACar.business.response.orderedAdditionalItemsResponse.GetAllOrderedAdditionalItemsResponse;
import com.kodlamaio.rentACar.business.response.orderedAdditionalItemsResponse.ReadOrderedAdditionalItemsResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface OrderedAdditionalItemsService {
	
	Result add(CreateOrderedAdditionalItemsRequest createAdditionalRequest);

	Result delete(DeleteOrderedAdditionalItemsRequest deleteAdditionalRequest);

	Result update(UpdateOrderedAdditionalItemsRequest updateAdditionalRequest);

	DataResult<ReadOrderedAdditionalItemsResponse> getById(int id);

	DataResult<List<GetAllOrderedAdditionalItemsResponse>> getAll();

}
