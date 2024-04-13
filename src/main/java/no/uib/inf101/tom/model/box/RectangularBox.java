package no.uib.inf101.tom.model.box;


import no.uib.inf101.tom.model.Coordinate;

public class RectangularBox extends Box{
    protected Coordinate pos;
    private double width;
    private double height;

    /**
     * 
     * @param pos the center of the Box
     * @param width the width of the Box
     * @param height the height of the Box
     */
    public RectangularBox(Coordinate pos, double width, double height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    @Override
    public Coordinate getCenter() {
        return this.pos;
    }

    @Override
    public Coordinate[] getCornerCoords() {
        Coordinate[] cornerCoords = new Coordinate[4];
        Coordinate upLeft = new Coordinate(
            this.pos.x() - this.width/2, this.pos.y() - this.height/2);
        cornerCoords[0] = upLeft;
        cornerCoords[1] = new Coordinate(upLeft.x() + this.width, upLeft.y());
        cornerCoords[2] = new Coordinate(upLeft.x() + this.width, upLeft.y() + this.height);
        cornerCoords[3] = new Coordinate(upLeft.x(), upLeft.y() + this.height);
        return cornerCoords;
    }

    @Override
    public String getName() {
        return "unnamed rectangular box";
    }   
    
}
