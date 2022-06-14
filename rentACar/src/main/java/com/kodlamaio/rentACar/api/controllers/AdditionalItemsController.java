package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.requests.additionalItems.CreateAddionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.additionalItems.AdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.additionalItems.ListAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalitems")
public class AdditionalItemsController {

	AdditionalItemService additionalService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateAddionalItemRequest createAddionalItemRequest) {
		return this.additionalService.add(createAddionalItemRequest);

	}
	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		return this.additionalService.update(updateAdditionalItemRequest);

	}
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		return this.additionalService.delete(deleteAdditionalItemRequest);

	}
	@GetMapping("/getall")
	public DataResult<List<ListAdditionalItemResponse>>getAll(){
		return this.additionalService.getAll();
	}
	@GetMapping("/getbyid")
	public DataResult<AdditionalItemResponse>getById(@RequestParam int id){
		return this.additionalService.getById(id);
	}

}
