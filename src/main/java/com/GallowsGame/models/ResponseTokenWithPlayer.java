package com.GallowsGame.models;

public class ResponseTokenWithPlayer {
    private boolean isOperationSuccessful;
    private Player playerDto;
    private String message;

    public ResponseTokenWithPlayer() {
    }

    public boolean isOperationSuccessful() {
        return isOperationSuccessful;
    }

    public void setOperationSuccessful(boolean operationSuccessful) {
        isOperationSuccessful = operationSuccessful;
    }

    public Player getPlayerDto() {
        return playerDto;
    }

    public void setPlayerDto(Player playerDto) {
        this.playerDto = playerDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
