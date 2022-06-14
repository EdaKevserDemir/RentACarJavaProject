package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.requests.additionals.DeleteAdditionalRequest;
import com.kodlamaio.rentACar.business.requests.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.response.additionals.AdditionalResponse;
import com.kodlamaio.rentACar.business.response.additionals.ListAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalService {
	
	Result add(CreateAdditionalRequest createAdditionalRequest);

	Result delete(DeleteAdditionalRequest deleteAdditionalRequest);

	Result update(UpdateAdditionalRequest updateAdditionalRequest);

	DataResult<AdditionalResponse> getById(int id);

	DataResult<List<ListAdditionalResponse>> getAll();

}
