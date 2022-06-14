package com.kodlamaio.rentACar.business.response.maintenances;

import java.sql.Date;

import com.kodlamaio.rentACar.business.response.brands.ListBrandResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListMaintenanceResponse {
	private int id;
	private Date dateSent;
	private Date dateReturned;
	private int carId;
}
