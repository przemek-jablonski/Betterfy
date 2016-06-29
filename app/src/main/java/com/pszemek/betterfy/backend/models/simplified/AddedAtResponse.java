package com.pszemek.betterfy.backend.models.simplified;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pszemek.betterfy.backend.models.AlbumFullObject;

/**
 * Created by Ciemek on 29/06/16.
 */
public class AddedAtResponse<T> {

    @SerializedName("added_at")
    @Expose
    public String addedAt;

    @SerializedName("album")
    @Expose
    public T album;
}
