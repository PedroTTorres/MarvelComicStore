package com.marvel.myapplication.interfaces;

import com.marvel.myapplication.modules.ComicData;
import com.marvel.myapplication.modules.ComicList;
import com.marvel.myapplication.modules.DataWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Comics {

    @GET("/v1/public/comics")
    Call<DataWrapper<ComicData>> ListofComics(@Query("limit") int limit
            , @Query("offset") int offset
            , @Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature
            , @Query("name") String name);

}
