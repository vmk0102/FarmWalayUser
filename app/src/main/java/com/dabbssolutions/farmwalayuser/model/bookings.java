package com.dabbssolutions.farmwalayuser.model;

import java.io.Serializable;

public class bookings implements Serializable {

    private int bid;
    private String bookingdate;
    private String bookingtime;
    private int uid;
    private int farmhouseid;
    private double bookingprice;
    private int isConfirmed;




    public int getBid() {
        return bid;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public String getBookingtime() {
        return bookingtime;
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

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
    }

    public void setBookingtime(String bookingtime) {
        this.bookingtime = bookingtime;
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
}

