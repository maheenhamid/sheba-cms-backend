package com.shebadigital.cms.area.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shebadigital.cms.area.model.entity.District;
import com.shebadigital.cms.area.model.entity.Division;
import com.shebadigital.cms.area.model.entity.Thana;
import com.shebadigital.cms.area.model.response.DistrictResponse;
import com.shebadigital.cms.area.model.response.DivisionResponse;
import com.shebadigital.cms.area.model.response.ThanaResponse;
import com.shebadigital.cms.area.repository.DistrictRepository;
import com.shebadigital.cms.area.repository.DivisionRepository;
import com.shebadigital.cms.area.repository.ThanaRepository;
import com.shebadigital.cms.common.ItemResponse;



@Service
public class LocationService {
	
	@Autowired
	public DivisionRepository divisionRepository;
	
	@Autowired
	public DistrictRepository districtRepository;
	
	@Autowired
	public ThanaRepository thanaRepository;
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemResponse divisionList() {
		ItemResponse itemResponse=new ItemResponse();
		List<Division> divisions=divisionRepository.findAll();
		List<DivisionResponse> divisionResponses=new ArrayList<>();
		for(Division d:divisions) {
			DivisionResponse divisionResponse=new DivisionResponse();
			divisionResponse.setDivisionId(d.getDivisionId());
			divisionResponse.setDivisionName(d.getDivisionName());
			divisionResponses.add(divisionResponse);
		}
		itemResponse.setItem(divisionResponses);
		itemResponse.setMessageType(1);
		itemResponse.setMessage("ok");
		return itemResponse;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemResponse districtList() {
		ItemResponse itemResponse=new ItemResponse();
		List<District> districts=districtRepository.findAll();
		List<DistrictResponse> districtResponses=new ArrayList<>();
		for(District d:districts) {
			DistrictResponse districtResponse=new DistrictResponse();
			districtResponse.setDistrictId(d.getDistrictId());
			districtResponse.setDistrictName(d.getDistrictName());
			districtResponse.setDivisionId(d.getDivision().getDivisionId());
			districtResponse.setDivisionName(d.getDivision().getDivisionName());
			districtResponses.add(districtResponse);
			
		}
		itemResponse.setItem(districtResponses);
		itemResponse.setMessageType(1);
		itemResponse.setMessage("ok");
		return itemResponse;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemResponse districtListByDivisionId(Integer divisionId) {
		ItemResponse itemResponse=new ItemResponse();
		List<District> districts=districtRepository.findByDivision_DivisionId(divisionId);
		List<DistrictResponse> districtResponses=new ArrayList<>();
		for(District d:districts) {
			DistrictResponse districtResponse=new DistrictResponse();
			districtResponse.setDistrictId(d.getDistrictId());
			districtResponse.setDistrictName(d.getDistrictName());
			districtResponse.setDivisionId(d.getDivision().getDivisionId());
			districtResponse.setDivisionName(d.getDivision().getDivisionName());
			districtResponses.add(districtResponse);
			
		}
		itemResponse.setItem(districtResponses);
		itemResponse.setMessageType(1);
		itemResponse.setMessage("ok");
		return itemResponse;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemResponse thanaListByDistrictId(Integer districtId) {
	
		ItemResponse itemResponse=new ItemResponse();
		List<Thana> thanas=thanaRepository.findByDistrict_districtId(districtId);
		List<ThanaResponse> thanaResponses=new ArrayList<>();
		for(Thana d:thanas) {
			ThanaResponse thanaResponse=new ThanaResponse();
			thanaResponse.setThanaId(d.getThanaId());
			thanaResponse.setThanaName(d.getThanaName());
			thanaResponse.setDistrictId(d.getDistrict().getDistrictId());
			thanaResponse.setDistrictName(d.getDistrict().getDistrictName());
			thanaResponses.add(thanaResponse);
			
		}
		itemResponse.setItem(thanaResponses);
		itemResponse.setMessageType(1);
		itemResponse.setMessage("ok");
		return itemResponse;
	}
	
	
	
	

}
