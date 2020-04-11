package com.hh.www.entinity;

/**
 * @author: huhao
 * @time: 2020/4/10 8:21
 * @desc:
 */
public class Address {

    private String location;
    private String zipcode;

    public Address() {
    }

    public Address(String location, String zipcode) {
        this.location = location;
        this.zipcode = zipcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "location='" + location + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
