package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.entitites.concretes.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandRepository brandRepository;

	// constr bak.ordakinin nesnesini bana oluştur.
	public BrandManager(BrandRepository brandRepository) {

		this.brandRepository = brandRepository;
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {

		Brand brand = new Brand();
		brand.setName(createBrandRequest.getName()); // gelen requesti veri tabanı nesnesine çevirdi.Mapping yapıldı.

		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {

		brandRepository.delete(brandRepository.getById(deleteBrandRequest.getId()));
		return new SuccessResult("deleted");

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = getById(updateBrandRequest.getId());
		brand.setName(updateBrandRequest.getName());
		// brand.setCars(createBrandRequest);
		this.brandRepository.save(brand);
		return new SuccessResult("updated");
	}

	@Override
	public DataResult<List<Brand>> getAll() {

		return new SuccessDataResult<List<Brand>>(brandRepository.findAll(), "BRAND.LISTED");
	}

	@Override
	public Brand getById(int id) {

		return brandRepository.getById(id);
	}

}
