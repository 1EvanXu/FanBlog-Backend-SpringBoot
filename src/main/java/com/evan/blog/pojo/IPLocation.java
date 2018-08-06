package com.evan.blog.pojo;

public class IPLocation {
    private String continent;
    private String region;
    private String city;

    public IPLocation() {
    }

    public IPLocation(String continent, String region, String city) {
        this.continent = continent;
        this.region = region;
        this.city = city;
    }

    public String getContinent() {
        return continent != null && !continent.equals("") ? continent : "其他";
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region != null && !region.equals("") ? region : "其他";
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city != null && !city.equals("") ? city : "其他";
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return getCity() + ":" +  getCity() + ":" + getContinent();
    }
}
