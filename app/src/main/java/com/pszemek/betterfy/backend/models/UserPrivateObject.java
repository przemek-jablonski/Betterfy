package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciemek on 20/06/16.
 */
public class UserPrivateObject {

    //can be null sometimes (not present in json)
    @SerializedName("birthdate")
    @Expose
    public String birthdate;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("display_name")
    @Expose
    public String displayName;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("external_urls")
    @Expose
    public ExternalUrlObject externalUrl;

    @SerializedName("followers")
    @Expose
    public FollowersObject followers;

    @SerializedName("href")
    @Expose
    public String href;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("images")
    @Expose
    public List<ImageObject> images = new ArrayList<ImageObject>();

    @SerializedName("product")
    @Expose
    public String product;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("uri")
    @Expose
    public String uri;
}
