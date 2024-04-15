package no.uib.inf101.tom;

import javax.swing.JFrame;

import no.uib.inf101.tom.controller.TomController;
import no.uib.inf101.tom.gameloop.GameLoop;
import no.uib.inf101.tom.model.TomModel;
import no.uib.inf101.tom.view.TomView;

/**
 * TOM
 */
public class Main {

  public static void main(String[] args) {

    //Initializing model, view, controller and gameloop
    TomModel model = new TomModel(Config.START_UP_STATE);
    TomView view = new TomView(model);
    TomController controller = new TomController(model, view);
    GameLoop gameLoop = new GameLoop(model, view, controller);

    //Frame Initialization
    JFrame frame = new JFrame(Config.WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(view);
    
    frame.setResizable(Config.WINDOW_RESIZABLE);
    if (Config.WINDOW_FULLSCREEN) {
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      frame.setUndecorated(true);
    } else {
      frame.pack();
    }
    frame.setVisible(true);
  }
}
