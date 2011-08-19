package com.utils.batsurvey;

import java.util.Date;

public class Survey {
    private String name;
    private Date date;
    
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
