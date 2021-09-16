package com.marvel.comicstore.model;

    import com.google.gson.annotations.SerializedName;

    import java.io.Serializable;
    import java.util.List;

    public class ComicList implements Serializable {
        @SerializedName("available")
        private int mAvailable;

        @SerializedName("returned")
        private int mReturned;

        @SerializedName("collectionURI")
        private String mCollectionURI;

        @SerializedName("items")
        private List<ComicData> mItems;

        public List<ComicData> getItems() {
            return mItems;
        }

}
