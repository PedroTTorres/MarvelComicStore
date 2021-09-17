package com.marvel.comicstore.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image implements Serializable {

    final String RESOLUTION = "/portrait_incredible";

    @SerializedName("path")
    private String mPath;

    @SerializedName("extension")
    private String mExtension;

    public String getmPath() {
        return mPath.replace("http://", "https://");
    }

    public String getmExtension() {
        return mExtension;
    }

    public String getUrl() {
        return getmPath()+RESOLUTION+"."+getmExtension();
    }



}