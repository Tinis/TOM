package no.uib.inf101.tom.model.box;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.PlaneVector;

public class HitBox extends Box {
    private Coordinate[] corners;
    private Coordinate center;

    public HitBox(Coordinate from, Coordinate towards, double reach, double width) {
        PlaneVector reachVector = new PlaneVector(from, towards);
        reachVector.setLength(reach);
        Coordinate endOfReachVector = PlaneVector.coordMoved(from, reachVector);

        PlaneVector widthVector1 = reachVector.rotate(true);
        widthVector1.setLength(width/2);
        PlaneVector widthVector2 = reachVector.rotate(false);
        widthVector2.setLength(width/2);

        this.corners[0] = PlaneVector.coordMoved(from, widthVector1);
        this.corners[1] = PlaneVector.coordMoved(from, widthVector2);
        this.corners[2] = PlaneVector.coordMoved(endOfReachVector, widthVector2);
        this.corners[3] = PlaneVector.coordMoved(endOfReachVector, widthVector1);
        //Don't worry these will always be in a clockwise order. 
        //now i set the reachvector to half the length and find the center. 
        reachVector.setLength(reach/2);
        this.center = PlaneVector.coordMoved(from, reachVector);
    }

    @Override
    public Coordinate[] getCornerCoords() {
        return this.corners;
    }

    @Override
    public Coordinate getCenter() {
        return this.center;
    }

    @Override
    public String getName() {
        return "unnamed hitbox";
    }


}
