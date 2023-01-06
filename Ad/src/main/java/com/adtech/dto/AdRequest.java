package com.adtech.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "buildAd")
public class AdRequest {
 
	 @Min(value=1, message="Advertiser ID:Advertiser ID Must Be More Then 1")
	  @Max(value=3000, message="Advertiser ID:Advertiser ID Must Be Less Then 3000")
	    private int adId;
	   
	 @NotBlank(message="productName can't be Blank")
	    private String productName;

	 
	    @NotBlank(message="Country can't be Blank")
	    private String country;
	    

	    @NotBlank(message="State can't be Blank")
	    private String state;
	    

	    @NotBlank(message="Device cant be Blank")
	    private String device;
	    
	    
}
