package com.example.havh.newyorkapi.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Asus on 3/16/2016.
 */
public class Article implements Parcelable {
    String webUrl;
    String headLine;
    String thumbnail;
    long beginDate;
    String sort;
    String newsDesk;

    public long getBeginDate() {
        return beginDate;
    }

    public String getSort() {
        return sort;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadLine() {
        return headLine;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Article(JSONObject jsonObject) {
        try {
            this.webUrl = jsonObject.getString("web_url");
            this.headLine = jsonObject.getJSONObject("headline").getString("main");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");

            if (multimedia != null && multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                this.thumbnail = "http://www.nytimes.com/" + multimediaJson.getString("url");
            } else {
                this.thumbnail = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Article> fromJSONArray(JSONArray array) {
        ArrayList<Article> result = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                result.add(new Article(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
         return result;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.webUrl);
        dest.writeString(this.headLine);
        dest.writeString(this.thumbnail);
        dest.writeLong(this.beginDate);
        dest.writeString(this.sort);
        dest.writeString(this.newsDesk);
    }

    protected Article(Parcel in) {
        this.webUrl = in.readString();
        this.headLine = in.readString();
        this.thumbnail = in.readString();
        this.beginDate = in.readLong();
        this.sort = in.readString();
        this.newsDesk = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
