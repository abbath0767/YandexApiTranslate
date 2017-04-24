package com.ng.yandextranslate.ui.fragment.favorite;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ng.yandextranslate.App;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.controller.adapter.FavoriteRecyclerViewAdapter;
import com.ng.yandextranslate.controller.adapter.HistoryRecyclerViewAdapter;
import com.ng.yandextranslate.presentation.contract.favorite.FavoriteContract;
import com.ng.yandextranslate.presentation.implementation.favorite.DaggerFavoriteComponent;
import com.ng.yandextranslate.presentation.implementation.favorite.FavoriteModule;
import com.ng.yandextranslate.presentation.implementation.favorite.FavoritePresenterIml;
import com.ng.yandextranslate.ui.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment extends BaseFragment implements FavoriteContract.View {

    @BindView(R.id.favorite_recycler_view)
    RecyclerView mRecycler;
    @BindView(R.id.empty_view)
    TextView mEmptyTextView;

    @Inject
    FavoritePresenterIml mPresenter;

    @Inject
    FavoriteRecyclerViewAdapter mAdapter;

    public static Fragment newInstance(Bundle args) {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        ButterKnife.bind(this, rootView);

        DaggerFavoriteComponent.builder()
                .appComponent(App.getAppComponent())
                .favoriteModule(new FavoriteModule(this))
                .build().inject(this);

        initRecyclerView();

        return rootView;
    }

    private void initRecyclerView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void notifyData() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear_history: {
                mPresenter.unMakeAllFavorites();
                return true;
            }
        }

        return false;
    }

    @Override
    public void showEmptyView() {
        mRecycler.setVisibility(View.GONE);
        mEmptyTextView.setVisibility(View.VISIBLE);
    }
}
