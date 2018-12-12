package com.GallowsGame.services;

import com.GallowsGame.models.UserData;
import org.json.simple.JSONObject;

import java.util.Base64;
import java.util.Optional;

public class PlayerService {//TODO PlayerService

    private final String API_BASE_URL = "http://localhost:8080/GallowsGameAPI-1.0-SNAPSHOT/words/player/";
    private ApiConnector apiConnector;

    public boolean login(UserData userData) {
        if(apiConnector==null){
            apiConnector = new ApiConnector();
        }
        JSONObject jsonObject = generateToken(userData);
        String post = apiConnector.getDataFromUrl(API_BASE_URL + "login", "POST", Optional.of(jsonObject));
        if(post.equals("true")){
            System.out.println("logged");
            return true;
        }else {
            System.out.println("authentication failed");
            return false;
        }
    }

    public boolean register(UserData userData) {
        return false;
    }

    public void increaseScore(UserData userData) {

    }

    private JSONObject generateToken(UserData userData) {
        String userDataString = userData.getUsername() + ":" + userData.getPassword();
        byte[] encode = Base64.getEncoder().encode(userDataString.getBytes());
        JSONObject token = new JSONObject();
        token.put("token", new String(encode));
        return token;
    }

}
