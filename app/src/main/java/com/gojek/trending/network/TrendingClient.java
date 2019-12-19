package com.gojek.trending.network;

import com.gojek.trending.model.TrendingResponse.TrendingResponse;

import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import rx.Observable;


import rx.functions.Func1;


public class TrendingClient {

    public static io.reactivex.Observable<List<TrendingResponse>> getTrendingResponses() {

        final io.reactivex.Observable<List<TrendingResponse>> trendingResponseObservable
                = RetrofitClient.getInstance().getTrendingService()
                .getTrendingResponses("", "daily")
                .map(new Function<List<TrendingResponse>, List<TrendingResponse>>() {
                    @Override
                    public List<TrendingResponse> apply(List<TrendingResponse> trendingResponseList) throws Exception {
                        return trendingResponseList;
                    }
                });

        return trendingResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}
