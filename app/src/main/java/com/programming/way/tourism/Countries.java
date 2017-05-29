package com.programming.way.tourism;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by Hesham on 5/28/2017.
 */

public class Countries {

    public Countries() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length()>0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        for (String country : countries) {
           Log.i("Country" ,country);
        }
        Log.i( "# countries found: " , ""+countries.size());

        /*try {
            JSONArray jsonArray = new JSONArray(String.valueOf(R.raw.country_city));
            for (int i = 0 ; i<jsonArray.length() ; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }
}