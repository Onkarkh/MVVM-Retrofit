package com.example.mvvm_tutorial.repositories;

import com.example.mvvm_tutorial.model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {

    @GET("post")
    Call<List<Post>> getPost();

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);
}
