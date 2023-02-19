package com.example.slyusarevpetsstore;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPI {
    @GET("user/{username}")
    Call<User> getUsers(@Path("username") String username);

    @POST("user")
    Call<User> createUsers(@Body User user);

    Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("https://petstore.swagger.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}