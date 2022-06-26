package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.individualCustomers.CreateIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.DeleteIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.UpdateIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.ReadIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.entitites.concretes.IndividualCustomer;

public class IndividualCustomerManager implements IndividualCustomerService {

	IndividualCustomerRepository individualCustomerRepository;
	ModelMapperService modelMapperService;

	public IndividualCustomerManager(IndividualCustomerRepository individualCustomerRepository,
			ModelMapperService modelMapperService) {
		this.individualCustomerRepository = individualCustomerRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateIndividualCustomersRequest createIndividualCustomersRequest)
			throws NumberFormatException, RemoteException {

		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(createIndividualCustomersRequest, IndividualCustomer.class);

		if (individualCustomerRepository.checkIfRealPerson(createIndividualCustomersRequest)) {
			this.individualCustomerRepository.save(individualCustomer);
			return new SuccessResult("INDIVIDUAL.CUSTOMER.ADDED");
		}

		else {
			return new ErrorResult("INDIVIDUAL.CUSTOMER.NOT.ADDED");
		}
	}

	@Override
	public Result update(UpdateIndividualCustomersRequest updateIndividualCustomersRequest) {
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(updateIndividualCustomersRequest, IndividualCustomer.class);
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(DeleteIndividualCustomersRequest deleteIndividualCustomersRequest) {
		this.individualCustomerRepository.deleteById(deleteIndividualCustomersRequest.getId());
		return new SuccessResult("INDIVIDUAL.CUSTOMER.DELETED");
	}

	@Override
	public DataResult<ReadIndividualCustomerResponse> getById(int id) {
		IndividualCustomer individualCustomer = this.individualCustomerRepository.findById(id);
		ReadIndividualCustomerResponse response = this.modelMapperService.forResponse().map(individualCustomer,
				ReadIndividualCustomerResponse.class);
		return new SuccessDataResult<ReadIndividualCustomerResponse>(response);
	}

	@Override
	public DataResult<List<GetAllIndividualCustomerResponse>> getAll() {
		List<IndividualCustomer> individualCustomers = this.individualCustomerRepository.findAll();
		List<GetAllIndividualCustomerResponse> response = individualCustomers.stream()
				.map(individualCustomer -> this.modelMapperService.forResponse().map(individualCustomer,
						GetAllIndividualCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomerResponse>>(response);
	}

	@Override
	public DataResult<List<GetAllIndividualCustomerResponse>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<IndividualCustomer> individualCustomers = this.individualCustomerRepository.findAll(pageable).getContent();
		List<GetAllIndividualCustomerResponse> response = individualCustomers.stream()
				.map(individualCustomer -> this.modelMapperService.forResponse().map(individualCustomer,
						GetAllIndividualCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomerResponse>>(response);
	}

}
