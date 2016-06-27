package com.pszemek.betterfy.backend.models.simplified;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pszemek.betterfy.backend.models.ExternalUrlObject;
import com.pszemek.betterfy.backend.models.ImageObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciemek on 20/06/16.
 */
public class AlbumSimpleObject {

    @SerializedName("album_type")
    @Expose
    public String albumType;

    @SerializedName("available_markets")
    public List<String> availableMarkets = new ArrayList<>();

    @SerializedName("external_urls")
    @Expose
    public ExternalUrlObject externalUrl;

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

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("uri")
    @Expose
    public String uri;

}
