package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ciemek on 12/06/16.
 */
public class ExternalUrlObject {

    @SerializedName("spotify")
    @Expose
    public String externalUrl;

}
