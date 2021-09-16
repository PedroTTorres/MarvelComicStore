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
        private List<ComicPrice> prices;

        public String getTitle() {
                return mTitle;
        }

        public List<ComicPrice> getPrices() {
                return prices;
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


}





