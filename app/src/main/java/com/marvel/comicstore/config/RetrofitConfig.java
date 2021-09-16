package com.marvel.comicstore.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marvel.comicstore.interfaces.Comics;

import java.lang.reflect.Modifier;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .create();

        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        }

    public Comics getComicsService(){
        return this.retrofit.create(Comics.class);


    }









}
