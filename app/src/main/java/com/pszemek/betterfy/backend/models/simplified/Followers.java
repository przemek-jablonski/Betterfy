package com.pszemek.betterfy.backend.models.simplified;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ciemek on 20/06/16.
 */
public class Followers {
    @SerializedName("href")
    @Expose
    public Object href;

    @SerializedName("total")
    @Expose
    public int total;

}
