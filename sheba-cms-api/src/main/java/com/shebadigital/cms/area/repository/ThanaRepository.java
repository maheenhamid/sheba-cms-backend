package com.shebadigital.cms.area.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.area.model.entity.Thana;



public interface ThanaRepository extends JpaRepository<Thana, Integer>{

	
	public List<Thana> findByDistrict_districtId(Integer districtId);
}
