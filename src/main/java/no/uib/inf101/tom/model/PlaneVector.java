package no.uib.inf101.tom.model;

import java.lang.Math;


/**
 * A 2D-Vector represented by an x-field and a y-field (both are doubles). 
 * Can get and set its length. Can not change its direction. 
 * Create a new Vector if you want to change the direction. 
 */
public class PlaneVector {
    private double x;
    private double y;

    
    public PlaneVector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public PlaneVector(Coordinate start, Coordinate end) {
        double xDiff = end.x() - start.x();
        double yDiff = end.y() - start.y();
        this.x = xDiff;
        this.y = yDiff;
    }

    public PlaneVector(Coordinate start, Coordinate end, double length) {
        this(start, end);
        setLength(length);
    }

        /**
     * 
     * @return the x value of the vector.
     */
    public double getX() {
        return x;
    }

    /**
     * 
     * @return the y value of the vector.
     */
    public double getY() {
        return y;
    }

    private PlaneVector scaledBy(double scalar) {
        double x = this.getX() * scalar;
        double y = this.getY() * scalar;
        return new PlaneVector(x, y);
    }

    private double dotProduct(PlaneVector otherVector) {
        return (this.getX() * otherVector.getX()) + 
            (this.getY() * otherVector.getY());
    }


    /**
     * @param clockwise Specifies which direction the vector is to be rotated. 
     * If parameter is true, the rotation will be clockwise. 
     * If it is false, the rotation will be counterclockwise.
     * @return a vector that is perpendicular to this vector. 
     * The returned vector is the same as this vector but it has been rotates 90 degrees
     */
    public PlaneVector normal(boolean clockwise) {
        if (clockwise) {
            return new PlaneVector(this.getY(), -this.getX());
        } else {
            return new PlaneVector(-this.getY(), this.getX());
        }
    }

    /**
     * projects the vector onto another vector. 
     * The returned vector will be pointing in the direction of the given vector 
     * (the one in the argument).
     * @param otherVector the vector that is to be projected onto.
     * @return the projection. 
     */
    public PlaneVector projectedOnto(PlaneVector otherVector) {
        double scalar = this.dotProduct(otherVector) / 
            Math.pow(otherVector.getLength(), 2);
        return otherVector.scaledBy(scalar);
    }

    /**
     * 
     * @return the length of this vector.
     */
    public double getLength() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     * Sets the length of the vector without changing the direction. 
     * If the current vector is a null-vector, the length will not increase. 
     * @param newLength the new length of the vector. 
     */
    public void setLength(double newLength) {
        if (getLength() != 0.0) {
            double scalar = newLength / getLength();
            this.x *= scalar;
            this.y *= scalar;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlaneVector other = (PlaneVector) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }

    
}
