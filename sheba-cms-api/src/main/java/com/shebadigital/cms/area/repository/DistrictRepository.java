package com.shebadigital.cms.area.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.area.model.entity.District;



public interface DistrictRepository extends JpaRepository<District, Integer>{
	
	public List<District> findByDivision_DivisionId(Integer divisionId);

}
