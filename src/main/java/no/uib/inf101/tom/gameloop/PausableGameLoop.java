package no.uib.inf101.tom.gameloop;

public interface PausableGameLoop {

    /**
     * pauses the gameloop. The thread will still be running but no updates nor redraws will be done. 
     */
    public void pause();

    /**
     * starts the gameloop.
     */
    public void start();
}
