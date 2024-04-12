package no.uib.inf101.tom.model;

public interface GameStateListener {

    /**
     * a method to be called whenever the gamestate changes.
     * @param newGameState the new game state that there has been a change to. 
     */
    void onGameStateChange(GameState newGameState);
}
