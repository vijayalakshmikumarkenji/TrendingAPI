/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gojek.trending.network;

import com.gojek.trending.model.TrendingResponse.TrendingResponse;

import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import rx.Observable;


import rx.functions.Func1;

/**
 * Created by Vijiayalakshmi.K.K
 * <p>
 * Client class to handle api call.
 */
public class TrendingClient {

    /**
     * Method to make a api call to get trending list from git hub
     *
     * @return trendingListResponse returns the list of trending responses object
     */
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
