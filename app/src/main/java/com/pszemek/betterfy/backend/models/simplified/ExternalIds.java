package com.pszemek.betterfy.backend.models.simplified;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ciemek on 20/06/16.
 */
public class ExternalIds {
    @SerializedName("isrc")
    @Expose
    public String isrc;

}
