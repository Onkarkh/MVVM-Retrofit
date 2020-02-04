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
    private ArrayList<Post> recyclerArrayList = new ArrayList<>();

    private PostRepository(){
        fetchData();
    }

    private void fetchData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi placeApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = placeApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> jsonResponse = response.body();

                for (int i = 0; i < 2; i++) {
                    Post item = jsonResponse.get(i);
                    recyclerArrayList.add(new Post(item.getUserId(), item.getId(), item.getTitle(), item.getBody()));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + t.getMessage());
            }
        });

    }

    public MutableLiveData<List<Post>> getPost() {
        MutableLiveData<List<Post>> data = new MutableLiveData<>();
        data.setValue(recyclerArrayList);
        if(recyclerArrayList==null){
            fetchData();
            return data;
        } else {
            fetchData();
            return data;
        }
    }

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }
}
