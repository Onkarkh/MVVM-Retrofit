package com.example.mvvm_tutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_tutorial.R;
import com.example.mvvm_tutorial.model.Post;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PostAdapter> {

    private static final String TAG = "RecyclerAdapter";
    private List<Post> mPost;
    private Context context;
    private LayoutInflater inflater;

    public RecyclerAdapter(List<Post> mPost, Context context) {
        this.mPost = mPost;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.PostAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_post_recycleview,parent,false);
        return new PostAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter holder, int position) {
        holder.mUserId.setText(Integer.toString(mPost.get(position).getUserId()));
        holder.mId.setText(Integer.toString(mPost.get(position).getId()));
        holder.mTitle.setText(mPost.get(position).getTitle());
        holder.mText.setText(mPost.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return mPost.size();
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
