package com.dabbssolutions.farmwalayuser.model;

import java.io.Serializable;

public class guesthousefeatures implements Serializable {

    private int guesthouseid;
    private int featureid;
    private String FeatureName;


    public int getGuesthouseid() {
        return guesthouseid;
    }

    public void setGuesthouseid(int guesthouseid) {
        this.guesthouseid = guesthouseid;
    }

    public int getFeatureid() {
        return featureid;
    }

    public void setFeatureid(int featureid) {
        this.featureid = featureid;
    }

    public String getFeaturename() {
        return FeatureName;
    }

    public void setFeaturename(String FeatureName) {
        this.FeatureName = FeatureName;
    }
}
