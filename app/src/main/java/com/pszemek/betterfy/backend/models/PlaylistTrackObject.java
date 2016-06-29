package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ciemek on 26/06/16.
 */
public class PlaylistTrackObject {

    @SerializedName("added_at")
    @Expose
    public String addedAt;

    @SerializedName("added_by")
    @Expose
    public UserPublicObject addedBy;

    @SerializedName("is_local")
    @Expose
    public boolean isLocal;

    @SerializedName("track")
    @Expose
    public TrackFullObject track;
}
