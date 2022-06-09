package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entitites.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorRepository colorRepository;

	public ColorManager(ColorRepository colorRepository) {

		this.colorRepository = colorRepository;
	}

	@Override
	public void add(CreateColorRequest createColorRequest) {
	Color color=new Color();
	color.setName(createColorRequest.getName());
	
	this.colorRepository.save(color);

	}

	@Override
	public void delete(CreateColorRequest createColorRequest) {
		colorRepository.delete(getById(createColorRequest.getId()));
		
	}

	@Override
	public void update(CreateColorRequest createColorRequest) {
		Color color=getById(createColorRequest.getId());
		color.setName(createColorRequest.getName());
		this.colorRepository.save(color);
		
	}

	@Override
	public List<Color> getAll() {
		
		return colorRepository.findAll();
	}

	@Override
	public Color getById(int id) {
//		Color colorToFind=null;
//		for (Color item : colorRepository.findAll()) {
//			if(item.getId()==id) {
//				colorToFind=item;
//			}
//			
//		}
		return colorRepository.getById(id);
	}

}
