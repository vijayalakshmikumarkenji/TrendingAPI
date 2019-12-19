package com.gojek.trending.ui;


import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.gojek.trending.R;
import com.gojek.trending.model.TrendingResponse.TrendingResponse;
import com.gojek.trending.network.TrendingClient;


import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.List;

import io.reactivex.disposables.Disposable;
import rx.Observable;
import rx.Observer;


public class TrendingActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    Toolbar mToolBar;
    RecyclerView mTrendingRecyclerView;
    ShimmerFrameLayout mShimmerFrameLayout;
    RelativeLayout mErrorLayoutView;
    TextView mRetyrView;
    SwipeRefreshLayout mSwipeToRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trending_layout);
        initViews();
        getTrendingList();
    }

    private void initViews() {
        setToolBar();
        setShimmerLayoutView();
        setErrorLayoutView();
        mTrendingRecyclerView = (RecyclerView) findViewById(R.id.trending_recycler_view);
        setPullToRefreshView();
    }

    private void setToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
        }
    }

    private void setShimmerLayoutView() {
        mShimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
    }

    private void setRecyclerView(List<TrendingResponse> trendingResponseList) {
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL
                , false);
        mTrendingRecyclerView.setLayoutManager(linearLayoutManager);
        TrendingAdapter trendingAdapter = new TrendingAdapter(getApplicationContext(), trendingResponseList);
        mTrendingRecyclerView.setAdapter(trendingAdapter);
    }

    private void setErrorLayoutView() {
        mErrorLayoutView = (RelativeLayout) findViewById(R.id.error_layout_view);
        mRetyrView = (TextView) findViewById(R.id.retry_view);
        mRetyrView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTrendingList();
            }
        });
    }


    @SuppressLint("ResourceType")
    private void setPullToRefreshView() {
        mSwipeToRefreshView = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_view);
        mSwipeToRefreshView.setOnRefreshListener(this);
        mSwipeToRefreshView.setBackground(getResources().getDrawable(R.drawable.refresh));

    }

    private void getTrendingList() {
        viewVisibilityBasedOnApiError(false);
        setShimmerAnimation(true);
        io.reactivex.Observer<List<TrendingResponse>> trendingObserver = new io.reactivex.Observer<List<TrendingResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<TrendingResponse> trendingResponseList) {
                setShimmerAnimation(false);
                viewVisibilityBasedOnApiError(false);
                setRecyclerView(trendingResponseList);
            }

            @Override
            public void onError(Throwable e) {
                setShimmerAnimation(false);
                viewVisibilityBasedOnApiError(true);
            }

            @Override
            public void onComplete() {
                setShimmerAnimation(false);
            }
        };

        TrendingClient.getTrendingResponses().subscribe(trendingObserver);
    }


    private void viewVisibilityBasedOnApiError(boolean visible) {
        if (visible) {
            // If trending api gets failed.
            mErrorLayoutView.setVisibility(View.VISIBLE);
            mTrendingRecyclerView.setVisibility(View.GONE);
        } else {
            mErrorLayoutView.setVisibility(View.GONE);
            mTrendingRecyclerView.setVisibility(View.VISIBLE);
        }

    }


    private void setShimmerAnimation(boolean doAnimate) {

        if (doAnimate) {
            mShimmerFrameLayout.setVisibility(View.VISIBLE);
            mShimmerFrameLayout.startShimmerAnimation();

        } else {
            mShimmerFrameLayout.stopShimmerAnimation();
            mShimmerFrameLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onRefresh() {
        mSwipeToRefreshView.setRefreshing(true);
        viewVisibilityBasedOnApiError(false);
        io.reactivex.Observer<List<TrendingResponse>> trendingObserver = new io.reactivex.Observer<List<TrendingResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<TrendingResponse> trendingResponseList) {
                mSwipeToRefreshView.setRefreshing(false);
                viewVisibilityBasedOnApiError(false);
                setRecyclerView(trendingResponseList);
            }

            @Override
            public void onError(Throwable e) {
                mSwipeToRefreshView.setRefreshing(false);
                viewVisibilityBasedOnApiError(true);
            }

            @Override
            public void onComplete() {

            }
        };

        TrendingClient.getTrendingResponses().subscribe(trendingObserver);
    }
}
