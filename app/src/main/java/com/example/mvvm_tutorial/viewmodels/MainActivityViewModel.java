package com.example.mvvm_tutorial.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_tutorial.model.ModelPost;
import com.example.mvvm_tutorial.repositories.PostRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<ModelPost>> mPost;
    private PostRepository mPostRepository;

    public void init() {
        if (mPost != null) {
            return;
        }
        mPostRepository = PostRepository.getInstance();
        mPost = mPostRepository.getPost();
    }

    public LiveData<List<ModelPost>> getPost() {
        return mPost;
    }
}
