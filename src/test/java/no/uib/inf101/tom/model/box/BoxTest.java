package no.uib.inf101.tom.model.box;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tom.model.Coordinate;

public class BoxTest {
    @Test
    void testGetCenter() {
        //RectangularBox
        Box box = new RectangularBox(new Coordinate(0, 0), 2, 2);
        assertEquals(new Coordinate(0, 0), box.getCenter());
    }

    @Test
    void testGetCornerCoords() {
        Box box1 = new RectangularBox(new Coordinate(0, 0), 2, 2);
        Coordinate[] cornersExpected = new Coordinate[]{
            new Coordinate(-1, -1),
            new Coordinate(1, -1),
            new Coordinate(1, 1),
            new Coordinate(-1, 1)
        };
    }

    @Test
    void testOverlapsWith() {
        //RectangularBox
        Box box1 = new RectangularBox(new Coordinate(0, 0), 2, 2);
        Box box2 = new RectangularBox(new Coordinate(0, 0), 2, 2);
        assertTrue(box1.overlapsWith(box2));

        box2 = new RectangularBox(new Coordinate(2, 2), 0.1, 0.1);
        assertFalse(box1.overlapsWith(box2));
    }
}
