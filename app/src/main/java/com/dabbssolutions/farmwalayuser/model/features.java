package com.dabbssolutions.farmwalayuser.model;

import java.io.Serializable;

public class features  implements Serializable {
    private int FeatureID;
    private String FeatureName;

    public int getFeatureID() {
        return FeatureID;
    }

    public void setFeatureID(int featureID) {
        FeatureID = featureID;
    }

    public String getFeatureName() {
        return FeatureName;
    }

    public void setFeatureName(String featureName) {
        FeatureName = featureName;
    }
}
