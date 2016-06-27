package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pszemek.betterfy.backend.models.simplified.TrackHrefObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciemek on 12/06/16.
 */
public class PlaylistObject {

    @SerializedName("collaborative")
    @Expose
    public boolean collaborative;

    @SerializedName("external_urls")
    @Expose
    public ExternalUrlObject externalUrls;

    @SerializedName("href")
    @Expose
    public String href;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("images")
    @Expose
    public List<ImageObject> images = new ArrayList<ImageObject>();

    @SerializedName("name")
    @Expose
    public String playlistName;

    @SerializedName("owner")
    @Expose
    public UserPublicObject user;

    @SerializedName("public")
    @Expose
    public boolean isPublic;

    @SerializedName("snapshot_id")
    @Expose
    public String snapshotId;

    @SerializedName("tracks")
    @Expose
    public TrackHrefObject tracks;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("uri")
    @Expose
    public String uri;

}
