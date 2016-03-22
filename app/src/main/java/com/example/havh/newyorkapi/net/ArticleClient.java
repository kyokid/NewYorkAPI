package com.example.havh.newyorkapi.net;

import android.text.TextUtils;
import android.util.Log;

import com.example.havh.newyorkapi.models.Article;
import com.example.havh.newyorkapi.models.SearchFilters;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Asus on 3/20/2016.
 */
public class ArticleClient {
    public static final String API_BASE_URL = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
    private AsyncHttpClient client;

    public ArticleClient() {
        this.client = new AsyncHttpClient();
    }

    public void getArticles(SearchFilters filters, final String query, JsonHttpResponseHandler handler) {
        RequestParams param = new RequestParams();
        param.put("api-key", "11dbf372497085fd7a10a5ab0e62d00b:12:74725998");
        param.put("page", 0);
        param.put("q", query);
        if (filters != null) {
            if (filters.getNewsDesk() != null) {
                param.put("fq", filters.getNewsDesk());
            }
            if (filters.getBeginDate() > 0) {
                param.put("begin_date", filters.getBeginDate());
            }
            if (filters.getSort() != null) {
                param.put("sort", filters.getSort());
            }
        }
        client.get(API_BASE_URL, param, handler);
    }
}
