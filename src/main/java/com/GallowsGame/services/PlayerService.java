package com.GallowsGame.services;

import com.GallowsGame.models.Player;
import com.GallowsGame.models.RequestMethod;
import com.GallowsGame.models.ResponseTokenWithPlayer;
import com.GallowsGame.models.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

public class PlayerService {//TODO PlayerService

    private Player player;
    private final String API_BASE_URL = "http://localhost:8080/GallowsGameAPI-1.0-SNAPSHOT/words/player/";
    private ApiConnector apiConnector;

    public PlayerService() {
        apiConnector = new ApiConnector();
    }

    public boolean login(UserData userData) {
        JSONObject jsonObject = generateToken(userData);
        String response = apiConnector.getDataFromUrl(API_BASE_URL + "login", RequestMethod.POST.get(), Optional.of(jsonObject));
        ResponseTokenWithPlayer responseTokenWithPlayer = readResponse(response);
        if(responseTokenWithPlayer.isOperationSuccessful()){
                player = responseTokenWithPlayer.getPlayerDto();
                return true;
        }else {
            return false;
        }
    }

    public boolean register(UserData userData) {
        JSONObject jsonObject = generateToken(userData);
        String response = apiConnector.getDataFromUrl(API_BASE_URL + "register", RequestMethod.POST.get(), Optional.of(jsonObject));
        ResponseTokenWithPlayer responseTokenWithPlayer = readResponse(response);
        if(responseTokenWithPlayer.isOperationSuccessful()){
                System.out.println(responseTokenWithPlayer.getPlayerDto().getUsername() + " registered");
            return true;
        }else {
            System.out.println(responseTokenWithPlayer.getMessage());
            return false;
        }
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

    private ResponseTokenWithPlayer readResponse(String response){
        ResponseTokenWithPlayer responseTokenWithPlayer = new ResponseTokenWithPlayer();
        ObjectMapper mapper = new ObjectMapper();
        try {
            responseTokenWithPlayer = mapper.readValue(response, ResponseTokenWithPlayer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseTokenWithPlayer;
    }

    public Player getPlayer() {
        return player;
    }
}
