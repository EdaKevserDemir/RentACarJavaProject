package com.kodlamaio.rentACar.entitites.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "customerId",referencedColumnName = "id")
@Table(name = "customers")

public class Customer  extends User{
	
	@Column(name = "customerId",insertable = false,updatable = false)
	private int customerId;
	
	@Column(name="customerNumber")
	private int customerNumber;
	
	@OneToMany(mappedBy = "customer")
	private List<Address> addresses;

	@OneToMany(mappedBy = "customer")
	private List<Rental> rentals;

}
