package com.kodlamaio.rentACar.core.adapters;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CustomerCheckService;
import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.entitites.concretes.Customer;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter implements CustomerCheckService {

	@Override
	public boolean checkIfRealPerson(CreateCustomerRequest createCustomerRequest) throws NumberFormatException, RemoteException {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();

		return kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(createCustomerRequest.getIdentity()), createCustomerRequest.getFirstName().toUpperCase(),
				createCustomerRequest.getLastName().toUpperCase(), createCustomerRequest.getBirthYear());
		
		 
	}
	

}
