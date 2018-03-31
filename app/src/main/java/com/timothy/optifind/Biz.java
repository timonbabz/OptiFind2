package com.timothy.optifind;

/**
 * Created by user on 3/14/2018.
 */

public class Biz {

    private String bizName, bizLocation, bizCategory, bizSubcategory;

    public Biz(){

    }

    public Biz(String bizName, String bizLocation, String bizCategory, String bizSubcategory) {
        this.bizName = bizName;
        this.bizLocation = bizLocation;
        this.bizCategory = bizCategory;
        this.bizCategory = bizSubcategory;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBizLocation() {
        return bizLocation;
    }

    public void setBizLocation(String bizLocation) {
        this.bizLocation = bizLocation;
    }

    public String getBizCategory() {
        return bizCategory;
    }

    public void setBizCategory(String bizCategory) {
        this.bizCategory = bizCategory;
    }

    public String getBizSubcategory() {
        return bizSubcategory;
    }

    public void setBizSubcategory(String bizSubcategory) {
        this.bizSubcategory = bizSubcategory;
    }
}
