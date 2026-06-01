package com.shebadigital.cms.topstudent.model.entity;



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
@Table(name = "top_student")
public class TopStudent implements Serializable{
	
	private static final long serialVersionUID = -3405893743704571616L;

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	
	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "serial")
	private Long serial;
	
	@Column(name = "father_name")
	private String fatherName;
	
	@Column(name = "mother_name")
	private String motherName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "roll")
	private String roll;
	
	@Column(name = "show_status")
	private Integer showStatus;
	
	@Column(name = "exam_name")
	private String examName;
	
	@Column(name = "year")
	private Integer year;
	
	@Column(name = "student_result")
	private String studentResult;	
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "input_date")
	private Date inputDate;
	
	@Column(name = "modify_date")
	private Date modifyDate;
	
	@ManyToOne
	@JoinColumn(name = "cms_id", nullable = false)
	private CmsInfo cmsInfo;
}
