package no.uib.inf101.tom.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlaneVectorTest {
    @Test
    void testGetLength() {
        PlaneVector vector = new PlaneVector(new Coordinate(1, 1), new Coordinate(4, 5));
        assertEquals(5, vector.getLength());

        vector = new PlaneVector(new Coordinate(1, 1), new Coordinate(-2, 5));
        assertEquals(5, vector.getLength());

        vector = new PlaneVector(new Coordinate(0, 0), new Coordinate(0, 0));
        assertEquals(0, vector.getLength());
    }

    @Test
    void testNormal() {
        //tests clockwise normal
        PlaneVector vector = new PlaneVector(1, 1);
        vector = vector.rotate(true);
        PlaneVector expected = new PlaneVector(-1, 1);
        assertEquals(expected, vector);
        //tests if its the same as it was when i rotate it back (counterClockWise)
        vector = vector.rotate(false);
        expected = new PlaneVector(1, 1);
        assertEquals(expected, vector);
    }

    @Test
    void testProjectedOnto() {
        //with positive numbers
        PlaneVector vector1 = new PlaneVector(3, 2);
        PlaneVector vector2 = new PlaneVector(1, 0);
        PlaneVector projection = vector1.projectedOnto(vector2);
        PlaneVector expectedProjection = new PlaneVector(3, 0);
        assertEquals(expectedProjection, projection);

        //with negative numbers
        vector1 = new PlaneVector(-3, 2);
        vector2 = new PlaneVector(1, 0);
        projection = vector1.projectedOnto(vector2);
        expectedProjection = new PlaneVector(-3, -0.0);
        assertEquals(expectedProjection, projection);
    }

    @Test
    void testSetLength() {
        PlaneVector vector = new PlaneVector(new Coordinate(1, 1), new Coordinate(4, 5));
        vector.setLength(20);
        assertEquals(20, vector.getLength());

        vector = new PlaneVector(new Coordinate(0, 0), new Coordinate(0, 0));
        vector.setLength(20);
        assertEquals(0, vector.getLength());
        
    }

    @Test
    void testNormalize() {
        PlaneVector vector = new PlaneVector(3, 3);
        vector.normalize();
        assertEquals(1, vector.getLength());
        
    }
}
