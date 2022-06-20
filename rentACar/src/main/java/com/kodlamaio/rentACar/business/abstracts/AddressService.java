package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.AddressResponse;
import com.kodlamaio.rentACar.business.response.addresses.ListAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AddressService {

	Result addSameAddress(CreateAddressRequest createAddressRequest);
	Result addDifferentAddress(CreateAddressRequest createAddressRequest);
	Result updateSameAddress(UpdateAddressRequest updateAddressRequest);
	Result updateDifferentAddress(UpdateAddressRequest updateAddressRequest);
	Result delete(DeleteAddressRequest deleteAddressRequest);
	DataResult<List<ListAddressResponse>>getAll();
	DataResult<AddressResponse>getById(int id);
	
}
