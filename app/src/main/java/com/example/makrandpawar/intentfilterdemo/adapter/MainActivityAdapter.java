package com.example.makrandpawar.intentfilterdemo.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.makrandpawar.intentfilterdemo.DetailedPost;
import com.example.makrandpawar.intentfilterdemo.MainActivity;
import com.example.makrandpawar.intentfilterdemo.R;
import com.example.makrandpawar.intentfilterdemo.model.FeedPosts;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    private List<FeedPosts.Post> posts;

    public MainActivityAdapter(List<FeedPosts.Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.mainactivity_singlepost,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        FeedPosts.Post post = posts.get(position);
        holder.title.setText(Html.fromHtml(post.title));
        int w = Resources.getSystem().getDisplayMetrics().widthPixels;
        int h = (Resources.getSystem().getDisplayMetrics().heightPixels)/3;
        Picasso.with(holder.imageView.getContext()).load(posts.get(position).featuredImage+"?quality=10").placeholder(R.mipmap.ic_placeholder).resize(w,h).centerCrop().into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(holder.itemView.getContext(),DetailedPost.class);
                detailIntent.putExtra("POSTID",posts.get(position).id.toString());
                holder.itemView.getContext().startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size() > 0 ? posts.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.mainactivity_singlepost_image);
            title = (TextView) itemView.findViewById(R.id.mainactivity_singlepost_title);
        }
    }
}
