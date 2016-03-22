package com.example.havh.newyorkapi.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.havh.newyorkapi.R;
import com.example.havh.newyorkapi.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Asus on 3/17/2016.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {
    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, android.R.layout.simple_list_item_1, articles);
    }

    private static class ViewHolder {
        ImageView imgThumbnail;
        TextView txtHeadline;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Article article = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_article_result, parent, false);
            viewHolder.imgThumbnail = (ImageView) convertView.findViewById(R.id.iv_thumbNail);
            viewHolder.txtHeadline = (TextView) convertView.findViewById(R.id.tv_headline);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String headline = article.getHeadLine();
        if (!TextUtils.isEmpty(headline)) {
            viewHolder.txtHeadline.setText(article.getHeadLine());
        }


        String thumbnail = article.getThumbnail();
        if (!TextUtils.isEmpty(thumbnail)) {
            if (!TextUtils.isEmpty(thumbnail)) {
                Picasso.with(getContext())
                        .load(thumbnail)
                        .placeholder(R.drawable.loading_icon)
                        .into(viewHolder.imgThumbnail);
            }
        }
        return convertView;
    }
}
