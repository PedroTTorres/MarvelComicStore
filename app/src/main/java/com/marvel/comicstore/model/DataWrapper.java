package com.marvel.comicstore.model;

import com.google.gson.annotations.SerializedName;

    public class DataWrapper<E> {
        @SerializedName("code")
        private int mCode;

        @SerializedName("status")
        private String mStatus;

        @SerializedName("copyright")
        private String mCopyright;

        @SerializedName("attributionText")
        private String mAttributionText;

        @SerializedName("attributionHTML")
        private String mAttributionHTML;

        @SerializedName("etag")
        private String mEtag;

        @SerializedName("data")
        private DataContainer<E> mData;

        public int getCode() {
            return mCode;
        }

        public void setCode(int code) {
            mCode = code;
        }

        public DataContainer<E> getData() {
            return mData;
        }
}
