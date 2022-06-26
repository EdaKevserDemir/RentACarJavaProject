package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.response.colors.ReadColorResponse;
import com.kodlamaio.rentACar.business.response.colors.GetAllColorResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.entitites.concretes.Color;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

	@Autowired
	private ColorService colorService;

	public ColorsController(ColorService colorService) {

		this.colorService = colorService;
	}

	

	@PostMapping("/add")
	private void add(@RequestBody CreateColorRequest createColorRequest) {
		this.colorService.add(createColorRequest);
	}

	@PostMapping("/delete")
	private Result delete(@RequestBody DeleteColorRequest deleteColorRequest) {
	return	this.colorService.delete(deleteColorRequest);

		
	}

	@PostMapping("/update")
	private Result update(@RequestBody UpdateColorRequest updateColorRequest) {
		return this.colorService.update(updateColorRequest);
		
	}

	@GetMapping("/getall")
	private DataResult<List<GetAllColorResponse>> getAll() {
		return colorService.getAll();
	}

	@GetMapping("/getbyid")

	private DataResult<ReadColorResponse> getById(@RequestParam int id) {
		return colorService.getById(id);
	}
}
