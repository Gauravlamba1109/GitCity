package com.example.githouse.api;

import com.example.githouse.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Service {
    @GET
    Call<ItemResponse>getItems(@Url String input);

}
