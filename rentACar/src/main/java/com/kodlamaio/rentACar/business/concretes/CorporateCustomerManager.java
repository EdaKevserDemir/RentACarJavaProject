package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.CreateCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.DeleteCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.UpdateCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.response.corporateCustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.entitites.concretes.CorporateCustomer;
import com.kodlamaio.rentACar.entitites.concretes.IndividualCustomer;

public class CorporateCustomerManager implements CorporateCustomerService {

	CorporateCustomerRepository corporateCustomerRepository;
	ModelMapperService modelMapperService;

	public CorporateCustomerManager(CorporateCustomerRepository corporateCustomerRepository,
			ModelMapperService modelMapperService) {

		this.corporateCustomerRepository = corporateCustomerRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCorporateCustomersRequest createCorporateCustomersRequest)
			throws NumberFormatException, RemoteException {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomersRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateCorporateCustomersRequest updateCorporateCustomersRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomersRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(DeleteCorporateCustomersRequest deleteCorporateCustomersRequest) {
		this.corporateCustomerRepository.deleteById(deleteCorporateCustomersRequest.getId());
		return new SuccessResult("CORPORATE.CUSTOMER.DELETED");
	}

	@Override
	public DataResult<ReadCorporateCustomerResponse> getById(int id) {
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findById(id);
		ReadCorporateCustomerResponse response = this.modelMapperService.forRequest().map(corporateCustomer,
				ReadCorporateCustomerResponse.class);
		return new SuccessDataResult<ReadCorporateCustomerResponse>(response);
	}

	@Override
	public DataResult<List<GetAllCorporateCustomerResponse>> getAll() {
		List<CorporateCustomer> comporateCustomers=this.corporateCustomerRepository.findAll();
		List<GetAllCorporateCustomerResponse> response = comporateCustomers.stream()
				.map(comporateCustomer -> this.modelMapperService.forResponse().map(corpo,
						GetAllIndividualCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomerResponse>>(response);
	}

	@Override
	public DataResult<List<GetAllCorporateCustomerResponse>> getAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
