package com.marvel.comicstore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ComicData implements Serializable {
        @SerializedName("resourceURI")
        private String mResourceURI;

        @SerializedName("title")
        private String mtitle;

        @SerializedName("type")
        private String mType;

        public String getTitle() {
            return mtitle;
        }



    }


