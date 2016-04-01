package com.example.carlos.kitcardtest.activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.carlos.kitcardtest.adapter.CustomAdapter;
import com.example.carlos.kitcardtest.R;
import com.example.carlos.kitcardtest.interfaces.IMainActivityView;
import com.example.carlos.kitcardtest.presenters.MainActivityPresenter;

/**
 * Created by Carlos2 on 01/04/2016.
 */
public class MainActivity extends ListActivity implements IMainActivityView,SwipeRefreshLayout.OnRefreshListener{

    private MainActivityPresenter mPresenter;
private SwipeRefreshLayout mPullToRefeshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mPullToRefeshView = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mPullToRefeshView.setOnRefreshListener(this);
        mPullToRefeshView.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mPresenter = new MainActivityPresenter(this);

        mPresenter.initView();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                mPullToRefeshView.setRefreshing(false);
            }
        }, 2500);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void setAdapter(CustomAdapter adapter) {
        setListAdapter(adapter);
    }
}
