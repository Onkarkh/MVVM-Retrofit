package com.example.mvvm_tutorial.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_tutorial.model.ModelPost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {

    private static final String TAG = "PostRepository";

    private static PostRepository instance;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    List<ModelPost> dataSet = new ArrayList<>();

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
        //setPostStatic();
        setPostDynamic();
        MutableLiveData<List<ModelPost>> data = new MutableLiveData<>();
        data.postValue(dataSet);
        return data;
    }

    private void setPostDynamic() {
        jsonPlaceHolderApi.getPosts().enqueue(new Callback<List<ModelPost>>() {

            @Override
            public void onResponse(Call<List<ModelPost>> call, Response<List<ModelPost>> response) {
                List<ModelPost> localDataSet = new ArrayList<>();
                List<ModelPost> serverResponse = response.body();

                for (int i = 0; i < serverResponse.size(); i++) {
                    ModelPost item = serverResponse.get(i);
                    ModelPost modelPost = new ModelPost();
                    String userId = item.getUserId();
                    String id = item.getId();
                    String title = item.getTitle();
                    String body = item.getBody();
                    modelPost.setUserId(userId);
                    modelPost.setId(id);
                    modelPost.setTitle(title);
                    modelPost.setBody(body);
                    dataSet.add(modelPost);
                }
            }

            @Override
            public void onFailure(Call<List<ModelPost>> call, Throwable t) {

            }
        });
    }

    private void setPostStatic() {
        ModelPost modelPost = new ModelPost();
        modelPost.setUserId("23");
        modelPost.setId("4");
        modelPost.setTitle("Test Title");
        modelPost.setBody("Test Body");
        dataSet.add(modelPost);
    }

}
