package com.example.makrandpawar.intentfilterdemo;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.makrandpawar.intentfilterdemo.adapter.MainActivityAdapter;
import com.example.makrandpawar.intentfilterdemo.model.FeedPosts;
import com.example.makrandpawar.intentfilterdemo.rest.RetrofitService;
import com.example.makrandpawar.intentfilterdemo.rest.TechcrunchService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.mainactivity_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mainactivity_swipe);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Posts");
        progressDialog.setMessage("Just a moment");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progressDialog.show();
                populateList();
            }
        });

        populateList();
    }


    private void populateList() {
        RetrofitService retrofitService = new RetrofitService();
        TechcrunchService techcrunchService = retrofitService.getRetrofitInstance().create(TechcrunchService.class);

        techcrunchService.getPostList().enqueue(new Callback<FeedPosts>() {
            @Override
            public void onResponse(Call<FeedPosts> call, Response<FeedPosts> response) {
                MainActivityAdapter adapter = new MainActivityAdapter(response.body().posts);
                recyclerView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<FeedPosts> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });

    }
}
