package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalDetailService;
import com.kodlamaio.rentACar.business.requests.rentalDetails.CreateRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.DeleteRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.UpdateRentalDetailRequest;
import com.kodlamaio.rentACar.business.response.rentalDetails.ListRentalDetailsResponse;
import com.kodlamaio.rentACar.business.response.rentalDetails.RentalDetailResponse;
import com.kodlamaio.rentACar.business.response.rentals.ListRentalResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalDetailRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entitites.concretes.Additional;
import com.kodlamaio.rentACar.entitites.concretes.Rental;
import com.kodlamaio.rentACar.entitites.concretes.RentalDetail;

@Service
public class RentalDetailManager implements RentalDetailService {

	@Autowired
	RentalDetailRepository rentalDetailRepository;
	@Autowired
	RentalRepository rentalRepository;
	@Autowired
	AdditionalRepository additionalRepository;
	@Autowired
	ModelMapperService modelMapperService;

	@Override
	public Result add(CreateRentalDetailRequest createRentalDetailRequest) {
		RentalDetail rentalDetail = this.modelMapperService.forRequest().map(createRentalDetailRequest,
				RentalDetail.class);
		Additional additional = this.additionalRepository.findById(createRentalDetailRequest.getAdditionalId());
		Rental rental = this.rentalRepository.findById(createRentalDetailRequest.getRentalId());

		rentalDetail.setAdditional(additional);
		rentalDetail.setRental(rental);

		double sum = additional.getTotalPrice() + rental.getTotalPrice();

		rentalDetail.setSumTotalPrice(sum);

		this.rentalDetailRepository.save(rentalDetail);

		return new SuccessResult("added");
	}

	@Override
	public Result update(UpdateRentalDetailRequest updateRentalDetailRequest) {
		RentalDetail updateToRentalDetail = this.modelMapperService.forRequest().map(updateRentalDetailRequest,
				RentalDetail.class);
	
		Additional additional = this.additionalRepository.findById(updateRentalDetailRequest.getAdditionalId());
		Rental rental = this.rentalRepository.findById(updateRentalDetailRequest.getRentalId());

		updateToRentalDetail.setAdditional(additional);
		updateToRentalDetail.setRental(rental);
		double sum = additional.getTotalPrice() + rental.getTotalPrice();

		updateToRentalDetail.setSumTotalPrice(sum);
		this.rentalDetailRepository.save(updateToRentalDetail);

		return new SuccessResult("updated");
	}

	@Override
	public Result delete(DeleteRentalDetailRequest deleteRentalDetailRequest) {
		this.rentalDetailRepository.deleteById(deleteRentalDetailRequest.getId());
		return new SuccessResult("deleted");
	}

	@Override
	public DataResult<RentalDetailResponse> getById(int id) {
	    RentalDetail rentalDetail = this.rentalDetailRepository.findById(id);
	    RentalDetailResponse rentalDetailResponse = this.modelMapperService.forResponse().map(rentalDetail, RentalDetailResponse.class);
		return new SuccessDataResult<RentalDetailResponse>(rentalDetailResponse);
	}

	@Override
	public DataResult<List<ListRentalDetailsResponse>> getAll() {
		List<RentalDetail> rentalDetails = this.rentalDetailRepository.findAll();

		List<ListRentalDetailsResponse> response = rentalDetails.stream()
				.map(rentalDetail -> this.modelMapperService.forResponse().map(rentalDetail, ListRentalDetailsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListRentalDetailsResponse>>(response);
	}
	

}
