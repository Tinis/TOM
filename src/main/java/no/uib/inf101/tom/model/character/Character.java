package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.box.CharacterBox;
import no.uib.inf101.tom.model.box.ViewableBox;

public abstract class Character extends CharacterBox implements ViewableCharacter{
    protected String name;

    public Character(Coordinate pos, double width, double height) {
        super(pos, width, height);
    }

    @Override
    public ViewableBox getBox() {
        return this;
    }



    
}
