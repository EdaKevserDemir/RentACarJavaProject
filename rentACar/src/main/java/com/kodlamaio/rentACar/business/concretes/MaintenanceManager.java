package com.kodlamaio.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentACar.entitites.concretes.Car;
import com.kodlamaio.rentACar.entitites.concretes.Maintenance;
@Service
public class MaintenanceManager implements MaintenanceService {

	
	private MaintenanceRepository maintenanceRepository;
	private CarRepository carRepository;
	
	
	public MaintenanceManager(MaintenanceRepository maintenanceRepository,CarRepository carRepository) {
		
		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
	}


	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		Maintenance maintenance=new Maintenance();
		Car car= this.carRepository.findById(createMaintenanceRequest.getCarId());
		car.setId(createMaintenanceRequest.getCarId());				
		
	
		maintenance.setId(createMaintenanceRequest.getId());
		maintenance.setDateSent(createMaintenanceRequest.getDateSent());
		maintenance.setDateReturned(createMaintenanceRequest.getDateReturned());
		car.setState(2);	
		
		
		maintenance.setCar(car);
		this.maintenanceRepository.save(maintenance);
		
		
		return new SuccessResult("eklendi");
	}

}
