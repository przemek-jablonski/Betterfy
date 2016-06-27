package com.pszemek.betterfy.backend.models.simplified;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pszemek.betterfy.backend.models.ExternalUrlObject;

/**
 * Created by Ciemek on 20/06/16.
 */
public class ArtistSimpleObject {

    @SerializedName("external_urls")
    @Expose
    public ExternalUrlObject externalUrls;

    @SerializedName("href")
    @Expose
    public String href;

    @SerializedName("id")
    @Expose
    public String id;

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
