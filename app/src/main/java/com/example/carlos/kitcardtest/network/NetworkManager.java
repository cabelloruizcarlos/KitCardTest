package com.example.carlos.kitcardtest.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.carlos.kitcardtest.model.BikePoint;
import com.example.carlos.kitcardtest.model.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Carlos2 on 01/04/2016.
 */
public class NetworkManager {

    private Context mContext;
    private int mResponseCode;
    private String mServerError, mServerResponse;

    public NetworkManager(Context context) {
        mContext = context;
        mServerError = "";
        mServerResponse = "";
    }

    public boolean getNetworkStatus(Context context) {

        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);  // 1

        final android.net.NetworkInfo mobile = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);  // 0

        boolean status = false;

        if (wifi != null && wifi.getState() == NetworkInfo.State.CONNECTED) {
            status = true;
        } else {
            if (mobile != null && mobile.getState() == NetworkInfo.State.CONNECTED)
                status = true;
        }
        return status;
    }

    public ArrayList<BikePoint> makeRequest() {

        mServerResponse = postJSONToServer(Constants.API_URL);
        if ((mServerResponse != null) && (!mServerResponse.equals("")))
            return JSONParser.parseResponse(mServerResponse);

        return null;
    }

    private String postJSONToServer(String apiUrl) {

        URL obj;

        if (getNetworkStatus(mContext)) {
            try {
                obj = new URL(apiUrl);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //set the request method to post,
                con.setRequestMethod("GET");

                // open
                con.connect();
                try {
                    mResponseCode = con.getResponseCode();
                } catch (SocketTimeoutException e) {
                    mResponseCode = 0;
                } catch (IOException e) {
                    mResponseCode = con.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // action on response
                mServerError = "";
                mServerResponse = "";

                if (mResponseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    con.disconnect();
                    mServerResponse = response.toString();
                    if (mServerResponse.equals(null) || (mServerResponse.equals(""))) {
                        mServerError = Constants.SERVER_ERROR;
                    }
                } else {
                    mServerError = Constants.SERVER_ERROR;
                }
            } catch (IOException e) {
                mServerError = Constants.SERVER_ERROR;
            }
        } else {
            mServerError = Constants.SERVER_ERROR;
        }
        return mServerResponse;
    }
}

