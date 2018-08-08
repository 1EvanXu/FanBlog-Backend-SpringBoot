package com.evan.blog.pojo;

public class VisitorRecord {
    public final static Integer PASSAGER = 0;
    public final static Integer USER     = 1;
    private Integer visitorType;

    private String name;

    private String ipAddress;

    private IPLocation ipLocation;

    private long visitTime;

    public VisitorRecord() {
    }

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

    public long getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(long visitTime) {
        this.visitTime = visitTime;
    }

    public IPLocation getIpLocation() {
        return ipLocation;
    }

    public void setIpLocation(IPLocation ipLocation) {
        this.ipLocation = ipLocation;
    }

    public String getRecord () {
        return getVisitorType() + ":" + name + ":" + ipAddress + ":" + ipLocation.toString();
    }

    public String getArticleVisitRecord () {
        return getVisitorType() + ":" + name + ":" + ipAddress;
    }
}
