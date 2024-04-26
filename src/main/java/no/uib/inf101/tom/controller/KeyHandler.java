package no.uib.inf101.tom.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.action.Dash;
import no.uib.inf101.tom.model.action.Punch;
import no.uib.inf101.tom.model.action.Shoot;
import no.uib.inf101.tom.view.TomView;

public class KeyHandler implements KeyListener{
    private ControllableModel model;
    private TomView view;

    public KeyHandler(ControllableModel model, TomView view) {
        this.model = model;
        this.view = view;
        this.view.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //CONSTANT KEYS (THEY ALWAYS WORK)
        if (code == Config.KILL_KEY) {
            System.out.println(
                "Closing program using kill key: " + KeyEvent.getKeyText(Config.KILL_KEY));
            System.exit(0);
        } else if (code == Config.DEBUG_KEY) {
            this.model.toggleDebug();
        }
        //QWER (ABILITIES and stuff that only works during an active game (the filter is in the model))
        if (code == Config.PUNCH_KEY) {
            this.model.sendAction(new Punch());
        } else if (code == Config.DASH_KEY) {
            this.model.sendAction(new Dash());
        } else if (code == Config.SHOOT_KEY) {
            this.model.sendAction(new Shoot());
        }
        if (code == Config.PAUSE_KEY) {
            this.model.pause();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    

}
