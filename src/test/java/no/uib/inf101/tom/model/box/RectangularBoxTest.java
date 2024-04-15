package no.uib.inf101.tom.model.box;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tom.model.Coordinate;

public class RectangularBoxTest {
    @Test
    void testGetCenter() {
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
        Coordinate[] cornersActual = box1.getCornerCoords();
        for (int i = 0; i < cornersActual.length; i++) {
            assertEquals(cornersExpected[i], cornersActual[i]);
        }
    }

}
