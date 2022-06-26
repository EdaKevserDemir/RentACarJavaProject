package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.response.maintenances.GetAllMaintenanceResponse;
import com.kodlamaio.rentACar.business.response.maintenances.ReadMaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentACar.entitites.concretes.Car;
import com.kodlamaio.rentACar.entitites.concretes.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService {
@Autowired
	private MaintenanceRepository maintenanceRepository;
@Autowired
	private CarRepository carRepository;
@Autowired
	private ModelMapperService modelMapperService;


	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {

		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
		Car car = this.carRepository.findById(createMaintenanceRequest.getCarId());
		car.setState(2);
		maintenance.setCar(car);
		
		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("MAINTENANCE.ADDED");

	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {

		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		Car car = this.carRepository.findById(updateMaintenanceRequest.getCarId());
		maintenance.setCar(car);
		
		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("updated");

	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		this.maintenanceRepository.deleteById(deleteMaintenanceRequest.getId());
		return new SuccessResult("silindi");

	}

	@Override
	public DataResult<List<GetAllMaintenanceResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();

		List<GetAllMaintenanceResponse> response = maintenances.stream().map(
				maintenance -> this.modelMapperService.forResponse().map(maintenance, GetAllMaintenanceResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllMaintenanceResponse>>(response);
	}

	@Override
	public DataResult<ReadMaintenanceResponse> getById(int id) {
		Maintenance maintenance=this.maintenanceRepository.findById(id).get();
		ReadMaintenanceResponse response=	this.modelMapperService.forResponse().map(maintenance, ReadMaintenanceResponse.class);
		return new SuccessDataResult<ReadMaintenanceResponse>(response);
			
	}

}
