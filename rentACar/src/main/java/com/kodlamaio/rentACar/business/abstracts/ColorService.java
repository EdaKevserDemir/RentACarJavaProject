package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.response.colors.ReadColorResponse;
import com.kodlamaio.rentACar.business.response.colors.GetAllColorResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface ColorService {

	Result add(CreateColorRequest createColorRequest);

	Result delete(DeleteColorRequest deleteColorRequest);

	Result update(UpdateColorRequest updateColorRequest);

	DataResult<List<GetAllColorResponse>> getAll();

	DataResult<ReadColorResponse> getById(int id);

}
