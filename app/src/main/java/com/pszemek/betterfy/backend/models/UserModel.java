package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pszemek.betterfy.backend.models.simplified.ExternalUrls;
import com.pszemek.betterfy.backend.models.simplified.Followers;
import com.pszemek.betterfy.backend.models.simplified.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciemek on 20/06/16.
 */
public class UserModel {

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
    public ExternalUrls externalUrls;

    @SerializedName("followers")
    @Expose
    public Followers followers;

    @SerializedName("href")
    @Expose
    public String href;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("images")
    @Expose
    public List<Image> images = new ArrayList<Image>();

    @SerializedName("product")
    @Expose
    public String product;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("uri")
    @Expose
    public String uri;

    public String spotifyAccessToken;

}
