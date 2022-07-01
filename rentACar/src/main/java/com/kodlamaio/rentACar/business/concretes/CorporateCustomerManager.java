package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.CreateCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.DeleteCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.UpdateCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.response.corporateCustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.entitites.concretes.CorporateCustomer;

@Service
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
		checkIfExistTaxNumber(createCorporateCustomersRequest.getTaxNumber());
		checkIfExistCompanyName(createCorporateCustomersRequest.getCompanyName());
		checkIfExistCorporateEmail(createCorporateCustomersRequest.getEmail());

		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomersRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateCorporateCustomersRequest updateCorporateCustomersRequest) {
		checkIfExistCorporateId(updateCorporateCustomersRequest.getCorporateId());

		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomersRequest,
				CorporateCustomer.class);

		checkIfExistCompanyName(updateCorporateCustomersRequest.getCompanyName());
		checkIfEmailIsSameForUpdate(updateCorporateCustomersRequest.getCorporateId(),updateCorporateCustomersRequest.getEmail());
		checkIfTaxNumberIsSameForUpdate(updateCorporateCustomersRequest.getCorporateId(),updateCorporateCustomersRequest.getTaxNumber());
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(DeleteCorporateCustomersRequest deleteCorporateCustomersRequest) {
		checkIfExistCorporateId(deleteCorporateCustomersRequest.getId());
		this.corporateCustomerRepository.deleteById(deleteCorporateCustomersRequest.getId());
		return new SuccessResult("CORPORATE.CUSTOMER.DELETED");
	}

	@Override
	public DataResult<ReadCorporateCustomerResponse> getById(int id) {
		checkIfExistCorporateId(id);
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findById(id);
		ReadCorporateCustomerResponse response = this.modelMapperService.forRequest().map(corporateCustomer,
				ReadCorporateCustomerResponse.class);
		return new SuccessDataResult<ReadCorporateCustomerResponse>(response);
	}

	@Override
	public DataResult<List<GetAllCorporateCustomerResponse>> getAll() {
		List<CorporateCustomer> comporateCustomers = this.corporateCustomerRepository.findAll();
		List<GetAllCorporateCustomerResponse> response = comporateCustomers.stream()
				.map(comporateCustomer -> this.modelMapperService.forResponse().map(comporateCustomers,
						GetAllCorporateCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCorporateCustomerResponse>>(response);
	}

	@Override
	public DataResult<List<GetAllCorporateCustomerResponse>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerRepository.findAll(pageable).getContent();
		List<GetAllCorporateCustomerResponse> response = corporateCustomers.stream()
				.map(corporateCustomer -> this.modelMapperService.forResponse().map(corporateCustomer,
						GetAllCorporateCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCorporateCustomerResponse>>(response);
	}

	private void checkIfExistTaxNumber(String taxNumber) {
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findByTaxNumber(taxNumber);
		if (corporateCustomer.getTaxNumber() != null) {
			throw new BusinessException(taxNumber);
		}
	}

	private void checkIfExistCompanyName(String companyName) {
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findByCompanyName(companyName);
		if (corporateCustomer.getCompanyName() != null) {
			throw new BusinessException(companyName);
		}
	}

	private void checkIfExistCorporateId(int id) {
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findById(id);
		if (corporateCustomer == null) {
			throw new BusinessException("CORPORATE.ID.NOT.FOUND");
		}

	}

	private void checkIfExistCorporateEmail(String email) {
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findByEmail(email);
		if (corporateCustomer != null) {
			throw new BusinessException("Email.exist");
		}
	}

	private void checkIfEmailIsSameForUpdate(int corporateId, String email) {
		CorporateCustomer currentCorporateCustomer = this.corporateCustomerRepository.findById(corporateId);
		if (!currentCorporateCustomer.getEmail().equals(email)) {
			checkIfExistCorporateEmail(email);
		}
	}
	private void checkIfTaxNumberIsSameForUpdate(int corporate,String taxNumber ) {
		CorporateCustomer currentCorporateCustomer=this.corporateCustomerRepository.findByTaxNumber(taxNumber);
		if(!currentCorporateCustomer.getTaxNumber().equals(taxNumber)) {
			checkIfExistTaxNumber(taxNumber);
		}
		
	}
}