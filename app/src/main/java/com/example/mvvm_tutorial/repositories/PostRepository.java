package com.example.mvvm_tutorial.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_tutorial.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {

    private static final String TAG = "PostRepository";

    private static PostRepository instance;
    private ArrayList<Post> dataSet = new ArrayList<>();

    JsonPlaceHolderApi placeApi;

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Post>> getPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        placeApi = retrofit.create(JsonPlaceHolderApi.class);
        MutableLiveData<List<Post>> data = new MutableLiveData<>();
        fetchData();
        data.setValue(dataSet);
        return data;
    }

    private void fetchData() {


        Call<List<Post>> call = placeApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Log.d(TAG, "*************************************************** " + response.body());
                List<Post> jsonResponse = response.body();
                jsonParser(jsonResponse);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + t.getMessage());
            }
        });

    }

    private void jsonParser(List<Post> res) {
        ArrayList<Post> recyclerArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Post item = res.get(i);
            Post post = new Post();
            post.setUserId(item.getUserId());
            post.setId(item.getId());
            post.setTitle(item.getTitle());
            post.setBody(item.getBody());
            recyclerArrayList.add(post);
        }
        dataSet.addAll(recyclerArrayList);
    }
}
