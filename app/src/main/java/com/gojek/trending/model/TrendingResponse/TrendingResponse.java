
package com.gojek.trending.model.TrendingResponse;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrendingResponse implements Parcelable
{

    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("stars")
    @Expose
    private long stars;
    @SerializedName("forks")
    @Expose
    private long forks;
    @SerializedName("currentPeriodStars")
    @Expose
    private long currentPeriodStars;
    @SerializedName("builtBy")
    @Expose
    private List<BuiltBy> builtBy = null;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("languageColor")
    @Expose
    private String languageColor;
    public final static Parcelable.Creator<TrendingResponse> CREATOR = new Creator<TrendingResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TrendingResponse createFromParcel(Parcel in) {
            return new TrendingResponse(in);
        }

        public TrendingResponse[] newArray(int size) {
            return (new TrendingResponse[size]);
        }

    }
    ;

    protected TrendingResponse(Parcel in) {
        this.author = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.stars = ((long) in.readValue((long.class.getClassLoader())));
        this.forks = ((long) in.readValue((long.class.getClassLoader())));
        this.currentPeriodStars = ((long) in.readValue((long.class.getClassLoader())));
        in.readList(this.builtBy, (com.gojek.trending.model.TrendingResponse.BuiltBy.class.getClassLoader()));
        this.language = ((String) in.readValue((String.class.getClassLoader())));
        this.languageColor = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public TrendingResponse() {
    }

    /**
     * 
     * @param forks
     * @param builtBy
     * @param author
     * @param name
     * @param description
     * @param language
     * @param avatar
     * @param stars
     * @param languageColor
     * @param url
     * @param currentPeriodStars
     */
    public TrendingResponse(String author, String name, String avatar, String url, String description, long stars, long forks, long currentPeriodStars, List<BuiltBy> builtBy, String language, String languageColor) {
        super();
        this.author = author;
        this.name = name;
        this.avatar = avatar;
        this.url = url;
        this.description = description;
        this.stars = stars;
        this.forks = forks;
        this.currentPeriodStars = currentPeriodStars;
        this.builtBy = builtBy;
        this.language = language;
        this.languageColor = languageColor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStars() {
        return stars;
    }

    public void setStars(long stars) {
        this.stars = stars;
    }

    public long getForks() {
        return forks;
    }

    public void setForks(long forks) {
        this.forks = forks;
    }

    public long getCurrentPeriodStars() {
        return currentPeriodStars;
    }

    public void setCurrentPeriodStars(long currentPeriodStars) {
        this.currentPeriodStars = currentPeriodStars;
    }

    public List<BuiltBy> getBuiltBy() {
        return builtBy;
    }

    public void setBuiltBy(List<BuiltBy> builtBy) {
        this.builtBy = builtBy;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageColor() {
        return languageColor;
    }

    public void setLanguageColor(String languageColor) {
        this.languageColor = languageColor;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(author);
        dest.writeValue(name);
        dest.writeValue(avatar);
        dest.writeValue(url);
        dest.writeValue(description);
        dest.writeValue(stars);
        dest.writeValue(forks);
        dest.writeValue(currentPeriodStars);
        dest.writeList(builtBy);
        dest.writeValue(language);
        dest.writeValue(languageColor);
    }

    public int describeContents() {
        return  0;
    }

}
