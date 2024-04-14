package no.uib.inf101.tom.model;

import no.uib.inf101.tom.Config;

/**
 * a record that represents a position in a 2dimensional plane (such as a level). 
 * The coordinate (0, 0) is always at the middle of the LevelImage. 
 */
public record Coordinate(double x, double y) {

    public boolean isAlmostEqualTo(Coordinate coord) {
        double xDiff = Math.abs(coord.x() - this.x());
        double yDiff = Math.abs(coord.y() - this.y());
        return (
            xDiff < Config.COORDINATE_ALMOSTEQUALS_PRECISION && 
            yDiff < Config.COORDINATE_ALMOSTEQUALS_PRECISION);
    }
}
