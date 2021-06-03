package com.dabbssolutions.farmwalayuser.model;

import java.io.Serializable;

public class farmhousefeatures implements Serializable {
    private int featureid;
    private int farmhouseid;
    private String farmname;
    private double farmprice;
    private String farmlocation;
    private String FeatureName;


    public int getFeatureid() {
        return featureid;
    }

    public void setFeatureid(int featureid) {
        this.featureid = featureid;
    }

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

    public double getFarmprice() {
        return farmprice;
    }

    public void setFarmprice(double farmprice) {
        this.farmprice = farmprice;
    }

    public String getFarmlocation() {
        return farmlocation;
    }

    public void setFarmlocation(String farmlocation) {
        this.farmlocation = farmlocation;
    }

    public String getFeatureName() {
        return FeatureName;
    }

    public void setFeatureName(String featureName) {
        FeatureName = featureName;
    }
}
