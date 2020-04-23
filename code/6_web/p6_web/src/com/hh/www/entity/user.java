package com.hh.www.entity;

/**
 * @author: huhao
 * @time: 2020/4/23 10:23
 * @desc:
 */
public class user {


    /**
     * state : success
     * code : 0
     * data : {"acc":100,"city":"北京市","dist":"通州区","addr":"北京市通州区永乐店镇北京金篮子生态种植有限公司","prov":"北京市","lon":116.82106018,"number":"13","town":"永乐店镇","street":"永乐大街","lat":39.69581985}
     */

    private String state;
    private String code;
    private DataBean data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * acc : 100
         * city : 北京市
         * dist : 通州区
         * addr : 北京市通州区永乐店镇北京金篮子生态种植有限公司
         * prov : 北京市
         * lon : 116.82106018
         * number : 13
         * town : 永乐店镇
         * street : 永乐大街
         * lat : 39.69581985
         */

        private int acc;
        private String city;
        private String dist;
        private String addr;
        private String prov;
        private double lon;
        private String number;
        private String town;
        private String street;
        private double lat;

        public int getAcc() {
            return acc;
        }

        public void setAcc(int acc) {
            this.acc = acc;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}
