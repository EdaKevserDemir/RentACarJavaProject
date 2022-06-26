package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.requests.addresses.CreateDifferentAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.CreateSameAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateDifferentAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateSameAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.ReadAddressResponse;
import com.kodlamaio.rentACar.business.response.addresses.GetAllAddressResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.entitites.concretes.Address;
import com.kodlamaio.rentACar.entitites.concretes.Brand;
import com.kodlamaio.rentACar.entitites.concretes.Customer;

@Service
public class AddressManager implements AddressService {

	AddressRepository addressRepository;

	ModelMapperService modelMapperService;

	CustomerRepository customerRepository;

	// aynıysa-farklıysa Notnull,Id kontrol,customerkontrol
	@Autowired
	public AddressManager(AddressRepository addressRepository, ModelMapperService modelMapperService,
			CustomerRepository customerRepository) {
		super();
		this.addressRepository = addressRepository;
		this.modelMapperService = modelMapperService;
		this.customerRepository = customerRepository;
	}

	@Override
	public Result addSameAddress(CreateSameAddressRequest createSameAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(createSameAddressRequest, Address.class);

		address.setInvoiceAddress(address.getContactAddress());
		this.addressRepository.save(address);

		return new SuccessResult("added");
	}

	@Override
	public Result addDifferentAddress(CreateDifferentAddressRequest createDifferentAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(createDifferentAddressRequest, Address.class);

		this.addressRepository.save(address);

		return new SuccessResult("added");
	}

	@Override
	public Result updateSameAddress(UpdateSameAddressRequest updateSameAddressRequest) {
		checkIfAddressId(updateSameAddressRequest.getCustomerId());
		Address updateToAddress = this.modelMapperService.forRequest().map(updateSameAddressRequest, Address.class);
		updateToAddress.setInvoiceAddress(updateToAddress.getContactAddress());
		this.addressRepository.save(updateToAddress);
		return new SuccessResult("updated");

	}

	@Override
	public Result updateDifferentAddress(UpdateDifferentAddressRequest updateDifferentAddressRequest) {
		checkIfAddressId(updateDifferentAddressRequest.getCustomerId());
		Address addressToUpdate = this.modelMapperService.forRequest().map(updateDifferentAddressRequest,
				Address.class);
		this.addressRepository.save(addressToUpdate);
		return new SuccessResult("updated");
	}

	@Override
	public Result delete(DeleteAddressRequest deleteAddressRequest) {
		checkIfAddressId(deleteAddressRequest.getId());
		this.addressRepository.deleteById(deleteAddressRequest.getId());
		return new SuccessResult("deleted");
	}

	@Override
	public DataResult<List<GetAllAddressResponse>> getAll() {

		List<Address> brands = this.addressRepository.findAll();

		List<GetAllAddressResponse> response = brands.stream()
				.map(address -> this.modelMapperService.forResponse().map(address, GetAllAddressResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllAddressResponse>>(response);
	}

	@Override
	public DataResult<ReadAddressResponse> getById(int id) {
		Address address = this.addressRepository.findById(id);
		ReadAddressResponse response = this.modelMapperService.forResponse().map(address, ReadAddressResponse.class);
		return new SuccessDataResult<ReadAddressResponse>(response);

	}

	private void checkIfAddressId(int id) {
		Address address = this.addressRepository.findById(id);
		if (address == null) {
			throw new BusinessException("ID.NOT.FOUND");
		}

	}

}
