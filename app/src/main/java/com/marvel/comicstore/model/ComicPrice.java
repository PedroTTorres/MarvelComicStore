package com.marvel.comicstore.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ComicPrice implements Comparable<ComicPrice> {

    @SerializedName("type")
    private String mType;

    @SerializedName("price")
    private float mPrice;

    public String getType() {
        return mType;
    }
    public float getPrice() {
        return mPrice;
    }

    @Override
    public int compareTo(@NonNull ComicPrice otherPrice) {
        if (mPrice > otherPrice.mPrice) {
            return 1;
        } else if (mPrice < otherPrice.mPrice) {
            return -1;
        } else {
            return 0;
        }
    }

}
