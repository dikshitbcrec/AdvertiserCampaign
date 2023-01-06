package com.adtech.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.adtech.exception.AdNotFoundException;
import com.adtech.exception.ProductNotFoundException;
import com.adtech.model.Ad;
import com.adtech.repository.AdRepository;
import com.adtech.repository.AdvertiserRepository;
import com.adtech.repository.FileRepository;



@Service
public class AdService{

	@Autowired
	private AdRepository adRepo;
	@Autowired
	private AdvertiserRepository advertiserRepository;
	
	@Autowired
	private FileRepository fileRepository;
	
	

	
	
//	create ad
	public Ad createAd(Ad ad)
	{	
		if(adRepo.findByProductName(ad.getProductName()).isPresent())
		{
			throw new ProductNotFoundException("Product Already Exist");
		}
		else {
			if(advertiserRepository.findByAdId(ad.getAdId()).isPresent())
			{
			Date currentDate=new Date();
	        ad.setTime(currentDate);        
			ad.setAdType("N.A");
			ad.setStatus("Submitting");
			
			ad.setMediaUrl("Currently, It  doesn't contain any file");
			 
			return adRepo.save(ad);
			
		}
		else
			{
				throw new AdNotFoundException("Advertiser do not have adid with this = "+ad.getAdId());
			}
		}
	}
	
	
	
// update ad	
	 public Ad updateAd(int adId,Ad ad)
	 {	
		 
		 	if(adRepo.findById(adId).isPresent()) {
			 Ad update=new Ad();
			 update=ad;
			return adRepo.save(update);
		 }else {
			throw new AdNotFoundException("ad-Id  don't exist");
		 }
	 }


	 
// get ad by id
	public Ad getAd(int adId)
	 {
		
		 
		 if(adRepo.findById(adId).isPresent()) {
			Optional<Ad> findAd=adRepo.findById(adId);
			Ad ad=findAd.get();
			return ad;
			 
		 }else {
			throw new AdNotFoundException("Ad with this Ad-Id "+adId+" Don't exist");
		 }
	 }
	 
// get all ad	 
	public List<Ad> getAll(){
		return adRepo.findAll();
		
	}
	 
	 


// delete ad
	 public String deleteAd(int adId)
		{
		 
		 	
		 
			if(adRepo.findById(adId).isPresent())
			{
				if(fileRepository.findById(adId).isPresent())
				{
					fileRepository.deleteById(adId);
				}
				adRepo.deleteById(adId);
				return "Deleted Successfully";
			}
			else {
				throw new AdNotFoundException("No Ad exist");
			}
		}



//Background Update happening 
	
	 @Scheduled(fixedRate = 60000,initialDelay = 60000)
	 public void backgroundUpate() {
		 
		 adRepo.updateAll();
		 System.out.println("Running");
		 
	 }
	 
	 
	
	
}








//List<Ad> allAd=adRepo.findByAll();
//
//
//for(int i=0;i<allAd.size();i++)
//{
//	Ad ad=allAd.get(i);
//	long milliTime=0;
////	try {
////		 milliTime=ConvertTime.convert_time_into_milliSecond(ad.getTime());
////	} catch (Exception e) {
////		// TODO Auto-generated catch block
////		e.printStackTrace();
////	}
//	
//	Date currentDate=new Date();
//	System.out.println(milliTime);
//	System.out.println(currentDate);
//	System.out.println(currentDate.getTime()-milliTime);
//	long minutes=(currentDate.getTime()-milliTime)/60000;
//	System.out.println("Minutes " +minutes);
//	if(minutes>=5 && ad.getStatus().equalsIgnoreCase("submitting")) {
//		ad.setStatus("Active");
//		adRepo.save(ad);
//		
//	}
//	 
//}
