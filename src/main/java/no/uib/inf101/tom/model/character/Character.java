package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.box.CharacterBox;

public abstract class Character extends CharacterBox{
    protected String name;

    public Character(Coordinate pos, double width, double height) {
        super(pos, width, height);
    }



    
}
