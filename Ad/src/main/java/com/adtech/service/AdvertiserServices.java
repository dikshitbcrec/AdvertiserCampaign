package com.adtech.service;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adtech.exception.AdNotFoundException;
import com.adtech.exception.FileExistingException;
import com.adtech.exception.ProductNotFoundException;

import com.adtech.model.Advertiser;
import com.adtech.repository.AdRepository;
import com.adtech.repository.AdvertiserRepository;
import com.adtech.repository.FileRepository;
@Service
public class AdvertiserServices {
	
		@Autowired
		private AdvertiserRepository advertiserRepository;
		@Autowired
		private AdRepository adRepo;
		@Autowired
		private FileRepository fileRepository;
		
		

	
//		adding new Advertiser with ad;
		
		public Advertiser create(Advertiser advertiser) {
		
			if(advertiserRepository.findByAdId(advertiser.getAdId()).isPresent())
			{
				throw new FileExistingException("Already adId is present");
			}else {
				
				if(adRepo.findById(advertiser.getAdId()).isPresent())
				{
					throw new AdNotFoundException("There is no any ad with this id");
				}
				else {
					Date date=new Date();
					advertiser.setCreatedTime(date.toString());
					return advertiserRepository.save(advertiser);
					
				}
			
			}
			
		}

		
		
//all advertiser details
				public List<Advertiser> getAllAdvertiser()
				{
					return advertiserRepository.findAll();
				}
					
		
		
		
		
//list of advertiser by advertiserName
				public List<Advertiser> getByAdvertiserName(String advertiserName )
				{
					
					List<Advertiser>advertiserList =advertiserRepository.findByAdvertiserName(advertiserName);
					if(advertiserList.size()>0)
					{
						return advertiserList;
					}
					else {
						throw new AdNotFoundException("There is no any data by with this "+advertiserName+ " Name");
					}
					
				}
		
		

		
	
		
		
		
		
// for deleting a particular data by AdvertiserId;	
		public String delByAdvertiserId(int id)
		{
			if(advertiserRepository.findById(id).isPresent())
			{
				int adId=advertiserRepository.findById(id).get().getAdId();
				
				if(adRepo.findById(adId).isPresent())
				{
					adRepo.deleteById(adId);
				}
				if(fileRepository.findById(adId).isPresent())
				{
					fileRepository.deleteById(adId);
				}
				advertiserRepository.deleteById(id);
				return "Successfully Deleted";
			}
			else {
				throw new ProductNotFoundException("OOPs, Advertiser id Not Found");
			}
			
			
			
		}






//gettting all data with the help of adId.
		public Optional<Advertiser> getByAdvertiserId(int adId) {
			
	
			Optional<Advertiser>advertiser=advertiserRepository.findById(adId);
			if(advertiser.isPresent())
			{
				return advertiser;
			}
			else {
				throw new AdNotFoundException("No Any Advertiser with this Id");
			}
			
		}

		
		
	

			
}
