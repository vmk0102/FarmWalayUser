package com.dabbssolutions.farmwalayuser.model;

import java.io.Serializable;

public class guesthousepictures implements Serializable {
    private String image;
    private int guesthouseid;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFarmhouseid() {
        return guesthouseid;
    }

    public void setFarmhouseid(int farmhouseid) {
        this.guesthouseid = farmhouseid;
    }
}
