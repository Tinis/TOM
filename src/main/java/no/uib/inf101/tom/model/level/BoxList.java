package no.uib.inf101.tom.model.level;

import java.util.ArrayList;
import java.util.Iterator;

import no.uib.inf101.tom.model.box.Box;


//TODO: This class may be useless.
public class BoxList<E extends Box> implements Iterable<E>{

    private ArrayList<E> loadedObjects;

    public BoxList(Level level) {

    }

    @Override
    public Iterator<E> iterator() {
        return loadedObjects.iterator();
    }

    

}
