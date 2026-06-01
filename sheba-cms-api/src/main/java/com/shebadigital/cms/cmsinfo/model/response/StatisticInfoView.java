package com.shebadigital.cms.cmsinfo.model.response;



import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticInfoView {

	@NotNull
	private Long id;
	
	private String name;

	@NotNull
	private Long quantity;

	@NotNull
	private Integer serial; 
}
