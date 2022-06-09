package com.kodlamaio.rentACar.business.requests.maintenances;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaintenanceRequest {
	private int id;
	private LocalDate dateSent;
	private LocalDate dateReturned;
	private int carId;
	private int state;

}
