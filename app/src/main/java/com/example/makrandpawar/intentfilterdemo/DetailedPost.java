package com.example.makrandpawar.intentfilterdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.makrandpawar.intentfilterdemo.rest.RetrofitService;
import com.example.makrandpawar.intentfilterdemo.rest.TechcrunchService;
import com.squareup.picasso.Picasso;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailedPost extends AppCompatActivity {

    TextView title, author;
    WebView content;
    ImageView image;
    int systemWidth, systemHeight;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = (TextView) findViewById(R.id.deailedpostactivity_title);
        content = (WebView) findViewById(R.id.deailedpostactivity_content);
        author = (TextView) findViewById(R.id.deailedpostactivity_author);
        image = (ImageView) findViewById(R.id.deailedpostactivity_image);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Posts");
        progressDialog.setMessage("Just a moment");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        systemHeight = (Resources.getSystem().getDisplayMetrics().heightPixels) / 3;
        systemWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        Intent intent = getIntent();
        if (intent.getStringExtra("SLUG") != null) {
            displayPostSlug(intent.getStringExtra("SLUG"));
        } else {
            displayPostId(intent.getStringExtra("POSTID"));
        }
    }


    private void displayPostSlug(String slug) {
        RetrofitService retrofitService = new RetrofitService();
        Retrofit retrofit = retrofitService.getRetrofitInstance();
        TechcrunchService techcrunchService = retrofit.create(TechcrunchService.class);
        techcrunchService.getDetailedPostSlug(slug).enqueue(new Callback<com.example.makrandpawar.intentfilterdemo.model.DetailedPost>() {
            @Override
            public void onResponse(Call<com.example.makrandpawar.intentfilterdemo.model.DetailedPost> call, Response<com.example.makrandpawar.intentfilterdemo.model.DetailedPost> response) {
                if (response.body() != null) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        title.setText(Html.fromHtml(response.body().title, Html.FROM_HTML_MODE_LEGACY));
                        content.loadDataWithBaseURL("",response.body().content, "text/html", "UTF-8", "");
                        author.setText(Html.fromHtml("By: "+response.body().author.name, Html.FROM_HTML_MODE_LEGACY));

                    } else {
                        title.setText(Html.fromHtml(response.body().title));
                        content.loadDataWithBaseURL("",response.body().content, "text/html", "UTF-8", "");
                        author.setText("By: "+Html.fromHtml(response.body().author.name));
                    }
                    Picasso.with(DetailedPost.this).load(response.body().featuredImage).placeholder(R.mipmap.ic_placeholder).resize(systemWidth, systemHeight).centerCrop().into(image);
                    progressDialog.dismiss();
                } else {
                    title.setText("Some Error Occured. Please check the link and try again.");
                    content.setVisibility(View.GONE);
                    author.setVisibility(View.GONE);
                    image.setVisibility(View.GONE);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<com.example.makrandpawar.intentfilterdemo.model.DetailedPost> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }

    private void displayPostId(String postId) {
        RetrofitService retrofitService = new RetrofitService();
        Retrofit retrofit = retrofitService.getRetrofitInstance();
        TechcrunchService techcrunchService = retrofit.create(TechcrunchService.class);

        techcrunchService.getDetailedPostId(postId).enqueue(new Callback<com.example.makrandpawar.intentfilterdemo.model.DetailedPost>() {
            @Override
            public void onResponse(Call<com.example.makrandpawar.intentfilterdemo.model.DetailedPost> call, Response<com.example.makrandpawar.intentfilterdemo.model.DetailedPost> response) {
                if (response.body() != null) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        title.setText(Html.fromHtml(response.body().title, Html.FROM_HTML_MODE_LEGACY));
                        content.loadDataWithBaseURL("",response.body().content, "text/html", "UTF-8", "");
                        author.setText(Html.fromHtml("By: "+response.body().author.name, Html.FROM_HTML_MODE_LEGACY));

                    } else {
                        title.setText(Html.fromHtml(response.body().title));
                        content.loadDataWithBaseURL("",response.body().content, "text/html", "UTF-8", "");
                        author.setText(Html.fromHtml("By: "+response.body().author.name));
                    }
                    Picasso.with(DetailedPost.this).load(response.body().featuredImage).placeholder(R.mipmap.ic_placeholder).resize(systemWidth, systemHeight).centerCrop().into(image);
                    progressDialog.dismiss();
                } else {
                    title.setText("Some Error Occured. Please check the link and try again.");
                    content.setVisibility(View.GONE);
                    author.setVisibility(View.GONE);
                    image.setVisibility(View.GONE);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<com.example.makrandpawar.intentfilterdemo.model.DetailedPost> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }
}
