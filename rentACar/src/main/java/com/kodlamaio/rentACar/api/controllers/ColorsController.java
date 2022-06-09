package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.response.colors.ColorResponse;
import com.kodlamaio.rentACar.entitites.concretes.Color;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

	@Autowired
	private ColorService colorService;

	public ColorsController(ColorService colorService) {

		this.colorService = colorService;
	}

	@GetMapping("/getColor")
	public String getColor() {
		return "color";
	}

	@PostMapping("/add")
	private void add(@RequestBody CreateColorRequest createColorRequest) {
		this.colorService.add(createColorRequest);
	}

	@PostMapping("/delete")
	private void delete(@RequestBody CreateColorRequest createColorRequest) {
		this.colorService.delete(createColorRequest);

	}

	@PostMapping("/update")
	private void update(@RequestBody CreateColorRequest createColorRequest) {
		this.colorService.update(createColorRequest);
	}

	@GetMapping("/getall")
	private List<Color> getAll() {
		return colorService.getAll();
	}

	@GetMapping("/getbyid")

	private Color getById(@RequestBody ColorResponse colorResponse) {
		return colorService.getById(colorResponse.getId());
	}
}
