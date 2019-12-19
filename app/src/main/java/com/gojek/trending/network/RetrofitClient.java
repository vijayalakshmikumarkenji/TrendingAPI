package com.gojek.trending.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient mRetroftClientInstance = null;

    private TrendingService mTrendingService;

    public static final String BASE_URL = "https://github-trending-api.now.sh";

    /**
     * Returns the Retrofitclient instance.
     *
     * @return RetrofitClient mRetroftClientInstance
     */
    public static synchronized RetrofitClient getInstance() {
        if (mRetroftClientInstance == null) {
            Log.d("in inita viji", "getInstance");
            mRetroftClientInstance = new RetrofitClient();
        }
        return mRetroftClientInstance;
    }

    private RetrofitClient() {
        getRetrofitInstance();
    }

    private void getRetrofitInstance() {
        Log.d("in inita viji", "getRetrofitInstance");
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        this.mTrendingService = retrofit.create(TrendingService.class);
    }

    /**
     * Returns the trending service instance to do rest call.
     *
     * @return TrendingService mTrendingService
     */
    public TrendingService getTrendingService() {
        Log.d("in inita viji", "getTrendingService");
        return mTrendingService;
    }

}
