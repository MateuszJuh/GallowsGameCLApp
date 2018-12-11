package com.GallowsGame.services;

import com.GallowsGame.models.WordToGuess;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Optional;

public class WordService {
    private final String RANDOM_WORD_URL = "http://localhost:8080/GallowsGameAPI-1.0-SNAPSHOT/words/random";
    private ApiConnector apiConnector;

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

    public String getRandomWordJson() {
        if(apiConnector==null){
            apiConnector = new ApiConnector();
        }
        return apiConnector.getDataFromUrl(RANDOM_WORD_URL, "GET", Optional.empty());
    }
}
