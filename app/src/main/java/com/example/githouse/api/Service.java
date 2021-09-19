package com.example.githouse.api;

import com.example.githouse.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Service {
    @GET("/search/users?q=location:Delhi")
    Call<ItemResponse>getItems();

}
