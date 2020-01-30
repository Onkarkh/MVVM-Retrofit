package com.example.mvvm_tutorial.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_tutorial.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {

    private static PostRepository instance;
    private ArrayList<Post> dataSet = new ArrayList<>();

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Post>> getPost() {
        fetchData();
        //setNicePlaces();
        MutableLiveData<List<Post>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }


    private void setNicePlaces(){
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good\n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good\n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good \n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good \n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good \n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good \n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good \n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good \n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good \n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good \n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good \n\n")
        );
        dataSet.add(
                new Post(1,2,"Good Job","Job is Good \n\n")
        );
    }


    public void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Map<String, String> parameter = new HashMap<>();
        parameter.put("userId", "2");
        parameter.put("_sort", "id");
        parameter.put("order", "_desc");

        Call<List<Post>> call = jsonPlaceHolderApi.getPost(parameter);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Post> posts = response.body();
                for (Post item : Objects.requireNonNull(posts)) {
                    dataSet.add(new Post(item.getUserId(), item.getId(), item.getTitle(), item.getText()));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
