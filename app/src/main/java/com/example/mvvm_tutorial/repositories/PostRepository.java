package com.example.mvvm_tutorial.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_tutorial.model.ModelPost;
import com.example.mvvm_tutorial.model.PostResponse;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {

    private static final String TAG = "PostRepository";

    private static PostRepository instance;

    private JsonPlaceHolderApi jsonPlaceHolderApi;


    public PostRepository() {
        jsonPlaceHolderApi = RetrofitService.createService(JsonPlaceHolderApi.class);
    }

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    public MutableLiveData<List<ModelPost>> getPost() {
        MutableLiveData<List<ModelPost>> data = new MutableLiveData<>();
        jsonPlaceHolderApi.getPosts().enqueue(new Callback<List<ModelPost>>() {
            @Override
            public void onResponse(Call<List<ModelPost>> call, Response<List<ModelPost>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelPost>> call, Throwable t) {

            }
        });
        return data;
    }
}
