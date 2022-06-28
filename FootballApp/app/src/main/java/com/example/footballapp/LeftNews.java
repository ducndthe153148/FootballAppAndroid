package com.example.footballapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.footballapp.adapter.Adapter;
import com.example.footballapp.api.ApiClient;
import com.example.footballapp.api.ApiInterface;
import com.example.footballapp.models.ArticlesItem;
import com.example.footballapp.models.Responses;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeftNews extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String API_KEY = "e5a7abe04cb44e41843fc49f810f6591";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<ArticlesItem> articles = new ArrayList<>();
    private Adapter adapter;
    private String TAG = LeftNews.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout errorLayout;

    private TextView topHeadline;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_news);

        //get id
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        topHeadline = findViewById(R.id.topheadlines);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(LeftNews.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        onLoadingSwipeRefresh("sports","football");

        errorLayout = findViewById(R.id.errorLayout);
        errorImage = findViewById(R.id.errorImage);
        errorTitle = findViewById(R.id.errorTitle);
        errorMessage = findViewById(R.id.errorMessage);
        btnRetry = findViewById(R.id.btn_retry);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // this event will enable the back
    // function to the button on press

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void LoadJson(final String category, String keyword) {
        errorLayout.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        Call<Responses> call;

        call = apiInterface.getNews(category, keyword, API_KEY);


        call.enqueue(new Callback<Responses>() {
            @Override
            public void onResponse(Call<Responses> call, Response<Responses> response) {
                if (response.isSuccessful() && response.body().getArticles() != null) {
                    if (!articles.isEmpty()) {
                        articles.clear();
                    }

                    articles = response.body().getArticles();
                    adapter = new Adapter(articles,LeftNews.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    initListener();

                    topHeadline.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);

                } else {
                    topHeadline.setVisibility(View.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);

                    String errorCode;
                    switch (response.code()) {
                        case 404:
                            errorCode = "404 Not found";
                            break;

                        case 500:
                            errorCode = "500 server broken";
                            break;

                        default:
                            errorCode = "unknown error";
                            break;
                    }

                    showErrorMessage(
                            R.drawable.no_result,
                            "No Result",
                            "Please try again!\n"+
                                    errorCode
                    );
                }
            }

            @Override
            public void onFailure(Call<Responses> call, Throwable t) {

                topHeadline.setVisibility(View.INVISIBLE);
                swipeRefreshLayout.setRefreshing(false);
                showErrorMessage(
                        R.drawable.oops,
                        "Oops....",
                        "Network failure, Please try again\n"+
                                t.toString()
                );

            }
        });

    }

    private void initListener() {

//        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ImageView imageView = view.findViewById(R.id.img);
//                Intent intent = new Intent(MainActivity.this, FootballNewsDetail.class);
//
//                ArticlesItem article = articles.get(position);
//                intent.putExtra("url", article.getUrl());
//                intent.putExtra("title", article.getTitle());
//                intent.putExtra("img", article.getUrlToImage());
//                intent.putExtra("date", article.getPublishedAt());
//                intent.putExtra("source", article.getSource().getName());
//                intent.putExtra("author", article.getAuthor());
//
//                startActivity(intent);
//
//            }
//        });
    }

    @Override
    public void onRefresh() {

        LoadJson("sports","football");

    }

    private void onLoadingSwipeRefresh(final String category, final String keyword) {

        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        LoadJson(category,keyword);
                    }
                }
        );
    }

    private void showErrorMessage(int imageView, String title, String message) {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
        }

        errorImage.setImageResource(imageView);
        errorTitle.setText(title);
        errorMessage.setText(message);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadingSwipeRefresh("sports","football");
            }
        });
    }
}