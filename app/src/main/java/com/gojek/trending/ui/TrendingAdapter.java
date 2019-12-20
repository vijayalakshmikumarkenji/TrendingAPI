package com.gojek.trending.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.gojek.trending.R;
import com.gojek.trending.model.TrendingResponse.TrendingResponse;

import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder> {

    Context mContext;
    List<TrendingResponse> mTrendingResponseList = new ArrayList<>();
    int mExpandedPosition = -1;

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
    public void onBindViewHolder(@NonNull final TrendingViewHolder trendingViewHolder, final int i) {

        setHeaderView(trendingViewHolder, i);
        setFooterView(trendingViewHolder, i);


    }

    private void setHeaderView(final TrendingViewHolder trendingViewHolder, int i) {
        Glide.with(mContext).load(Uri.parse(mTrendingResponseList.get(i).getAvatar()))
                .asBitmap().fitCenter()
                .into(new BitmapImageViewTarget(trendingViewHolder.mUserImageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        trendingViewHolder.mUserImageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
        trendingViewHolder.mAuthorNameView.setText(mTrendingResponseList.get(i).getAuthor());
        trendingViewHolder.mNameView.setText(mTrendingResponseList.get(i).getName());
    }

    private void setFooterView(final TrendingViewHolder trendingViewHolder, final int i) {
        setVisibilityOfFooterView(trendingViewHolder, i);
        trendingViewHolder.mDescriptionView.setText(mTrendingResponseList.get(i).getDescription()
                + " (" + mTrendingResponseList.get(i).getUrl() + ")");
        setLanguageView(trendingViewHolder, i);
        setStarView(trendingViewHolder, i);
        setForkView(trendingViewHolder, i);
    }

    private void setVisibilityOfFooterView(final TrendingViewHolder trendingViewHolder, final int i) {
        final boolean isExpanded = i == mExpandedPosition;
        trendingViewHolder.mExpandLayoutView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        trendingViewHolder.mNameLinearLayoutView.setActivated(isExpanded);
        trendingViewHolder.mNameLinearLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trendingViewHolder.mExpandLayoutView.setVisibility(View.VISIBLE);
                mExpandedPosition = isExpanded ? -1 : i;
                notifyDataSetChanged();
            }
        });
    }


    private void setLanguageView(final TrendingViewHolder trendingViewHolder, int i) {
        if (mTrendingResponseList.get(i).getLanguage() != null) {
            trendingViewHolder.mLanguageTextView
                    .setText(mTrendingResponseList.get(i).getLanguage());
        } else {
            trendingViewHolder.mLanguageTextView.setText("No Language Mentioned");
        }
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.OVAL);
        gd.setColor(Color.RED);
        gd.setCornerRadius(5);
        trendingViewHolder.mLanguageImageView.setImageDrawable(gd);
    }


    private void setStarView(final TrendingViewHolder trendingViewHolder, final int i) {
        if (mTrendingResponseList.get(i).getStars() == 0) {
            trendingViewHolder.mStarTextView.setText("0");
        } else {
            trendingViewHolder.mStarTextView.setText(String
                    .valueOf(mTrendingResponseList.get(i).getStars()));
        }
    }

    private void setForkView(final TrendingViewHolder trendingViewHolder, final int i) {
        if (mTrendingResponseList.get(i).getForks() == 0) {
            trendingViewHolder.mForkTextView.setText("0");
        } else {
            trendingViewHolder.mForkTextView.setText(String
                    .valueOf(mTrendingResponseList.get(i).getForks()));
        }
    }

    @Override
    public int getItemCount() {
        return mTrendingResponseList.size();
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mNameLinearLayoutView;
        private ImageView mUserImageView;
        private TextView mAuthorNameView;
        private TextView mNameView;
        private LinearLayout mExpandLayoutView;
        private TextView mDescriptionView;
        private ImageView mLanguageImageView;
        private TextView mLanguageTextView;
        private TextView mStarTextView;
        private ImageView mStarImageView;
        private ImageView mForkImageView;
        private TextView mForkTextView;

        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameLinearLayoutView = (LinearLayout) itemView.findViewById(R.id.name_linear_layout_view);
            mUserImageView = (ImageView) itemView.findViewById(R.id.user_image_view);
            mAuthorNameView = (TextView) itemView.findViewById(R.id.author_name_view);
            mNameView = (TextView) itemView.findViewById(R.id.name_view);
            mExpandLayoutView = (LinearLayout) itemView.findViewById(R.id.expand_layout_view);
            mDescriptionView = (TextView) itemView.findViewById(R.id.url_view);
            mLanguageImageView = (ImageView) itemView.findViewById(R.id.lang_image_view);
            mLanguageTextView = (TextView) itemView.findViewById(R.id.lang_text_view);
            mStarTextView = (TextView) itemView.findViewById(R.id.star_text_view);
            mForkTextView = (TextView) itemView.findViewById(R.id.fork_text_view);
            mForkImageView = (ImageView) itemView.findViewById(R.id.fork_image_view);
            mStarImageView = (ImageView) itemView.findViewById(R.id.star_image_view);
        }
    }
}
