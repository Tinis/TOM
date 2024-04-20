package no.uib.inf101.tom.model;

import java.util.ArrayList;
import java.util.List;

public class ObservableGameState {
    //Struktur for klassen er frå kursnotatene:
    //https://inf101v23.stromme.me/notat/observer/
    private GameState gameState;
    private List<GameStateListener> listeners;

    public ObservableGameState(GameState initialState) {
        this.gameState = initialState;
        this.listeners = new ArrayList<>();
    }

    public void addGameStateListener(GameStateListener listener) {
        this.listeners.add(listener);
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setGameState(GameState newGameState) {
        this.gameState = newGameState;
        this.notifyListeners(newGameState);
    }

    private void notifyListeners(GameState newGameState) {
        for (GameStateListener listener : this.listeners) {
            listener.onGameStateChange(newGameState);
        }
    }


}
