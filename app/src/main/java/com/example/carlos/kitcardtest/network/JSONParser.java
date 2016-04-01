package com.example.carlos.kitcardtest.network;

import com.example.carlos.kitcardtest.model.BikePoint;
import com.example.carlos.kitcardtest.model.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Carlos2 on 01/04/2016.
 */
public class JSONParser {


    public static ArrayList<BikePoint> parseResponse(String response) {
        JSONArray jsonArray;
        JSONObject jsonObject, jsonObject1;
        ArrayList<BikePoint> result = new ArrayList<>();

        try {

            JSONArray serverData = new JSONArray(response);
            if ((response.indexOf("error") <= 0) || (response.indexOf("ERROR") <= 0)) {

                for (int i = 0; i < serverData.length(); ++i) {
                    jsonObject = serverData.getJSONObject(i);

                    if (jsonObject != null) {

                        BikePoint bikePoint = new BikePoint();
                        if (jsonObject.has("id")) {
                            String id = jsonObject.getString("id");
                            bikePoint.setId(id);
                        }

                        if (jsonObject.has("commonName")) {
                            bikePoint.setName(jsonObject.getString("commonName"));
                        }

                        if (jsonObject.has("lat")) {
                            bikePoint.setLat(Float.parseFloat(jsonObject.getString("lat")));
                        }

                        if (jsonObject.has("lon")) {
                            bikePoint.setLat(Float.parseFloat(jsonObject.getString("lon")));
                        }

                        if (jsonObject.has("additionalProperties")) {
                            jsonArray = jsonObject.getJSONArray("additionalProperties");
                            for (int j = 0; j < jsonArray.length(); j++) {
                                jsonObject1 = jsonArray.getJSONObject(j);
                                String key = "";
                                String value = "";
                                if (jsonObject1.has("key")) {
                                    key = jsonObject1.getString("key");
                                }
                                if (jsonObject1.has("value")) {
                                    value = jsonObject1.getString("value");
                                }
                                switch (key) {
                                    case Constants.EMPTY_DOCKS: {
                                        bikePoint.setEmptyDocks(value);
                                    }
                                    case Constants.LOCKED: {
                                        bikePoint.setLocked(Boolean.parseBoolean(value));
                                    }
                                    case Constants.DOCKS: {
                                        bikePoint.setDocks(value);
                                    }
                                }
                            }
                        }
                        result.add(bikePoint);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}