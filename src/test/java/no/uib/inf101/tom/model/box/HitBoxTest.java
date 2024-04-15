package no.uib.inf101.tom.model.box;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tom.model.Coordinate;

public class HitBoxTest {

    private final HitBox hit1 = new HitBox(
        new Coordinate(0, 0), new Coordinate(10, 0), 5, 2);

    @Test
    void testGetCenter() {
        Coordinate center1 = hit1.getCenter();
        assertEquals(new Coordinate(2.5, 0), center1);
    }

    @Test
    void testGetCornerCoords() {
        Coordinate[] corners = hit1.getCornerCoords();
        assertEquals(new Coordinate(0, 1), corners[0]);
        assertEquals(new Coordinate(0, -1), corners[1]);
        assertEquals(new Coordinate(5, -1), corners[2]);
        assertEquals(new Coordinate(5, 1), corners[3]);
    }
}
