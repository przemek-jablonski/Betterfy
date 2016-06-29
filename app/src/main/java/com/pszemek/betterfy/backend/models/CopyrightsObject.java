package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ciemek on 29/06/16.
 */
public class CopyrightsObject {

    @SerializedName("text")
    @Expose
    public String copyrightText;

    @SerializedName("type")
    @Expose
    public String copyrightType;

}
