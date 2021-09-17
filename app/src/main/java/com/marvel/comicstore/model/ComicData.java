package com.marvel.comicstore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ComicData implements Serializable {

        Locale usa = new Locale("en", "US");
        Currency dollars = Currency.getInstance(usa);
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        @SerializedName("resourceURI")
        private String mResourceURI;

        @SerializedName("title")
        private String mTitle;

        @SerializedName("type")
        private String mType;

        @SerializedName("prices")
        private List<ComicPrice> mPrices;

        @SerializedName("images")
        private List<Image> mImages;

        @SerializedName("thumbnail")
        private Image thumbnail;

        @SerializedName("description")
        private String mDescription;

        public String getTitle() {
                return mTitle;
        }

        public List<ComicPrice> getPrices() {
                return mPrices;
        }

        public Float getLeastPrice() {
                List<ComicPrice> prices = getPrices();
                Float leastPrice = Float.MAX_VALUE;
                for (ComicPrice price : prices) {
                        if (price.getPrice() < leastPrice) {
                                leastPrice = price.getPrice();
                        }
                }
                return leastPrice;
        }

        public String getPrice(){
                return dollarFormat.format(getLeastPrice());
        }

        public Image getThumbnail() {
                return thumbnail;
        }

        public String getmDescription(){
                return mDescription;
        }


}





