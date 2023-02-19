package com.example.slyusarevpetsstore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PetAPI {
    @GET("pet/{id}")
    Call<Pets> getPets(@Path("id") String id);

    @POST("pet")
    Call<Pets> createPets(@Body Pets pet);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://petstore.swagger.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



}