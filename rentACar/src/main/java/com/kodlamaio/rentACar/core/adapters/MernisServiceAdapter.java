package com.kodlamaio.rentACar.core.adapters;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CustomerCheckService;
import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.CreateIndividualCustomersRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.UpdateIndividualCustomersRequest;
import com.kodlamaio.rentACar.entitites.concretes.Customer;
import com.kodlamaio.rentACar.entitites.concretes.IndividualCustomer;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter implements CustomerCheckService {

	@Override
	public boolean checkIfRealPerson(CreateIndividualCustomersRequest createIndividualCustomersRequest) throws NumberFormatException, RemoteException {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();

		return kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(createIndividualCustomersRequest.getIdentity()), createIndividualCustomersRequest.getFirstName().toUpperCase(),
				createIndividualCustomersRequest.getLastName().toUpperCase(), createIndividualCustomersRequest.getBirthYear());
		
		 
	}

	@Override
	public boolean checkIfRealPerson(UpdateIndividualCustomersRequest updateIndividualCustomersRequest)
			throws NumberFormatException, RemoteException {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();

		return kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(updateIndividualCustomersRequest.getIdentity()), updateIndividualCustomersRequest.getFirstName().toUpperCase(),
				updateIndividualCustomersRequest.getLastName().toUpperCase(), updateIndividualCustomersRequest.getBirthYear());
		
	}
	

}
