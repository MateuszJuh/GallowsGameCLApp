package com.GallowsGame.models;

import java.util.Optional;

public class ResponseTokenWithPlayer {
    private boolean isOperationSuccessful;
    private Optional<Player> playerDto;

    public ResponseTokenWithPlayer() {
    }

    public boolean isOperationSuccessful() {
        return isOperationSuccessful;
    }

    public void setOperationSuccessful(boolean operationSuccessful) {
        isOperationSuccessful = operationSuccessful;
    }

    public Optional<Player> getPlayerDto() {
        return playerDto;
    }

    public void setPlayerDto(Optional<Player> playerDto) {
        this.playerDto = playerDto;
    }
}
