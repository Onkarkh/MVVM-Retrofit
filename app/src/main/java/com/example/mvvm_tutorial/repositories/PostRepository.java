package com.example.mvvm_tutorial.repositories;

import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_tutorial.MainActivity;
import com.example.mvvm_tutorial.adapter.RecyclerAdapter;
import com.example.mvvm_tutorial.model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {

    private static final String TAG = "PostRepository";

    private static PostRepository instance;
    private List<Post> dataSet = new ArrayList<>();
    private List<Post> items = new ArrayList<>();

    JsonPlaceHolderApi placeApi;

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Post>> getPost() {
        //setNicePlaces();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        placeApi = retrofit.create(JsonPlaceHolderApi.class);
        MutableLiveData<List<Post>> data = new MutableLiveData<>();
        fetchData();
        setData();
        data.setValue(dataSet);
        return data;
    }

    private void setData() {

    }


    private void setNicePlaces() {
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good\n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good\n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good \n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good \n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good \n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good \n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good \n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good \n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good \n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good \n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good \n\n")
        );
        dataSet.add(
                new Post(1, 2, "Good Job", "Job is Good \n\n")
        );
    }

    private void fetchData() {

        Map<String, String> parameter = new HashMap<>();
        parameter.put("userId", "2");
        parameter.put("_sort", "id");
        parameter.put("order", "_desc");

        Call<List<Post>> call = placeApi.getPosts(parameter);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                Log.d(TAG, "Server Response: " + response.body().toString());
                Log.d(TAG, "Response " + response.toString());


                items = response.body();

//                for (int i = 0; i < items.size(); i++) {
//                    Post data = items.get(i);
//                    Log.d(TAG, "setData: " + data.getBody());
//                    dataSet.add(new Post(data.getUserId(), data.getId(), data.getTitle(), data.getBody()));
//                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });

    }
}
