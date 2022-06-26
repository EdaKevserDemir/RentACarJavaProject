package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.response.brands.ReadBrandResponse;
import com.kodlamaio.rentACar.business.response.brands.GetAllBrandResponse;
import com.kodlamaio.rentACar.business.response.colors.ReadColorResponse;
import com.kodlamaio.rentACar.business.response.colors.GetAllColorResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entitites.concretes.Brand;
import com.kodlamaio.rentACar.entitites.concretes.Color;

//Id,name

@Service
public class ColorManager implements ColorService {

	private ColorRepository colorRepository;
	private ModelMapperService modelMapperService;

	public ColorManager(ColorRepository colorRepository, ModelMapperService modelMapperService) {

		this.colorRepository = colorRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		checkIfColorExistsByName(createColorRequest.getName());
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.ADDED");

	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		checkIfColorId(deleteColorRequest.getId());
		this.colorRepository.deleteById(deleteColorRequest.getId());
		return new SuccessResult("deleted");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		checkIfColorId(updateColorRequest.getId());
		checkIfColorExistsByName(updateColorRequest.getName());
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.UPDATED");

	}

	@Override
	public DataResult<List<GetAllColorResponse>> getAll() {

		List<Color> colors = this.colorRepository.findAll();

		List<GetAllColorResponse> response = colors.stream()
				.map(color -> this.modelMapperService.forResponse().map(color, GetAllColorResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllColorResponse>>(response);
	}

	@Override
	public DataResult<ReadColorResponse> getById(int id) {
		checkIfColorId(id);
		Color color = this.colorRepository.findById(id);
		ReadColorResponse response = this.modelMapperService.forResponse().map(color, ReadColorResponse.class);

		return new SuccessDataResult<ReadColorResponse>(response);
	}

	private void checkIfColorExistsByName(String name) {
		Color currentColor = this.colorRepository.findByName(name);
		if (currentColor == null) {
			throw new BusinessException("COLOR.EXISTS");
		}
	}

	private void checkIfColorId(int id) {
		Color color = this.colorRepository.findById(id);
		if (color == null) {
			throw new BusinessException("ID.NOT.FOUND");
		}
	}
}
