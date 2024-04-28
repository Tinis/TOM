package no.uib.inf101.tom.gameloop;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.view.TomView;

public class GameLoop implements PausableGameLoop, Runnable{
    private Updatable model;
    private Updatable controller;
    private TomView view;
    private Thread gameThread;
    private boolean isRunning;

    public GameLoop(Updatable model, TomView view, Updatable controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
        this.isRunning = true;

        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //code inspired by RyiSnow - Game Loop and Key Input - How to Make a 2D Game in Java #2
        //url: https://youtu.be/VpH33Uw-_0E?t=1684
        double drawInterval = 1000000000 / Config.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //Finding the actual FPS
        long timer = 0;
        int drawCount = 0;

        while (this.gameThread != null) {
            if (this.isRunning) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    controller.update();
                    model.update();
                    view.repaint();
                    delta--;
                    drawCount += 1;
                }
                //Displaying the FPS
                if (timer >= 1000000000) {
                    if (Config.PRINT_ACTUAL_FPS) {
                        System.out.println("FPS: " + drawCount);
                    }
                    drawCount = 0;
                    timer = 0;
                }
            }
        }
    }

    @Override
    public void pause() {
        this.isRunning = false;
    }

    @Override
    public void start() {
        this.isRunning = true;
    }
}
