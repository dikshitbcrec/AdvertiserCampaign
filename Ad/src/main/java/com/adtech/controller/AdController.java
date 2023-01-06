package com.adtech.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adtech.dto.AdRequest;
import com.adtech.model.Ad;
import com.adtech.service.AdService;

@RestController
@RequestMapping("/ad")
public class AdController {

	
	
	
	@Autowired
	private AdService adService;
	
	
	
	@PostMapping()
	public ResponseEntity<Ad>create(@RequestBody @Valid AdRequest adRequest) throws InterruptedException
	{	
		Ad ad=new Ad();
		ad.setAdId(adRequest.getAdId());
		ad.setProductName(adRequest.getProductName());
		ad.setCountry(adRequest.getCountry());
		ad.setState(adRequest.getState());
		ad.setDevice(adRequest.getDevice());
		return new ResponseEntity<Ad>(adService.createAd(ad),HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{adId}")
	public ResponseEntity<Ad> update(@PathVariable("adId")  int adId,@RequestBody Ad ad){
		return new ResponseEntity<Ad>(adService.updateAd(adId, ad),HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping("/{adId}")
	public ResponseEntity<Ad> getAd(@PathVariable("adId") int adId)
	{
		return new ResponseEntity<Ad>(adService.getAd(adId),HttpStatus.FOUND);
	}

	@GetMapping()
	public ResponseEntity<List<Ad>> getAllAd(){
	{
		return new ResponseEntity<List<Ad>>(adService.getAll(),HttpStatus.FOUND);
	}
}
	
	
	
	@DeleteMapping("/{adId}")
	public ResponseEntity<String> delete(@PathVariable("adId") int adId)
	{
		return new ResponseEntity<String>(adService.deleteAd(adId),HttpStatus.FOUND);
	}
	
}
