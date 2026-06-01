package com.shebadigital.cms.examroutine.model.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exam_routine")
public class ExamRoutine implements Serializable{


	private static final long serialVersionUID = -6203554841415549266L;
	
	@Id
	@Column(name = "exam_routine_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long examRoutineId;
	
	@Column(name = "routine_header")
	private String routineHeader;
	
	@Column(name = "exam_name")
	private String examName;
	
	@Column(name = "class_name")
	private String className;
	
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
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
