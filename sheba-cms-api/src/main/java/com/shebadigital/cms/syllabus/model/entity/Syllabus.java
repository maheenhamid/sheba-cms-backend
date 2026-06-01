package com.shebadigital.cms.syllabus.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "syllabus")
public class Syllabus implements Serializable{
	
	
	private static final long serialVersionUID = 3977695616312066080L;

	@Id
	@Column(name = "syllabus_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long syllabusId;
	
	@Column(name = "class_name")
	private String className;
	
	@Column(name = "header_text")
	private String headerText;
	
	@Column(name = "year")
	private Integer year;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "show_status")
	private Integer showStatus;
	
	@Column(name = "serial")
	private Integer serial;
	
	@Column(name = "entry_date")
	private Date entryDate;
	
	@Column(name = "last_modify_date")
	private Date lastModifyDate;
	
	@Column(name = "last_modified_by")
	private String lastModifiedBy;
	
	@ManyToOne
	@JoinColumn(name = "cms_id")
	private CmsInfo cmsInfo;
	
	
}
