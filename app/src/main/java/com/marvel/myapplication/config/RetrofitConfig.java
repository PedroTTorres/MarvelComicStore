package com.marvel.myapplication.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
                .baseUrl("http://gateway.marvel.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }





}
