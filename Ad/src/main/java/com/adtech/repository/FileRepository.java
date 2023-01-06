package com.adtech.repository;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.adtech.model.File;

public interface FileRepository extends JpaRepository<File,Integer> {


    public Optional<File> findByName(String fileName);
 
    @Modifying
	@Transactional
	@Query("Delete From File Where ad_id = :adId")
	Integer  deleteByAdid(int adId);
}