package com.example.mvvm_tutorial.repositories;

import com.example.mvvm_tutorial.model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {

    @GET("post")
    Class<List<Post>> getPost(
            @Query("userId") Integer[] userId
    );

    @GET("posts")
    Call<List<Post>> getPost(@QueryMap Map<String, String> parameters);
}
