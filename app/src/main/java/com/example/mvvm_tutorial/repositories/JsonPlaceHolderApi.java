package com.example.mvvm_tutorial.repositories;

import com.example.mvvm_tutorial.model.ModelPost;
import com.example.mvvm_tutorial.model.PostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<ModelPost>> getPosts();
}
