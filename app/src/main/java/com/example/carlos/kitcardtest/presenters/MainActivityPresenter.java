package com.example.carlos.kitcardtest.presenters;

import android.content.Context;
import android.os.AsyncTask;

import com.example.carlos.kitcardtest.adapter.CustomAdapter;
import com.example.carlos.kitcardtest.interfaces.IMainActivityView;
import com.example.carlos.kitcardtest.model.BikePoint;
import com.example.carlos.kitcardtest.network.NetworkManager;

import java.util.ArrayList;

/**
 * Created by Carlos2 on 01/04/2016.
 */
public class MainActivityPresenter {

    private Context mContext;

    private IMainActivityView mView;
    private NetworkManager mNetworkManager;
    private CustomAdapter mAdapter;

    private ArrayList<BikePoint> listItems = new ArrayList<>();

    public MainActivityPresenter(IMainActivityView view) {
        mView = view;
        mContext = mView.getContext();
    }

    public void initView() {

        mNetworkManager = new NetworkManager(mView.getContext());

        RetrieveBikePoints backTask = new RetrieveBikePoints();
        backTask.execute();

    }


    private void updateListItems(ArrayList<BikePoint> serverRes) {
        listItems = new ArrayList<>(serverRes);

        mAdapter = new CustomAdapter(this.mContext,listItems);
        mView.setAdapter(mAdapter);
    }

    protected class RetrieveBikePoints extends AsyncTask<Void, Void, ArrayList<BikePoint>> {

        @Override
        protected ArrayList<BikePoint> doInBackground(Void... params) {
            return mNetworkManager.makeRequest();
        }

        @Override
        protected void onPostExecute(ArrayList<BikePoint> serverRes) {
            updateListItems(serverRes);
        }
    }
}