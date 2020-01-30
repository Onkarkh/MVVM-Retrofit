package com.example.mvvm_tutorial.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_tutorial.R;
import com.example.mvvm_tutorial.model.Post;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private List<Post> mPost = new ArrayList<>();
    private Context context;

    public RecyclerAdapter(List<Post> mPost,Context context) {
        this.mPost = mPost;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_recycleview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).mUserId.setText(Integer.toString(mPost.get(position).getUserId()));
        ((ViewHolder) holder).mId.setText(Integer.toString(mPost.get(position).getId()));
        ((ViewHolder) holder).mTitle.setText(mPost.get(position).getTitle());
        ((ViewHolder) holder).mText.setText(mPost.get(position).getText());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+mPost.size());
        return mPost.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mUserId, mId, mTitle, mText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserId = itemView.findViewById(R.id.userId);
            mId = itemView.findViewById(R.id.id);
            mTitle = itemView.findViewById(R.id.title);
            mText = itemView.findViewById(R.id.text);
        }
    }
}
