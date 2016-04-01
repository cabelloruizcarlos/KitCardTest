package com.example.carlos.kitcardtest.interfaces;

import android.content.Context;

import com.example.carlos.kitcardtest.adapter.CustomAdapter;

/**
 * Created by Carlos2 on 01/04/2016.
 */
public interface IMainActivityView {

    Context getContext();
    void setAdapter(CustomAdapter adapter);
}
