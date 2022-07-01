package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CustomerCheckService;
import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.individualCustomers.CreateIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.DeleteIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.UpdateIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.ReadIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.entitites.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	IndividualCustomerRepository individualCustomerRepository;
	ModelMapperService modelMapperService;
	CustomerCheckService customerCheckService;

	public IndividualCustomerManager(IndividualCustomerRepository individualCustomerRepository,
			ModelMapperService modelMapperService, CustomerCheckService customerCheckService) {
		this.individualCustomerRepository = individualCustomerRepository;
		this.modelMapperService = modelMapperService;
		this.customerCheckService = customerCheckService;
	}

	@Override
	public Result add(CreateIndividualCustomersRequest createIndividualCustomersRequest)
			throws NumberFormatException, RemoteException {
		// Real Person?
		checkIfExistIndividualCustomer(createIndividualCustomersRequest);
		// customer exist?
		checkIfExistIndividualIdentity(createIndividualCustomersRequest.getIdentity());
		//checkIfExistEmail(createIndividualCustomersRequest.getEmail());
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(createIndividualCustomersRequest, IndividualCustomer.class);

		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateIndividualCustomersRequest updateIndividualCustomersRequest)
			throws NumberFormatException, RemoteException {
		checkIfExistIndividualId(updateIndividualCustomersRequest.getIndividualId());
		checkIfIdentityIsSameForUpdate(updateIndividualCustomersRequest.getIndividualId(),
				updateIndividualCustomersRequest.getIdentity());
		checkIfExistIndividualCustomer(updateIndividualCustomersRequest);
		//checkIfExistEmail(updateIndividualCustomersRequest.getEmail());

		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(updateIndividualCustomersRequest, IndividualCustomer.class);
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(DeleteIndividualCustomersRequest deleteIndividualCustomersRequest) {
		checkIfExistIndividualId(deleteIndividualCustomersRequest.getIndividualId());
		this.individualCustomerRepository.deleteById(deleteIndividualCustomersRequest.getIndividualId());
		return new SuccessResult("INDIVIDUAL.CUSTOMER.DELETED");
	}

	@Override
	public DataResult<ReadIndividualCustomerResponse> getById(int id) {
		checkIfExistIndividualId(id);
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

//	private void checkIfExistEmail(String eMail) {
//		IndividualCustomer individualCustomer = this.individualCustomerRepository.findByEmail(eMail);
//		if (individualCustomer.getEmail() != null) {
//
//			throw new BusinessException("EMAIL.EXIST");
//		}
//	}

	private void checkIfExistIndividualId(int id) {
		IndividualCustomer individualCustomer = this.individualCustomerRepository.findById(id);
		if (individualCustomer==null ) {

			throw new BusinessException("ID.EXIST");
		}
	}

	private void checkIfExistIndividualCustomer(CreateIndividualCustomersRequest createIndividualCustomersRequest)
			throws NumberFormatException, RemoteException {

		if (!customerCheckService.checkIfRealPerson(createIndividualCustomersRequest)) {
			throw new BusinessException("THIS IDENTITY IS NOT VALID");
		}
	}

	private void checkIfExistIndividualCustomer(UpdateIndividualCustomersRequest updateIndividualCustomersRequest)
			throws NumberFormatException, RemoteException {

		if (!customerCheckService.checkIfRealPerson(updateIndividualCustomersRequest)) {
			throw new BusinessException("THIS IDENTITY IS NOT VALID");
		}
	}

	// Create time,check identity for customer.
	private void checkIfExistIndividualIdentity(String identity) {
		IndividualCustomer individualCustomer = this.individualCustomerRepository.findByIdentity(identity);
		if (individualCustomer != null) {
			throw new BusinessException(identity);
		}

	}

	// check customer Identity ,when updating individual customer.
	private void checkIfIdentityIsSameForUpdate(int individualId, String identity) {
		IndividualCustomer individualCustomer = this.individualCustomerRepository.findById(individualId);
		if (!individualCustomer.getIdentity().equals(identity)) {
			checkIfExistIndividualIdentity(identity);
		}
	}

}
