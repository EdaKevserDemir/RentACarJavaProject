package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalService;
import com.kodlamaio.rentACar.business.requests.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.requests.additionals.DeleteAdditionalRequest;
import com.kodlamaio.rentACar.business.requests.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.response.additionals.AdditionalResponse;
import com.kodlamaio.rentACar.business.response.additionals.ListAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionals")
public class AdditionalsController {

	@Autowired
	AdditionalService additionalService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalRequest createAdditionalRequest) {

		return this.additionalService.add(createAdditionalRequest);
		

	}
	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdditionalRequest updateAdditionalRequest) {
		return this.additionalService.update(updateAdditionalRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteAdditionalRequest deleteAdditionalRequest) {
		return this.additionalService.delete(deleteAdditionalRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListAdditionalResponse>>getall(){
		return this.additionalService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<AdditionalResponse>getByid(@RequestParam int id){
		return this.additionalService.getById(id);
	}
}
