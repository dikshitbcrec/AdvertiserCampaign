package com.adtech.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="advertiser")
public class Advertiser {
	
//	For Creating Ad, where we can store (Audio,Video,Image) url,text; 
	@Id
	private int advertiserId;
	private String advertiserName;
	private String email;
	private int  adId;
	private String createdTime;
	


	
	
}
