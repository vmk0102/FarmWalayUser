package com.dabbssolutions.farmwalayuser.model;

import java.io.Serializable;

public class farmhousepictures implements Serializable {
    private String image;
    private int farmhouseid;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFarmhouseid() {
        return farmhouseid;
    }

    public void setFarmhouseid(int farmhouseid) {
        this.farmhouseid = farmhouseid;
    }
}
