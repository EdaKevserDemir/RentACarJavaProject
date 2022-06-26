package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import com.kodlamaio.rentACar.business.requests.corporateCustomers.CreateCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.DeleteCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.UpdateCorporateCustomersRequest;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.response.corporateCustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CorporateCustomerService {
	Result add(CreateCorporateCustomersRequest createCorporateCustomersRequest)
			throws NumberFormatException, RemoteException;

	Result update(UpdateCorporateCustomersRequest updateCorporateCustomersRequest);

	Result delete(DeleteCorporateCustomersRequest deleteCorporateCustomersRequest);

	DataResult<ReadCorporateCustomerResponse> getById(int id);

	DataResult<List<GetAllCorporateCustomerResponse>> getAll();

	DataResult<List<GetAllCorporateCustomerResponse>> getAll(int pageNo, int pageSize);

}
