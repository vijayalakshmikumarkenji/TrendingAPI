package com.gojek.trending.network;


import com.gojek.trending.model.TrendingResponse.TrendingResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrendingService {
    @GET("/repositories")
    io.reactivex.Observable<List<TrendingResponse>> getTrendingResponses(
            @Query("language") String language,
            @Query("since") String since
    );
}
