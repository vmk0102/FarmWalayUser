package com.dabbssolutions.farmwalayuser.model;

import java.io.Serializable;

public class guesthouses  implements Serializable {
    private int guesthouseid;
    private String guesthouselocation;
    private String guesthousename;
    private float guesthouseprice;
    private int adminid;
    private String guestpic;

    public int getGuesthouseid() {
        return guesthouseid;
    }

    public void setGuesthouseghid(int guesthouseid) {
        this.guesthouseid = guesthouseid;
    }

    public String getGuesthouselocation() {
        return guesthouselocation;
    }

    public void setGuesthouselocation(String guesthouselocation) {
        this.guesthouselocation = guesthouselocation;
    }

    public String getGuesthousename() {
        return guesthousename;
    }

    public void setGuesthousename(String guesthousename) {
        this.guesthousename = guesthousename;
    }

    public float getGuesthouseprice() {
        return guesthouseprice;
    }

    public void setGuesthouseprice(float guesthouseprice) {
        this.guesthouseprice = guesthouseprice;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public void setGuesthouseid(int guesthouseid) {
        this.guesthouseid = guesthouseid;
    }

    public String getGuestpic() {
        return guestpic;
    }

    public void setGuestpic(String guestpic) {
        this.guestpic = guestpic;
    }
}

