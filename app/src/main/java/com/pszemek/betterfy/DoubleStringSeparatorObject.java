package com.pszemek.betterfy;

import com.pszemek.betterfy.backend.models.TopObject;

/**
 * Created by Ciemek on 30/06/16.
 */
public class DoubleStringSeparatorObject implements TopObject {

    public String leftString;
    public String rightString;

    public DoubleStringSeparatorObject(String leftString, String rightString) {
        this.leftString = leftString;
        this.rightString = rightString;
    }
}
