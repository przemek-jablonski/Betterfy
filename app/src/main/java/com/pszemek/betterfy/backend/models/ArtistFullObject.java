package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciemek on 30/06/16.
 */
public class ArtistFullObject implements TopObject {

    @SerializedName("external_urls")
    @Expose
    public ExternalUrlObject externalUrls;

    @SerializedName("followers")
    @Expose
    public FollowersObject followers;

    @SerializedName("genres")
    @Expose
    public List<String> genres = new ArrayList<>();

    @SerializedName("href")
    @Expose
    public String href;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("images")
    @Expose
    public List<ImageObject> images = new ArrayList<>();

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("popularity")
    @Expose
    public int popularity;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("uri")
    @Expose
    public String uri;

}
