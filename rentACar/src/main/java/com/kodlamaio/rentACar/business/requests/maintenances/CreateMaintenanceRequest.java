package com.kodlamaio.rentACar.business.requests.maintenances;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceRequest {
	private int id;
	private Date dateSent;
	private Date dateReturned;
	private int carId;
	

}