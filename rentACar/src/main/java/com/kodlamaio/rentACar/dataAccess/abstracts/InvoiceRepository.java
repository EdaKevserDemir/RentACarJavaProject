package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kodlamaio.rentACar.entitites.concretes.Invoice;
import com.kodlamaio.rentACar.entitites.concretes.RentalDetail;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	
//	@Query("Select r.totalPrice from rentals r  join invoices i r.id=i.rental_id group by rental_id ")
//	RentalDetails getByTotalPrice(int id);

	
	
}
