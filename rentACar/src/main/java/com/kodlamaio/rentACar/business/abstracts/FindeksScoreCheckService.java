package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;


public interface FindeksScoreCheckService {
	int checkIfFindeksScore(String  tcNo) throws NumberFormatException, RemoteException;
}
