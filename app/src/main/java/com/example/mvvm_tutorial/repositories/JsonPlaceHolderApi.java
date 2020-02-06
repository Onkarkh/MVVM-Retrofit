package com.example.mvvm_tutorial.repositories;

import com.example.mvvm_tutorial.model.ModelPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("post")
    Call<List<ModelPost>> getPost();

    @GET("posts")
    Call<List<ModelPost>> getPosts();
}
