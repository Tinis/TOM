package no.uib.inf101.tom.gameloop;

import no.uib.inf101.tom.model.TomModel;
import no.uib.inf101.tom.view.TomView;

public class GameLoop implements PausableGameLoop, Runnable{
    private TomModel model;
    private TomView view;
    private Thread gameThread;
    private boolean isRunning;

    public GameLoop(TomModel model, TomView view) {
        this.model = model;
        this.view = view;
        this.isRunning = true;

        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (this.gameThread != null) {

        }
    }


}
