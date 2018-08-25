package com.evan.blog.pojo;

public class IPLocation {
    private String country;
    private String province;
    private String city;

    public IPLocation() {
    }

    public IPLocation(String country, String province, String city) {
        this.country = country;
        this.province = province;
        this.city = city;
    }

    public String getCountry() {
        return country != null && !country.equals("") ? country : "其他";
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province != null && !province.equals("") ? province : "其他";
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city != null && !city.equals("") ? city : "其他";
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return getCity() + ":" +  getProvince() + ":" + getCountry();
    }
}
