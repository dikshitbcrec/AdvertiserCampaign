package com.adtech.repository;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.adtech.model.Advertiser;

public interface AdvertiserRepository extends JpaRepository<Advertiser, Integer> {
	
public List<Advertiser> findByAdvertiserName(String advertiserName);

public Optional<Advertiser> findByAdId(int adId);


		@Modifying
		@Transactional
		@Query("Delete From Advertiser Where ad_id = :adId")
		Integer  deleteByAdid(int adId);
			

}
