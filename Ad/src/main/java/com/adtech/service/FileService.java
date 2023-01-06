package com.adtech.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adtech.exception.AdNotFoundException;
import com.adtech.exception.FileExistingException;
import com.adtech.exception.FileNotFoundException;
import com.adtech.model.Ad;
import com.adtech.model.File;
import com.adtech.repository.AdRepository;
import com.adtech.repository.FileRepository;
import com.adtech.util.ImageUtils;

@Service
public class FileService {

    @Autowired
    private FileRepository filerepository;
    @Autowired
    private AdRepository adRepository;
    
// Inserting new file    
    public String uploadfile(int adId,MultipartFile file) throws IOException {
    	
    
    	if(adRepository.findById(adId).isPresent() && !(filerepository.findById(adId).isPresent()))
    	{
					if(filerepository.findByName(file.getOriginalFilename()).isPresent()) {
					    			
					    			throw new FileExistingException("File already Exist");
					    			
					    		}else
					    		{
					    			File imageData = filerepository.save(File.builder()
					    	                .name(file.getOriginalFilename())
					    	                .type(file.getContentType())
					    	                .adId(adId)
					    	                .imageData(ImageUtils.compressImage(file.getBytes())).build());
					    			
					    			
					    			if(adRepository.findById(imageData.getAdId()).isPresent()){
					    				System.out.println("entering here");
					    					Optional<Ad> findAd=adRepository.findById(adId);
					    					Ad ad=findAd.get();
					    					ad.setMediaUrl("http://localhost:9090/media/"+adId);
			    							ad.setAdType(imageData.getType());
					    						adRepository.save(ad);
					    					}
					    			
					    			
					    	        if (imageData != null) {
					    	            return "file Added successfully : " + file.getOriginalFilename()+" file id: "+imageData.getAdId();
					    	        }
					    	      
					    	        return null;
					    			
					    		}
					 
				    		
				    	}
				    	else {
				    		throw new AdNotFoundException("Sorry No Ad Exist with this adId Or already having existing ad_id");
				    	}
				    		
    	
        
    }
    	
    	
    	
 
//for getting file in api.
    public byte[] downloadFile(String fileName){
    	
    	Optional<File> file=filerepository.findByName(fileName);
    	File imaData=file.get();
    	Optional<Ad> ad=adRepository.findById(imaData.getAdId());
    		if(ad.get().getStatus().equalsIgnoreCase("Active")) {

    			if(filerepository.findByName(fileName).isPresent()){
    	    		 Optional<File> dbImageData = filerepository.findByName(fileName);
    	    	        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
    	    	        return images;	
    	    	}else {
    	    		throw new FileNotFoundException("File Not found");
    	    	}
        		
        	}
        	else {
        		
        		throw new AdNotFoundException("Sorry This Ad is not Active please wait");
        	}
    	}
    	
 
 

    
    




//  Used to update file with the help of id;
    
  public String UpdateFile(int id,MultipartFile file) throws IOException {
	  
  	if(filerepository.findById(id).isPresent() && (!filerepository.findByName(file.getOriginalFilename()).isPresent()))
  	{
  		Optional<File> opimage=filerepository.findById(id);
  		File img=opimage.get();
  		img.setType(file.getContentType());
  		img.setName(file.getOriginalFilename());
  		img.setImageData(ImageUtils.compressImage(file.getBytes()));
  		img.setAdId(img.getAdId());
  		File updateFile=filerepository.save(img);
  		
  		if(adRepository.findById(id).isPresent())
  		{
  			System.out.println("Entering to Ad update");
			Optional<Ad> findAd=adRepository.findById(updateFile.getAdId());
			Ad ad=findAd.get();
				
					ad.setMediaUrl("http://localhost:9090/media/"+id);
					ad.setAdType(updateFile.getType());
				
				adRepository.save(ad);
		}
  		
  		
  		
  		
  		return "file updated successfully : " +file.getOriginalFilename();
  				
  	}
  	else {
  		throw new FileNotFoundException("File with id is not present or file which you are tying to upload already exist");
  	}
  	
  	
  }
    
// used to Delete file by id; 
    public String deleteFile(int id) {
    	
    	if(filerepository.findById(id).isPresent())
    	{	
    		filerepository.deleteById(id);
    		return "Deleted Successfully";
    		
    	}
    	else {
    		throw new FileNotFoundException("Oops! File not found");
    	}
    }
    
    
    
    	
  public Optional<File> getById(int adId){
	  return filerepository.findById(adId);
  }
    
    
}