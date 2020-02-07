package com.example.mvvm_tutorial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvvm_tutorial.adapter.RecyclerAdapter;
import com.example.mvvm_tutorial.model.ModelPost;
import com.example.mvvm_tutorial.repositories.PostRepository;
import com.example.mvvm_tutorial.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public RecyclerAdapter mRecyclerAdapter;
    public MainActivityViewModel mainActivityViewModel;
    ArrayList<ModelPost> modelPostArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getPost().observe(this, new Observer<List<ModelPost>>() {
            @Override
            public void onChanged(List<ModelPost> modelPosts) {
                initRecyclerView(modelPosts);
                mRecyclerAdapter.notifyDataSetChanged();
            }
        });
        initRecyclerView(mainActivityViewModel.getPost().getValue());
        getAllPost();
    }

    private void initRecyclerView(List<ModelPost> value) {
        mRecyclerAdapter = new RecyclerAdapter(value, this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    private void getAllPost() {
        mainActivityViewModel.getPost().observe(this, modelPosts -> mRecyclerAdapter.setPostsList((ArrayList<ModelPost>) modelPosts));
    }
}
