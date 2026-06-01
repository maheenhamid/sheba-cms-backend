package com.shebadigital.cms.classroutine.model.entity;

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
@Table(name = "class_routine")
public class ClassRoutine implements Serializable{


	private static final long serialVersionUID = -6203554841415549266L;
	
	@Id
	@Column(name = "class_routine_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classRoutineId;
	
	@Column(name = "routine_header")
	private String routineHeader;
	
	@Column(name = "class_name")
	private String className;
	
	@Column(name = "year")
	private Integer year;
	
	@Column(name = "serial")
	private Integer serial;
	
	@Column(name = "entry_date")
	private Date entryDate;
	
	@Column(name = "modify_date")
	private Date modifyDate;
	
	@Column(name = "routine_status")
	private Integer routineStatus;
	
	@Column(name = "file_name")
	private String fileName;
	
	@ManyToOne
	@JoinColumn(name = "cms_id")
	private CmsInfo cmsInfo;
	
	
	
}
