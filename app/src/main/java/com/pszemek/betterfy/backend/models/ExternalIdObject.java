package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ciemek on 20/06/16.
 */
public class ExternalIdObject {

    @SerializedName(value = "id", alternate = {"ean", "upc"})
    @Expose
    public String id;

}
