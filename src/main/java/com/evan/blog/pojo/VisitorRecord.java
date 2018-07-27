package com.evan.blog.pojo;

import java.util.Date;

public class VisitorRecord {
    public final static Integer PASSAGER = 0;
    public final static Integer USER     = 1;
    private Integer visitorType;

    private String name;

    private String ipAddress;

    private String region;

    private String city;

    private Date visitTime;

    public Integer getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(Integer visitorType) {
        this.visitorType = visitorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    @Override
    public String toString() {
        return visitorType + ":"
                + name + ":"
                + region + ":"
                + city + ":";
    }
}
