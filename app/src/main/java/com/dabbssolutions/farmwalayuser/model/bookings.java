package com.dabbssolutions.farmwalayuser.model;

import java.io.Serializable;

public class bookings implements Serializable {

    private int bid;
    private String checkindate;
    private String checkoutdate;
    private int uid;
    private int farmhouseid;
    private double bookingprice;
    private int isConfirmed;
    private int isDeleted;
    private int guesthouseid;
    private String static_custnumber;
    private String static_custname;
    private String FullName;
    private String FarmhouseName;
    private String GuesthouseName;
    private String Location;
    private String Phone;
    private float Price;
    private String CheckinDate;
    private String CheckoutDate;
    private String StaticCustomer;
    private String StaticPhone;




    public int getGuesthouseid() {
        return guesthouseid;
    }

    public void setGuesthouseid(int guesthouseid) {
        this.guesthouseid = guesthouseid;
    }





    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }



    public int getBid() {
        return bid;
    }


    public int getUid() {
        return uid;
    }

    public int getFarmhouseid() {
        return farmhouseid;
    }

    public double getBookingprice() {
        return bookingprice;
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }



    public void setBid(int bid) {
        this.bid = bid;
    }


    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setFarmhouseid(int farmhouseid) {
        this.farmhouseid = farmhouseid;
    }

    public void setBookingprice(double bookingprice) {
        this.bookingprice = bookingprice;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public String getStatic_custnumber() {
        return static_custnumber;
    }

    public void setStatic_custnumber(String static_custnumber) {
        this.static_custnumber = static_custnumber;
    }

    public String getStatic_custname() {
        return static_custname;
    }

    public void setStatic_custname(String static_custname) {
        this.static_custname = static_custname;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getFarmhouseName() {
        return FarmhouseName;
    }

    public void setFarmhouseName(String farmhouseName) {
        FarmhouseName = farmhouseName;
    }

    public String getGuesthouseName() {
        return GuesthouseName;
    }

    public void setGuesthouseName(String guesthouseName) {
        GuesthouseName = guesthouseName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getCheckinDate() {
        return CheckinDate;
    }

    public void setCheckinDate(String checkinDate) {
        CheckinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return CheckoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        CheckoutDate = checkoutDate;
    }

    public String getStaticCustomer() {
        return StaticCustomer;
    }

    public void setStaticCustomer(String staticCustomer) {
        StaticCustomer = staticCustomer;
    }

    public String getStaticPhone() {
        return StaticPhone;
    }

    public void setStaticPhone(String staticPhone) {
        StaticPhone = staticPhone;
    }
}

