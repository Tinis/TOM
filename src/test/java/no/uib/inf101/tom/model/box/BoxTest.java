package no.uib.inf101.tom.model.box;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tom.model.Coordinate;

public class BoxTest {

    @Test
    void testOverlapsWithTwoRectangles() {
        //RectangularBoxes
        Box box1 = new RectangularBox(new Coordinate(0, 0), 2, 2);
        Box box2 = new RectangularBox(new Coordinate(0, 0), 2, 2);
        assertTrue(box1.overlapsWith(box2));

        box2 = new RectangularBox(new Coordinate(2, 2), 0.1, 0.1);
        assertFalse(box1.overlapsWith(box2));

        box2 = new RectangularBox(new Coordinate(3, 0), 2, 2);
        assertFalse(box1.overlapsWith(box2));

        box2 = new RectangularBox(new Coordinate(0.5, 0), 2, 2);
        assertTrue(box1.overlapsWith(box2));
    }
    @Test
    void testOverlapsWithRectangleHitbox() {
        Box box1 = new RectangularBox(new Coordinate(0, 0), 2, 2);
        Box hitBox = new HitBox(
            new Coordinate(0, 0), new Coordinate(10, 10), 10, 3);
        assertTrue(box1.overlapsWith(hitBox));
        assertTrue(hitBox.overlapsWith(box1));

        hitBox = new HitBox(
            new Coordinate(10, 10), new Coordinate(100, -200), 20, 1);
        assertFalse(box1.overlapsWith(hitBox));
        assertFalse(hitBox.overlapsWith(box1));
    }
}
