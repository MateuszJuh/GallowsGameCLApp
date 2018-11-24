package com.GallowsGame.services;

import com.GallowsGame.exceptions.APIConnectionError;
import com.GallowsGame.models.WordToGuess;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WordService {
    private final String RANDOM_WORD_URL = "http://localhost:8080/GallowsGameAPI-1.0-SNAPSHOT/words/random";

    public WordToGuess getRandomWord() {
        String randomWordJson = getRandomWordJson();
        return new WordToGuess(getOnlyWordFromJSON(randomWordJson).toCharArray());
    }

    private String getOnlyWordFromJSON(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        String word = "";
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
            word = (String) jsonObject.get("word");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return word;
    }

    private String getRandomWordJson() {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(RANDOM_WORD_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
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
