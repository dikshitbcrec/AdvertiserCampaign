package com.adtech.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.adtech.model.Ad;

public interface AdRepository extends JpaRepository<Ad, Integer> {

	
	public Optional<Ad> findByProductName(String productName);
	
	
	
	@Query("SELECT u FROM Ad u WHERE u.status ='Active'")
	public List<Ad> findByStatus();
	
	
	@Transactional
	@Modifying
	@Query("UPDATE Ad  SET status = 'Active' where status='submitting' AND SUBTIME(now(),'0:5:0.000000') >= creation_time")
	public void updateAll();
	
	
}





























//@Query(value="update ad set status='Active' where status='submitting' AND TIMESTAMPDIFF(MINUTE,creation_time,now()) >=5",nativeQuery = true)