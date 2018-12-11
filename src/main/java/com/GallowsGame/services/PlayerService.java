package com.GallowsGame.services;

import com.GallowsGame.models.UserData;
import org.json.simple.JSONObject;

import java.util.Optional;

public class PlayerService {//TODO PlayerService

    private final String API_BASE_URL = "http://localhost:8080/GallowsGameAPI-1.0-SNAPSHOT/words/player/";
    private ApiConnector apiConnector;

    public boolean login(UserData userData) {
        if(apiConnector==null){
            apiConnector = new ApiConnector();
        }
        JSONObject jsonObject = new JSONObject();//TODO generate json
        String post = apiConnector.getDataFromUrl(API_BASE_URL + "login", "POST", Optional.of(jsonObject));
        if(post.equals("true")){
            return true;
        }else {
            return false;
        }
    }

    public boolean register(UserData userData) {
        return false;
    }

    public void increaseScore(UserData userData) {

    }

    private String generateToken(UserData userData) {
        return "";
    }

}
