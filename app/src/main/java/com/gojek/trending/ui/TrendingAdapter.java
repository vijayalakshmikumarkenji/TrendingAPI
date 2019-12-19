package com.gojek.trending.ui;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gojek.trending.R;
import com.gojek.trending.model.TrendingResponse.TrendingResponse;

import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder> {

    Context mContext;
    List<TrendingResponse> mTrendingResponseList = new ArrayList<>();

    TrendingAdapter(Context context, List<TrendingResponse> trendingResponseList) {
        mContext = context;
        mTrendingResponseList = trendingResponseList;
    }

    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TrendingViewHolder trendingViewHolder;
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.trending_row_item_layout, viewGroup, false);
        trendingViewHolder = new TrendingViewHolder(view);
        return trendingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingViewHolder trendingViewHolder, int i) {

        Glide.with(mContext)
                .load(Uri.parse(mTrendingResponseList.get(i).getAvatar()))
                .into(trendingViewHolder.mUserImageView);
        trendingViewHolder.mAuthorNameView.setText(mTrendingResponseList.get(i).getAuthor());
        trendingViewHolder.mNameView.setText(mTrendingResponseList.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return mTrendingResponseList.size();
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder {
        private ImageView mUserImageView;
        private TextView mAuthorNameView;
        private TextView mNameView;

        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserImageView = (ImageView) itemView.findViewById(R.id.user_image_view);
            mAuthorNameView = (TextView) itemView.findViewById(R.id.author_name_view);
            mNameView = (TextView) itemView.findViewById(R.id.name_view);

        }
    }
}
