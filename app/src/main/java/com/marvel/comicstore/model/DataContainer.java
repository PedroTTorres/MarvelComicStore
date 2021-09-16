package com.marvel.comicstore.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataContainer<E> {
    @SerializedName("offset")
    private int mOffset;

    @SerializedName("limit")
    private int mLimit;

    @SerializedName("total")
    private int mTotal;

    @SerializedName("count")
    private int mCount;

    @SerializedName("results")
    private List<E> mResults;

    public List<E> getResults() {
        return mResults;
    }
}