package com.shebadigital.cms.area.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shebadigital.cms.area.service.LocationService;
import com.shebadigital.cms.common.ItemResponse;


@Controller
@RequestMapping(value = "/location/info")
public class LocationController {
	
	@Autowired
	public LocationService locationService;
	
	@GetMapping(value = "/division-list")
	public ResponseEntity<ItemResponse> findDivisionList(){
		ItemResponse itemResponse=locationService.divisionList();
		return new ResponseEntity<>(itemResponse,HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/district-list")
	public ResponseEntity<ItemResponse> findDistrictList(){
		ItemResponse itemResponse=locationService.districtList();
		return new ResponseEntity<>(itemResponse,HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/district-list/by/division-id")
	public ResponseEntity<ItemResponse> findDistrictByDivisionId(@RequestParam Integer divisionId){
		ItemResponse itemResponse=locationService.districtListByDivisionId(divisionId);
		return new ResponseEntity<>(itemResponse,HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/thana-list/by/district-id")
	public ResponseEntity<ItemResponse> findThanaByDistrictId(@RequestParam Integer districtId){
		ItemResponse itemResponse=locationService.thanaListByDistrictId(districtId);
		return new ResponseEntity<>(itemResponse,HttpStatus.OK);
	}
	
	
	
	
	

}
