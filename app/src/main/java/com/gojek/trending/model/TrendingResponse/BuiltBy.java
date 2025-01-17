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

package com.gojek.trending.model.TrendingResponse;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Vijiayalakshmi.K.K
 * <p>
 * Model class built by parameter from API responses
 */
public class BuiltBy implements Parcelable {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    public final static Parcelable.Creator<BuiltBy> CREATOR = new Creator<BuiltBy>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BuiltBy createFromParcel(Parcel in) {
            return new BuiltBy(in);
        }

        public BuiltBy[] newArray(int size) {
            return (new BuiltBy[size]);
        }

    };

    protected BuiltBy(Parcel in) {
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.href = ((String) in.readValue((String.class.getClassLoader())));
        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public BuiltBy() {
    }

    /**
     * @param href
     * @param avatar
     * @param username
     */
    public BuiltBy(String username, String href, String avatar) {
        super();
        this.username = username;
        this.href = href;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(username);
        dest.writeValue(href);
        dest.writeValue(avatar);
    }

    public int describeContents() {
        return 0;
    }

}
