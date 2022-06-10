package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
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

	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository) {

		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		Maintenance maintenance = new Maintenance();
		Car car = this.carRepository.findById(createMaintenanceRequest.getCarId());
		car.setId(createMaintenanceRequest.getCarId());

		maintenance.setId(createMaintenanceRequest.getId());
		maintenance.setDateSent(createMaintenanceRequest.getDateSent());
		maintenance.setDateReturned(createMaintenanceRequest.getDateReturned());
		car.setState(2);

		maintenance.setCar(car);
		this.maintenanceRepository.save(maintenance);

		return new SuccessResult("eklendi");
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		Maintenance maintenanceToUpdate = maintenanceRepository.getById(updateMaintenanceRequest.getId());
		maintenanceToUpdate.setDateReturned(updateMaintenanceRequest.getDateReturned());
		maintenanceToUpdate.setDateSent(updateMaintenanceRequest.getDateSent());
		Car car = this.carRepository.findById(updateMaintenanceRequest.getCarId());
		car.setId(updateMaintenanceRequest.getCarId());
		maintenanceToUpdate.setCar(car);
		LocalDate date = LocalDate.parse("2022-06-09");

		if (date.equals(maintenanceToUpdate.getDateReturned()) || date.isAfter(maintenanceToUpdate.getDateReturned())
				|| date.isBefore(maintenanceToUpdate.getDateReturned())) {

			car.setState(1);
		}

		car.setState(2);

		maintenanceRepository.save(maintenanceToUpdate);
		return new SuccessResult("Bakım durumu güncellendi");
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		this.maintenanceRepository.deleteById(deleteMaintenanceRequest.getId());
		return new SuccessResult("silindi");

	}

	@Override
	public DataResult<List<Maintenance>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Maintenance getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
