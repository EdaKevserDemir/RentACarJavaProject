package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalService;
import com.kodlamaio.rentACar.business.requests.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.requests.additionals.DeleteAdditionalRequest;
import com.kodlamaio.rentACar.business.requests.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.response.additionals.AdditionalResponse;
import com.kodlamaio.rentACar.business.response.additionals.ListAdditionalResponse;
import com.kodlamaio.rentACar.business.response.brands.BrandResponse;
import com.kodlamaio.rentACar.business.response.brands.ListBrandResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalRepository;
import com.kodlamaio.rentACar.entitites.concretes.Additional;
import com.kodlamaio.rentACar.entitites.concretes.Brand;

@Service
public class AdditionalManager implements AdditionalService {

	@Autowired
	AdditionalRepository additionalRepository;

	@Autowired
	ModelMapperService modelMapperService;

	@Override
	public Result add(CreateAdditionalRequest createAdditionalRequest) {
		Additional additional = this.modelMapperService.forRequest().map(createAdditionalRequest, Additional.class);
		this.additionalRepository.save(additional);
		return new SuccessResult("ADDITIONAL.ADDED");
	}

	@Override
	public Result delete(DeleteAdditionalRequest deleteAdditionalRequest) {
		this.additionalRepository.deleteById(deleteAdditionalRequest.getId());
		return new SuccessResult("ADDITIONAL.DELETED");
	}

	@Override
	public Result update(UpdateAdditionalRequest updateAdditionalRequest) {
		Additional additional = this.modelMapperService.forRequest().map(updateAdditionalRequest, Additional.class);
		this.additionalRepository.save(additional);
		return new SuccessResult("ADDITIONAL.UPDATED");
	}

	@Override
	public DataResult<AdditionalResponse> getById(int id) {
		Additional additional = this.additionalRepository.findById(id);
		AdditionalResponse response = this.modelMapperService.forResponse().map(additional, AdditionalResponse.class);
		return new SuccessDataResult<AdditionalResponse>(response);

	}

	@Override
	public DataResult<List<ListAdditionalResponse>> getAll() {
		List<Additional> additionals = this.additionalRepository.findAll();
		List<ListAdditionalResponse >response=additionals.stream()
				.map(additional -> this.modelMapperService.forResponse().map(additional, ListAdditionalResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListAdditionalResponse>>(response);
	}

}
