package com.ng.yandextranslate.ui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ng.yandextranslate.App;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.controller.data.service.history.HistoryDataService;
import com.ng.yandextranslate.model.pojo.HistoryData;
import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.presentation.contract.history.HistoryContract;
import com.ng.yandextranslate.presentation.implementation.history.HistoryPresenterImpl;
import com.ng.yandextranslate.ui.fragment.history.HistoryFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NG on 11.04.17.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = HistoryRecyclerViewAdapter.class.getSimpleName();

    HistoryPresenterImpl mPresenter;

    private List<HistoryData> list;

    @Inject
    public HistoryRecyclerViewAdapter(HistoryPresenterImpl presenter) {
        mPresenter = presenter;
        this.list = mPresenter.getHistory();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_recycler, parent, false);

        return new HistoryViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HistoryViewHolder viewHolder = (HistoryViewHolder) holder;
        viewHolder.mOriginalTextView.setText(list.get(position).getOriginalText());
        viewHolder.mTranslateTextView.setText(list.get(position).getTranslateText());
        viewHolder.mLangsTextView.setText(list.get(position).getLanguagePair().getLangPairStringValue());
        viewHolder.mFavoriteCheckBox.setChecked(list.get(position).isFavorite());

        viewHolder.mFavoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                mPresenter.makeFavorite(list.get(position).getKey(), isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_text_view_original)
        TextView mOriginalTextView;
        @BindView(R.id.item_text_view_translate)
        TextView mTranslateTextView;
        @BindView(R.id.item_text_view_langs)
        TextView mLangsTextView;
        @BindView(R.id.item_checkbox_favorite)
        CheckBox mFavoriteCheckBox;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
