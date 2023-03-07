package com.hamburgeressen.application.service;

import com.hamburgeressen.application.entity.LocationEntity;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


@Service
public class LocationService {

    public LocationEntity getLocationForAddress(String address) throws IOException, JSONException {

        Double lat = 0D;
        Double lon = 0D;

        String charset = "UTF-8";

        address =  URLEncoder.encode(address, charset);

        String url = "https://eu1.locationiq.com/v1/search";
        String query = String.format("?key=<key>&q=" + address + "&format=json");
        URL theUrl = new URL(url + query);
        System.out.println(theUrl);
        HttpURLConnection connection = (HttpURLConnection) theUrl.openConnection();

        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();
        String responseJson = sb.toString();
        System.out.println(responseJson);
        JSONArray responseObject = new JSONArray(responseJson);
        connection.disconnect();

        JSONObject response = responseObject.getJSONObject(0);

        lat = response.getDouble("lat");
        lon = response.getDouble("lon");

        return new LocationEntity(lon, lat);
    }

}
