package com.pszemek.betterfy.backend.models.simplified;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ciemek on 27/06/16.
 */
public class TrackHrefObject {

    @SerializedName("href")
    @Expose
    public String href;

    @SerializedName("total")
    @Expose
    public int totalTracks;

}
