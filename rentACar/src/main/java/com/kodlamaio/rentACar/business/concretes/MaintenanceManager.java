package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.response.brands.ListBrandResponse;
import com.kodlamaio.rentACar.business.response.colors.ColorResponse;
import com.kodlamaio.rentACar.business.response.maintenances.ListMaintenanceResponse;
import com.kodlamaio.rentACar.business.response.maintenances.MaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentACar.entitites.concretes.Brand;
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
	public DataResult<List<ListMaintenanceResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();

		List<ListMaintenanceResponse> response = maintenances.stream().map(
				maintenance -> this.modelMapperService.forResponse().map(maintenance, ListMaintenanceResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListMaintenanceResponse>>(response);
	}

	@Override
	public DataResult<MaintenanceResponse> getById(int id) {
		Maintenance maintenance=this.maintenanceRepository.findById(id);
		MaintenanceResponse response=	this.modelMapperService.forResponse().map(maintenance, MaintenanceResponse.class);
		return new SuccessDataResult<MaintenanceResponse>(response);
			
	}

}
