package no.uib.inf101.tom.gameloop;

public interface Updatable {
    
    /**
     * Updates something. 
     * Such as the model or controller (makes stuff move and cutscenes go to the next frame). 
     */
    public void update();
}
