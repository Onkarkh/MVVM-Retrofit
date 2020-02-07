package com.example.mvvm_tutorial.model;

import java.util.List;

public class PostResponse {
    private List<ModelPost> postObject = null;

    public List<ModelPost> getPostObject() {
        return postObject;
    }

    public void setPostObject(List<ModelPost> postObject) {
        this.postObject = postObject;
    }
}
