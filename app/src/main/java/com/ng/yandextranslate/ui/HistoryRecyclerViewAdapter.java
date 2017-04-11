package com.ng.yandextranslate.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ng.yandextranslate.R;
import com.ng.yandextranslate.model.pojo.HistoryData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NG on 11.04.17.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HistoryData> list;

    public HistoryRecyclerViewAdapter(List<HistoryData> list) {
        this.list = list;
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

        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
