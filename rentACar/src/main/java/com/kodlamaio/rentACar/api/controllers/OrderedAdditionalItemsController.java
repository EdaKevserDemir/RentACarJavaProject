package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.OrderedAdditionalItemsService;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.CreateOrderedAdditionalItemsRequest;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.DeleteOrderedAdditionalItemsRequest;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.UpdateOrderedAdditionalItemsRequest;
import com.kodlamaio.rentACar.business.response.orderedAdditionalItemsResponse.GetAllOrderedAdditionalItemsResponse;
import com.kodlamaio.rentACar.business.response.orderedAdditionalItemsResponse.ReadOrderedAdditionalItemsResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/orderedAdditionalItems")
public class OrderedAdditionalItemsController {

	@Autowired
	OrderedAdditionalItemsService additionalService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateOrderedAdditionalItemsRequest createOrderedAdditionalItemsRequest) {

		return this.additionalService.add(createOrderedAdditionalItemsRequest);
		

	}
	@PostMapping("/update")
	public Result update(@RequestBody UpdateOrderedAdditionalItemsRequest updateOrderedAdditionalItemsRequest) {
		return this.additionalService.update(updateOrderedAdditionalItemsRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteOrderedAdditionalItemsRequest deleteOrderedAdditionalItemsRequest) {
		return this.additionalService.delete(deleteOrderedAdditionalItemsRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllOrderedAdditionalItemsResponse>>getall(){
		return this.additionalService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<ReadOrderedAdditionalItemsResponse>getByid(@RequestParam int id){
		return this.additionalService.getById(id);
	}
}
