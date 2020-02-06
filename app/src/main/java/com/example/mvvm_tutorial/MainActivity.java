package com.example.mvvm_tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvvm_tutorial.adapter.RecyclerAdapter;
import com.example.mvvm_tutorial.model.ModelPost;
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
        mainActivityViewModel.getPost().observe(this, modelPosts -> {
            List<ModelPost> modelPost = modelPosts;
            modelPostArrayList.addAll(modelPost);
            mRecyclerAdapter.notifyDataSetChanged();
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerAdapter = new RecyclerAdapter(mainActivityViewModel.getPost().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
}
