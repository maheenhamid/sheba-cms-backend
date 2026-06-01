package com.shebadigital.cms.area.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="location_thana")
public class Thana  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3373591986757961969L;

	/**
	 * 
	 */


	@Id
	@Column(name="thana_id")
	private Integer thanaId;
	
	@Column(name="thana_name")
	private String thanaName;
	
	@ManyToOne
	@JoinColumn(name="district_id")
	private District district;

	public Integer getThanaId() {
		return thanaId;
	}

	public void setThanaId(Integer thanaId) {
		this.thanaId = thanaId;
	}

	public String getThanaName() {
		return thanaName;
	}

	public void setThanaName(String thanaName) {
		this.thanaName = thanaName;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	
	
}
