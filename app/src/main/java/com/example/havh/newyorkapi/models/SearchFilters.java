package com.example.havh.newyorkapi.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Asus on 3/22/2016.
 */
public class SearchFilters implements Parcelable {
    private long beginDate;
    private String sort;
    private String newsDesk;


    public long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getNewsDesk() {
        return newsDesk;
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
        dest.writeLong(this.beginDate);
        dest.writeString(this.sort);
        dest.writeString(this.newsDesk);
    }

    public SearchFilters() {
    }

    protected SearchFilters(Parcel in) {
        this.beginDate = in.readLong();
        this.sort = in.readString();
        this.newsDesk = in.readString();
    }

    public static final Creator<SearchFilters> CREATOR = new Creator<SearchFilters>() {
        @Override
        public SearchFilters createFromParcel(Parcel source) {
            return new SearchFilters(source);
        }

        @Override
        public SearchFilters[] newArray(int size) {
            return new SearchFilters[size];
        }
    };
}
