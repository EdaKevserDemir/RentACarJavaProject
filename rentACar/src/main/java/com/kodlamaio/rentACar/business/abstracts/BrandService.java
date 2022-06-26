package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.response.brands.ReadBrandResponse;
import com.kodlamaio.rentACar.business.response.brands.GetAllBrandResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entitites.concretes.Brand;

//Response request Pattern

public interface BrandService {

	Result add(CreateBrandRequest createBrandRequest);

	Result delete(DeleteBrandRequest deleteBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);

	DataResult<List<GetAllBrandResponse>> getAll();

	DataResult<ReadBrandResponse> getById(int id);

}
