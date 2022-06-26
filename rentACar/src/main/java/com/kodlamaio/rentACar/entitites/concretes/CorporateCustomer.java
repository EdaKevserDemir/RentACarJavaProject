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
@PrimaryKeyJoinColumn(name = "corporateId", referencedColumnName = "id")
@Table(name = "corporateCustomers")
public class CorporateCustomer {

	@Column(name = "corporateId", insertable = false, updatable = false)
	private int corporateId;
	
	@Column(name="taxNumber") 
	private String taxNumber;
	
	@Column(name="companyName")
	private String companyName;
	
	
}
