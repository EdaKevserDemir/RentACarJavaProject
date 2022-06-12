package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

	private MaintenanceRepository maintenanceRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;

	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository,
			ModelMapperService modelMapperService) {

		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
		this.maintenanceRepository = maintenanceRepository;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
//		Maintenance maintenance = new Maintenance();
		Car car = this.carRepository.findById(createMaintenanceRequest.getCarId());
//		car.setId(createMaintenanceRequest.getCarId());
//
//		maintenance.setId(createMaintenanceRequest.getId());
//		maintenance.setDateSent(createMaintenanceRequest.getDateSent());
//		maintenance.setDateReturned(createMaintenanceRequest.getDateReturned());
		
//
	
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
		maintenance.setCar(car);
		car.setState(2);
		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("BRAND.ADDED");

	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		Maintenance maintenanceToUpdate = maintenanceRepository.findById(updateMaintenanceRequest.getId());
//		maintenanceToUpdate.setDateReturned(updateMaintenanceRequest.getDateReturned());
//		maintenanceToUpdate.setDateSent(updateMaintenanceRequest.getDateSent());
		Car car = this.carRepository.findById(updateMaintenanceRequest.getCarId());
		car.setId(updateMaintenanceRequest.getCarId());

		car.setState(2);
		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);

		maintenanceToUpdate.setCar(car);
		LocalDate date = LocalDate.parse("2022-06-09");

		if (date.equals(maintenanceToUpdate.getDateReturned()) || date.isAfter(maintenanceToUpdate.getDateReturned())
				|| date.isBefore(maintenanceToUpdate.getDateReturned())) {

			car.setState(1);
		}

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
		return new SuccessDataResult<MaintenanceResponse>(
				this.modelMapperService.forResponse().map(id, MaintenanceResponse.class));
	}

}
