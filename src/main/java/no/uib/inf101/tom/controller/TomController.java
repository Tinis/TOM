package no.uib.inf101.tom.controller;

import no.uib.inf101.tom.view.TomView;

public class TomController {
    private ControllableModel model;
    private TomView view;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public TomController(ControllableModel model, TomView view) {
        this.model = model;
        this.view = view;
        this.keyHandler = new KeyHandler(model, view);
        this.mouseHandler = new MouseHandler(model, view);
    }


    
}
