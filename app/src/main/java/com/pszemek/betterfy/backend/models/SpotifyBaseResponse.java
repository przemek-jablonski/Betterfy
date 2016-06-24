package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciemek on 23/06/16.
 */
public class SpotifyBaseResponse<T> {

    @SerializedName("href")
    @Expose
    public String href;

    @SerializedName("items")
    private List<T> items = new ArrayList<T>();

    @SerializedName("limit")
    @Expose
    public int limit;

    @SerializedName("next")
    @Expose
    public String next;

    @SerializedName("offset")
    @Expose
    public int offset;

    @SerializedName("previous")
    @Expose
    public String previous;

    @SerializedName("total")
    @Expose
    public int total;


    public List<T> getItems() {
        return items;
    }

}
