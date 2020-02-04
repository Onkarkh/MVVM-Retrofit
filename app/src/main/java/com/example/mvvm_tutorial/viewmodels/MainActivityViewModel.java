package com.example.mvvm_tutorial.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_tutorial.model.Post;
import com.example.mvvm_tutorial.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Post>> mPost;
    private PostRepository mPostRepository;

    public void init() {
        if (mPost != null) {
            return;
        }
        mPostRepository = PostRepository.getInstance();
        mPost = mPostRepository.getPost();
    }

    public LiveData<List<Post>> getPost() {
        return mPost;
    }
}
