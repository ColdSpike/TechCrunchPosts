package com.example.makrandpawar.intentfilterdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MAKRAND PAWAR on 8/10/2017.
 */

public class FeedPosts {

    public List<Post> posts;

    public static class Post {
        @SerializedName("ID")
        public Integer id;
        public String title;
        @SerializedName("featured_image")
        public String featuredImage;
    }
}
