/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shebadigital.cms.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 *
 * @author riad
 */

@Service
public class CommonInfoUtils {
	
	private Logger logger = LoggerFactory.getLogger(CommonInfoUtils.class);
	
	
	

	
////	 public static final String DEFAULT_IMAGE_PATH = ApplicationUtils.getFilePath("DEFAULT");
////	 public static final String STAFF_IMAGE_PATH = ApplicationUtils.getFilePath("STAFF");
//	 public static final String STAFF_ID_CARD_TEMP_7_IMAGE_NAME = "staff_id_card_temp_7_heading_image.png";
//	 public static final String STUDENT_ID_CARD_TEMP_9_IMAGE_NAME = "std_id_card_temp_9_heading.png";
//	 public static final String STUDENT_ID_CARD_TEMP_10_IMAGE_NAME = "std_id_card_temp2_heading.png"; // Temp 2 = Temp 10
//	 public static final String STUDENT_ID_CARD_IMAGE_NAME = "std_id_card_barcode.png";
//    
//    public  static final int SUCCESS_STATUS=1;
//    public  static final int ERROR_STATUS=0;
    
    
 
    
    
    public static List<MonthInfo> getMonthNameWithFirstDateAndLastDate(int year) {

        List<MonthInfo> list = new ArrayList<>();

        try {

            for (int i = 1; i <= 12; i++) {

                YearMonth yearMonth = YearMonth.of(year, i);
                LocalDate firstLocalDate = yearMonth.atDay(1);
                LocalDate lastLocalDate = yearMonth.atEndOfMonth();

                java.util.Date firstDate;

                firstDate = new SimpleDateFormat("yyyy-MM-dd").parse(firstLocalDate.toString());
                java.util.Date lastDate = new SimpleDateFormat("yyyy-MM-dd").parse(lastLocalDate.toString());
                String monthName = new SimpleDateFormat("MMMM").format(firstDate);
                list.add(new MonthInfo(monthName, firstDate, lastDate));
            }

        } catch (ParseException ex) {
        	System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    
//    public DefaultSignatureDto provideDefaultSignDto(Institute institute) {
//
//		List<DefaultSignature> defaultSigns = defaultSignatureRepository.findByInstitute(institute);
//	
//		DefaultSignatureDto defaultSignatureDto = new DefaultSignatureDto();
//
//		for (DefaultSignature defaultSign : defaultSigns) {
//			defaultSignatureDto = mapDefaultSignDto(defaultSign, defaultSignatureDto);
//		}
//
//		return defaultSignatureDto;
//	}
//	
//    
//	
//	public DefaultSignatureDto mapDefaultSignDto(DefaultSignature defaultSign, DefaultSignatureDto defaultSignatureDto) {
//
//		switch (defaultSign.getUsedId()) {
//
//		case 11:
//			defaultSignatureDto.setStudentIdCardSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setStudentIdCardSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setStudentIdCardSignStatus(defaultSign.getSignStatus());
//			break;
//
//		case 12:
//			defaultSignatureDto.setHrIdCardSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setHrIdCardSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setHrIdCardSignStatus(defaultSign.getSignStatus());
//			break;
//
//		case 13:
//			defaultSignatureDto.setMarksheetRightSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setMarksheetRightSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setMarksheetRightSignStatus(defaultSign.getSignStatus());
//			break;
//
//		case 14:
//			defaultSignatureDto.setMarksheetMiddleSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setMarksheetMiddleSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setMarksheetMiddleSignStatus(defaultSign.getSignStatus());
//			break;
//
//		case 15:
//			defaultSignatureDto.setMarksheetLeftSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setMarksheetLeftSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setMarksheetLeftSignStatus(defaultSign.getSignStatus());
//			break;
//			
//		case 16:
//			defaultSignatureDto.setAdmitCardRightSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setAdmitCardRightSignImgName(defaultSign.getSignImgName());
//			defaultSignatureDto.setAdmitCardRightSignStatus(defaultSign.getSignStatus());
//			break;
//
//		case 17:
//			defaultSignatureDto.setAdmitCardLeftSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setAdmitCardLeftSignImgName(defaultSign.getSignImgName());
//			defaultSignatureDto.setAdmitCardLeftSignStatus(defaultSign.getSignStatus());
//			break;
//			
//		case 18:
//			defaultSignatureDto.setStudentFeeReceiptBottomSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setStudentFeeReceiptBottomSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setStudentFeeReceiptBottomSignStatus(defaultSign.getSignStatus());
//			break;
//			
//		case 19:
//			defaultSignatureDto.setTestimonialRightSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setTestimonialRightSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setTestimonialRightSignStatus(defaultSign.getSignStatus());
//			break;
//			
//		case 20:
//			defaultSignatureDto.setTestimonialLeftSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setTestimonialLeftSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setTestimonialLeftSignStatus(defaultSign.getSignStatus());
//			break;
//			
//		case 21:
//			defaultSignatureDto.setTcLeftSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setTcLeftSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setTcLeftSignStatus(defaultSign.getSignStatus());
//			break;
//			
//		case 22:
//			defaultSignatureDto.setTcRightSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setTcRightSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setTcRightSignStatus(defaultSign.getSignStatus());
//			break;
//			
//		case 23:
//			defaultSignatureDto.setCtMarksheetMiddleSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setCtMarksheetMiddleSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setCtMarksheetMiddleSignStatus(defaultSign.getSignStatus());
//			break;
//		
//		case 24:
//			defaultSignatureDto.setCtMarksheetRightSignTitle(defaultSign.getSignatureTitle());
//			defaultSignatureDto.setCtMarksheetRightSignName(defaultSign.getSignImgName());
//			defaultSignatureDto.setCtMarksheetRightSignStatus(defaultSign.getSignStatus());
//			break;
//		}
//
//		return defaultSignatureDto;
//	}
    
    
}
