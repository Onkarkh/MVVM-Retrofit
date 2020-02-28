package com.example.mvvm_tutorial;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_tutorial.adapter.RecyclerAdapter;
import com.example.mvvm_tutorial.model.ModelPost;
import com.example.mvvm_tutorial.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public RecyclerAdapter mRecyclerAdapter;
    public MainActivityViewModel mainActivityViewModel;
    ArrayList<ModelPost> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getPost().observe(this, modelPosts -> {
            List<ModelPost> item = mainActivityViewModel.getPost().getValue();
            postList.addAll(item);
            mRecyclerAdapter.notifyDataSetChanged();
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        if (mRecyclerAdapter == null) {
            mRecyclerAdapter = new RecyclerAdapter(postList, this);
            RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(mRecyclerAdapter);
        } else {
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }
}