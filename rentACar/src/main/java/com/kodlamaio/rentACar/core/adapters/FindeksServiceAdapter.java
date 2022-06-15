package com.kodlamaio.rentACar.core.adapters;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.FindeksScoreCheckService;

@Service
public class FindeksServiceAdapter implements FindeksScoreCheckService {

	Random random = new Random();
	HashMap<String, Integer> findeksScore;

	@Override
	public int checkIfFindeksScore(String tcNo) throws NumberFormatException, RemoteException {

		findeksScore = new HashMap<String, Integer>();
		int score = random.nextInt(1900) + 1;

		System.out.println(score);
		findeksScore.put(tcNo, score); // Hashmap içine put ile değer ekliyoruz.
		return score;

	}

}
