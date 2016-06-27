package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ciemek on 12/06/16.
 */
public class UserPublicObject {

    @SerializedName("display_name")
    public String displayName;

    @SerializedName("external_urls")
    @Expose
    public ExternalUrlObject externalUrlObject;

    @SerializedName("href")
    @Expose
    public String href;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("uri")
    @Expose
    public String uri;
}
