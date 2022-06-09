package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.entitites.concretes.Color;

public interface ColorService {

	void add(CreateColorRequest createColorRequest);

	void delete(CreateColorRequest createeateColorRequest);

	void update(CreateColorRequest createColorRequest);

	List<Color> getAll();

	Color getById(int id);

}
