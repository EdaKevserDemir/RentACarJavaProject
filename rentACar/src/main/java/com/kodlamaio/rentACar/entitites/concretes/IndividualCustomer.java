package com.kodlamaio.rentACar.entitites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "individualId", referencedColumnName = "id")
@Table(name = "individualCustomers")
public class IndividualCustomer {
	
	@Column(name = "individualId",insertable = false,updatable = false)
	private int individualId;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "Identity")
	private String identity;
	
	@Column(name = "birthYear")
	private int birthYear;

}
