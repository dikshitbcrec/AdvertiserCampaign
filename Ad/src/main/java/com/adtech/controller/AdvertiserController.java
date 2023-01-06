package com.adtech.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adtech.model.Advertiser;
import com.adtech.service.AdvertiserServices;

@RestController
@RequestMapping("/advertiser")
public class AdvertiserController {
	
	
//	
	@Autowired
	private AdvertiserServices advertiserServices;

	
	

	
	
	
	//creating new ad for advertiser	
	@PostMapping()
	public ResponseEntity<Advertiser> create(@RequestBody Advertiser advertiser)
	{
		return new ResponseEntity<Advertiser>(advertiserServices.create(advertiser),HttpStatus.CREATED);
	}

	
	
	// getting all advertiser list
	 @GetMapping()
	 	
	 	public ResponseEntity<List<Advertiser>> getAll(){
		 return new ResponseEntity<List<Advertiser>>(advertiserServices.getAllAdvertiser(),HttpStatus.FOUND);
	 }
 
 
 
 
//	 getting all advertiser by Advertiser Name
	@GetMapping("/advertiser-name")
		public ResponseEntity<List<Advertiser>> getbyCompanyName(@RequestParam String advertiserName){
			 return new ResponseEntity<List<Advertiser>>(advertiserServices.getByAdvertiserName(advertiserName),HttpStatus.FOUND);
		 }
	
 
//  getting all advertiser list with the help of advertiserId
 @GetMapping("/{advrtiserId}")

	public ResponseEntity<Optional<Advertiser>> get(@RequestParam int advertiserId){
	 return new ResponseEntity<Optional<Advertiser>>(advertiserServices.getByAdvertiserId(advertiserId),HttpStatus.FOUND);
}
 
 
 
 
// delete advertiser with advertiserId
 @DeleteMapping("/{advertiserId}")
 public String delete(@PathVariable("advertiserId") int advertiserId)
 {
	 return advertiserServices.delByAdvertiserId(advertiserId);
 }

}
