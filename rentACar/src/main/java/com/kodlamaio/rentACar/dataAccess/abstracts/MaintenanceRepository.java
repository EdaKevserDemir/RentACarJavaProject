package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entitites.concretes.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer>{

}
