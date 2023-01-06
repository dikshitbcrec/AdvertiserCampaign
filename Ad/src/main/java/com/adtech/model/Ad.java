package com.adtech.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.Data;

@Data
@Entity
public class Ad {
	
		@Id
		private int adId;
		
		
		private String productName;
		private String adType;
		private String status;
		@Column(name="creation_time")
		private Date time;

		
//		for targeting purpose.
		private String country;
		private String state;
		private String device;
		private String mediaUrl;
	
	
	

}
