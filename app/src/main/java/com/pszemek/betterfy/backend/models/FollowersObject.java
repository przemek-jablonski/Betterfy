package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ciemek on 20/06/16.
 */
public class FollowersObject {

    /**
     * A link to the Web API endpoint providing full details of the followersObject;
     * null if not available.
     *
     * !!This will always be set to NULL, as the Web API does not support it at the moment.
     *
     */
    @SerializedName("href")
    @Expose
    public Object href;

    @SerializedName("total")
    @Expose
    public int totalFollowers;

}
