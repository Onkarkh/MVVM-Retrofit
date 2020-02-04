package com.example.mvvm_tutorial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mvvm_tutorial.adapter.RecyclerAdapter;
import com.example.mvvm_tutorial.model.Post;
import com.example.mvvm_tutorial.repositories.PostRepository;
import com.example.mvvm_tutorial.repositories.RetrofitService;
import com.example.mvvm_tutorial.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public RecyclerAdapter mRecyclerAdapter;
    public MainActivityViewModel mainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getPost().observe(this, posts -> mRecyclerAdapter.notifyDataSetChanged());

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerAdapter = new RecyclerAdapter(mainActivityViewModel.getPost().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
}
