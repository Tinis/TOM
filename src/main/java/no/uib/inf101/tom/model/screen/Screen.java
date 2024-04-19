package no.uib.inf101.tom.model.screen;

import java.util.ArrayList;

/**
 * A screen is an image with buttons on it that do things. 
 * A screen's button can bring up a different screen
 *  or load a level or quit the game and stuff.  
 */
public class Screen {
    private String name;
    private ArrayList<Button> buttonList;

    public Screen(String name) {
        this.buttonList = new ArrayList<>();
    }
/////////
//GETTERS
/////////
    public ArrayList<Button> getButtons() {
        return this.buttonList;
    }

    public String getName() {
        return this.name;
    }

/////////
//PUTTERS
/////////
    public void putButton(Button button) {
        this.buttonList.add(button);
    }


}
