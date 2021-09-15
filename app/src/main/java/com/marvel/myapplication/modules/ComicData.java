package com.marvel.myapplication.modules;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ComicData implements Serializable {
        @SerializedName("resourceURI")
        private String mResourceURI;

        @SerializedName("name")
        private String mName;

        @SerializedName("type")
        private String mType;

        public String getName() {
            return mName;
        }
    }


