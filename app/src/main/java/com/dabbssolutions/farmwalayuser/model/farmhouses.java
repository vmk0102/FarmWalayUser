package com.dabbssolutions.farmwalayuser.model;

import java.io.Serializable;

public class farmhouses  implements Serializable {
    private int farmhouseid;
    private String farmname;
    private String farmlocation;
    private String farmprice;
    private int adminid;
    private String farmpic;

    public int getFarmhouseid() {
        return farmhouseid;
    }

    public void setFarmhouseid(int farmhouseid) {
        this.farmhouseid = farmhouseid;
    }

    public String getFarmname() {
        return farmname;
    }

    public void setFarmname(String farmname) {
        this.farmname = farmname;
    }

    public String getFarmlocation() {
        return farmlocation;
    }

    public void setFarmlocation(String farmlocation) {
        this.farmlocation = farmlocation;
    }

    public String getFarmprice() {
        return farmprice;
    }

    public void setFarmprice(String farmprice) {
        this.farmprice = farmprice;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getFarmpic() {
        return farmpic;
    }

    public void setFarmpic(String farmpic) {
        this.farmpic = farmpic;
    }
}
