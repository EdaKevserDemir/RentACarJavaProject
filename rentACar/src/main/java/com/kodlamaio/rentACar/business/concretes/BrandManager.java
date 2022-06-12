package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.response.brands.BrandResponse;
import com.kodlamaio.rentACar.business.response.brands.ListBrandResponse;
import com.kodlamaio.rentACar.business.response.cars.CarResponse;
import com.kodlamaio.rentACar.business.response.colors.ColorResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.entitites.concretes.Brand;
import com.kodlamaio.rentACar.entitites.concretes.Car;

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

		// Brand brand = new Brand();
		// brand.setName(createBrandRequest.getName()); // gelen requesti veri tabanı
		// nesnesine çevirdi.Mapping yapıldı.

		checkIfBrandExistsByName(createBrandRequest.getName());

		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {

		brandRepository.deleteById(deleteBrandRequest.getId());
		return new SuccessResult("deleted");

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {

//		Brand brand = getById(updateBrandRequest.getId());
//		brand.setName(updateBrandRequest.getName());
		// brand.setCars(createBrandRequest);

		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("updated");
	}

	@Override
	public DataResult<List<ListBrandResponse>> getAll() {

		List<Brand> brands = this.brandRepository.findAll();

		List<ListBrandResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, ListBrandResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListBrandResponse>>(response);
	}

	@Override
	public DataResult<BrandResponse> getById(int id) {
		Brand brand = this.brandRepository.findById(id);
		BrandResponse response = this.modelMapperService.forResponse().map(brand,BrandResponse.class);
		return new SuccessDataResult<BrandResponse>(response);
		//return new SuccessDataResult<BrandResponse>(this.modelMapperService.forResponse().map(id, BrandResponse.class));
	}

	public void checkIfBrandExistsByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand != null) {
			throw new BusinessException("BRAND.EXISTS");
		}
	}
}
