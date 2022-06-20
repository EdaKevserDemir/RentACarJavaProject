package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.AddressResponse;
import com.kodlamaio.rentACar.business.response.addresses.ListAddressResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.entitites.concretes.Address;
import com.kodlamaio.rentACar.entitites.concretes.Customer;

@Service
public class AddressManager implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	@Autowired
	ModelMapperService modelMapperService;
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Result addSameAddress(CreateAddressRequest createAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
	
		address.setInvoiceAddress(address.getContactAddress());
		this.addressRepository.save(address);

		return new SuccessResult("added");
	}

	@Override
	public Result addDifferentAddress(CreateAddressRequest createAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
	
		this.addressRepository.save(address);

		return new SuccessResult("added");
	}

	@Override
	public Result updateSameAddress(UpdateAddressRequest updateAddressRequest) {
		Address addressToUpdate = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		Customer customer = this.customerRepository.findByid(updateAddressRequest.getCustomerId());
		addressToUpdate.setCustomer(customer);
		addressToUpdate.setInvoiceAddress(addressToUpdate.getContactAddress());
		this.addressRepository.save(addressToUpdate);
		return new SuccessResult("updated");

	}

	@Override
	public Result updateDifferentAddress(UpdateAddressRequest updateAddressRequest) {
		Address addressToUpdate = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		Customer customer = this.customerRepository.findByid(updateAddressRequest.getCustomerId());
		addressToUpdate.setCustomer(customer);
		this.addressRepository.save(addressToUpdate);
		return new SuccessResult("updated");
	}

	@Override
	public Result delete(DeleteAddressRequest deleteAddressRequest) {
		this.addressRepository.deleteById(deleteAddressRequest.getId());
		return new SuccessResult("deleted");
	}

	@Override
	public DataResult<List<ListAddressResponse>> getAll() {

		List<Address> brands = this.addressRepository.findAll();

		List<ListAddressResponse> response = brands.stream()
				.map(address -> this.modelMapperService.forResponse().map(address, ListAddressResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListAddressResponse>>(response);
	}

	@Override
	public DataResult<AddressResponse> getById(int id) {
		Address address = this.addressRepository.findById(id);
		AddressResponse response = this.modelMapperService.forResponse().map(address, AddressResponse.class);
		return new SuccessDataResult<AddressResponse>(response);

	}

}
