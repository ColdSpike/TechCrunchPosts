package com.example.makrandpawar.intentfilterdemo.rest;

import com.example.makrandpawar.intentfilterdemo.model.DetailedPost;
import com.example.makrandpawar.intentfilterdemo.model.FeedPosts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by MAKRAND PAWAR on 8/10/2017.
 */

public interface TechcrunchService {
    @GET("/rest/v1.1/sites/techcrunch.com/posts?fields=ID,title,featured_image")
    Call<FeedPosts> getPostList();

    @GET("/rest/v1.1/sites/techcrunch.com/posts/{postId}")
    Call<DetailedPost> getDetailedPostId(@Path("postId") String id);

    @GET("/rest/v1.1/sites/techcrunch.com/posts/slug:{post_slug}")
    Call<DetailedPost> getDetailedPostSlug(@Path("post_slug") String slug);
}
