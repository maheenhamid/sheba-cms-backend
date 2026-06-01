/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shebadigital.cms.common;

import java.util.Date;

/**
 *
 * @author riad
 */
public class MonthInfo {
    
    private String monthName;
    private Date firstDate;
    private Date lastDate;

    public MonthInfo(String monthName, Date firstDate, Date lastDate) {
        this.monthName = monthName;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
    
    
    
}
