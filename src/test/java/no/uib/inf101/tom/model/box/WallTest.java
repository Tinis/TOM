package no.uib.inf101.tom.model.box;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tom.model.Coordinate;

public class WallTest {
    @Test
    void testGetCenter() {
        Wall wall = new Wall(new Coordinate(0, 0), new Coordinate(2, 0));
        assertEquals(new Coordinate(1, 0), wall.getCenter());
    }

    @Test
    void testGetCornerCoords() {
        Wall wall = new Wall(new Coordinate(0, 0), new Coordinate(1, 0));
        Coordinate[] expected = new Coordinate[4];
        expected[0] = new Coordinate(-0.5, -0.5);
        expected[1] = new Coordinate(1.5, -0.5);
        expected[2] = new Coordinate(1.5, 0.5);
        expected[3] = new Coordinate(-0.5, 0.5);
        Coordinate[] actual = wall.getCornerCoords();
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
