package com.example.makrandpawar.intentfilterdemo.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MAKRAND PAWAR on 8/10/2017.
 */

public class RetrofitService {
    Retrofit retrofit;
    public Retrofit getRetrofitInstance(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://public-api.wordpress.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
