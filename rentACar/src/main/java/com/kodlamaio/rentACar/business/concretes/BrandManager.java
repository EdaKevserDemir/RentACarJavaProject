package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.response.brands.GetAllBrandResponse;
import com.kodlamaio.rentACar.business.response.brands.ReadBrandResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.entitites.concretes.Brand;

//Id,name

@Service
public class BrandManager implements BrandService {

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;

	// constr bak.ordakinin nesnesini bana oluştur.
	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {

		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistsByName(createBrandRequest.getName());
		//Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		Brand brand=Brand.builder()
				.name(createBrandRequest.getName())
				.build();
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		checkIfExistBrandId(deleteBrandRequest.getId());
		brandRepository.deleteById(deleteBrandRequest.getId());
		return new SuccessResult("deleted");

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		checkIfExistBrandId(updateBrandRequest.getId());
		checkIfBrandExistsByName(updateBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("updated");
	}

	@Override
	public DataResult<List<GetAllBrandResponse>> getAll() {
		List<Brand> brands = this.brandRepository.findAll();
		List<GetAllBrandResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllBrandResponse>>(response);
	}

	@Override
	public DataResult<ReadBrandResponse> getById(int id) {
		Brand brand = this.brandRepository.findById(id);
		ReadBrandResponse response = this.modelMapperService.forResponse().map(brand, ReadBrandResponse.class);
		return new SuccessDataResult<ReadBrandResponse>(response);
	}

	//Checking for name
	private void checkIfBrandExistsByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand == null) {
			throw new BusinessException("BRAND.EXISTS");
		}
	}

	//checking for brandId
	private void checkIfExistBrandId(int id) {
		Brand brand = this.brandRepository.findById(id);
		if (brand == null) {
			throw new BusinessException("BRAND.ID.NOT.FOUND");
		}

	}
}
