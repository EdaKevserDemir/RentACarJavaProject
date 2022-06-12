package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.response.brands.BrandResponse;
import com.kodlamaio.rentACar.business.response.brands.ListBrandResponse;
import com.kodlamaio.rentACar.business.response.colors.ColorResponse;
import com.kodlamaio.rentACar.business.response.colors.ListColorResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entitites.concretes.Brand;
import com.kodlamaio.rentACar.entitites.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorRepository colorRepository;
	private ModelMapperService modelMapperService;

	public ColorManager(ColorRepository colorRepository,ModelMapperService modelMapperService) {

		this.colorRepository = colorRepository;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {

			
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);

		this.colorRepository.save(color);
		return new SuccessResult("COLOR.ADDED");

	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		this.colorRepository.delete(colorRepository.getById(deleteColorRequest.getId()));
		return new SuccessResult("deleted");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
//		Color color=getById(createColorRequest.getId());
//		color.setName(createColorRequest.getName());
		
		

		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);

		this.colorRepository.save(color);
		return new SuccessResult("COLOR.UPDATED");

	}

	@Override
	public DataResult<List<ListColorResponse>> getAll() {

		List<Color> colors = this.colorRepository.findAll();

		List<ListColorResponse> response = colors.stream()
				.map(color -> this.modelMapperService.forResponse().map(color, ListColorResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListColorResponse>>(response);
	}

	@Override
	public DataResult<ColorResponse> getById( int id) {

		Color color=this.colorRepository.findById(id);
		ColorResponse response=this.modelMapperService.forResponse().map(color, ColorResponse.class);
		
		return new SuccessDataResult<ColorResponse>(response);
	}

}
