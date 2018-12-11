package com.GallowsGame.services;

import com.GallowsGame.exceptions.APIConnectionError;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class ApiConnector {

    public String getDataFromUrl(String urlAddress, String method, Optional<JSONObject> requestData){
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            if(method!="GET" && requestData.isPresent()){
                connection.setRequestProperty("Content-Type", "application-json");
                OutputStream output = connection.getOutputStream();
                output.write(requestData.toString().getBytes(StandardCharsets.UTF_8));
                output.close();
            }
            int connCode = connection.getResponseCode();
            if (connCode != 200) {
                throw new APIConnectionError("Problem with connection, code: " + connCode);
            }
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = inputReader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

}
