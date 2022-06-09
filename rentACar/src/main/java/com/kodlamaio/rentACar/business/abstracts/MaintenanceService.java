package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface MaintenanceService {

	Result add(CreateMaintenanceRequest createMaintenanceRequest);
}
