package com.ng.yandextranslate.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ng.yandextranslate.R;
import com.ng.yandextranslate.model.pojo.HistoryData;
import com.ng.yandextranslate.presentation.implementation.favorite.FavoritePresenterIml;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NG on 21.04.17.
 */

public class FavoriteRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    FavoritePresenterIml mPresenterIml;

    private List<HistoryData> mDataList;

    @Inject
    public FavoriteRecyclerViewAdapter(FavoritePresenterIml presenterIml) {
        mPresenterIml = presenterIml;
        mDataList = mPresenterIml.getFavorite();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View holder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_recycler, parent, false);

        return new FavoriteViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        FavoriteViewHolder viewHolder = (FavoriteViewHolder) holder;
        viewHolder.mOriginalTextView.setText(mDataList.get(position).getOriginalText());
        viewHolder.mTranslateTextView.setText(mDataList.get(position).getTranslateText());
        viewHolder.mLangsTextView.setText(mDataList.get(position).getLanguagePair().getLangPairStringValue());
        viewHolder.mFavoriteCheckBox.setChecked(mDataList.get(position).isFavorite());

        viewHolder.mFavoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                mPresenterIml.unMackeFavorite(mDataList.get(position).getKey(), isChecked);
                mDataList.remove(position);
                mPresenterIml.notifyData();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_favorite_text_view_original)
        TextView mOriginalTextView;
        @BindView(R.id.item_favorite_text_view_translate)
        TextView mTranslateTextView;
        @BindView(R.id.item_favorite_text_view_langs)
        TextView mLangsTextView;
        @BindView(R.id.item_favorite_checkbox_favorite)
        CheckBox mFavoriteCheckBox;

        public FavoriteViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
