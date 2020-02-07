package com.example.mvvm_tutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_tutorial.R;
import com.example.mvvm_tutorial.model.ModelPost;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PostAdapter> {

    private static final String TAG = "RecyclerAdapter";
    private List<ModelPost> mModelPost;
    private Context context;
    private LayoutInflater inflater;

    public RecyclerAdapter(List<ModelPost> mModelPost, Context context) {
        this.mModelPost = mModelPost;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.PostAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_recycleview, parent, false);
        PostAdapter postAdapter = new PostAdapter(view);
        return postAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter holder, int position) {
        holder.mUserId.setText(mModelPost.get(position).getUserId());
        holder.mId.setText(mModelPost.get(position).getId());
        holder.mTitle.setText(mModelPost.get(position).getTitle());
        holder.mText.setText(mModelPost.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        if (mModelPost != null) {
            return mModelPost.size();
        } else {
            return 0;
        }
    }
    public void setPostsList(ArrayList<ModelPost> mModelPost) {
        this.mModelPost = mModelPost;
        notifyDataSetChanged();
    }

    class PostAdapter extends RecyclerView.ViewHolder {
        private TextView mUserId, mId, mTitle, mText;

        public PostAdapter(@NonNull View itemView) {
            super(itemView);
            mUserId = itemView.findViewById(R.id.userId);
            mId = itemView.findViewById(R.id.id);
            mTitle = itemView.findViewById(R.id.title);
            mText = itemView.findViewById(R.id.text);
        }
    }
}
