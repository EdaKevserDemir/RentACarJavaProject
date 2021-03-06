package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.cars.GetAllCarResponse;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entitites.concretes.Rental;

public interface RentalService {
	Result add(CreateRentalRequest createRentalRequest) throws NumberFormatException, RemoteException;

	Result update(UpdateRentalRequest updateRentalRequest) throws NumberFormatException, RemoteException;

	Result delete(DeleteRentalRequest deleteRentalRequest);

	DataResult<List<GetAllRentalResponse>> getAll();

	DataResult<List<GetAllCarResponse>>getAllByFindeksScore();

	DataResult<ReadRentalResponse> getById(int id);

}
