package com.dabbssolutions.farmwalayuser.model;

//import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class admins implements Serializable {


    private int adminid;
    private String password;
    private String firstname;
    private String lastname;
    private String cnic;
    private String phone;
    private int isSuperUser;

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsSuperUser() {
        return isSuperUser;
    }

    public void setIsSuperUser(int isSuperUser) {
        this.isSuperUser = isSuperUser;
    }
}
