package com.marvel.comicstore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Thumbnail implements Serializable {
    @SerializedName("path")
    private String mPath;

    @SerializedName("extension")
    private String mExtension;

    public String getmPath() {
        return mPath;
    }

    public String getmExtension() {
        return mExtension;
    }


    public String getUrl() {
        if (getmPath() == null || getmExtension() == null) {
            return null;
        }
        return String.format("%s.%s", getmPath(), getmExtension());
    }
}