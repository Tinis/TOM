package no.uib.inf101.tom.model.box;

import java.util.ArrayList;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.CoordinatePointConverter;
import no.uib.inf101.tom.model.PlaneVector;

/**
 * A box. A two dimensional convex polygon.
 */
public abstract class Box implements ViewableBox{

    /**
     * Checks if the box overlaps with another box using the seperating axis theorem.
     * @param otherBox the other box that this may overlap with.
     * @return true if it overlaps, false if not. 
     */
    public boolean overlapsWith(Box otherBox){
        //pseudo code by pikuma - Collision Detection with SAT (Math for Game Developers) 
        //url: https://www.youtube.com/watch?v=-EsWKT7Doww&ab_channel=pikuma
        ArrayList<Double> seperations = new ArrayList<>();
        Coordinate[] myVertices = this.getCornerCoords();
        Coordinate[] otherVertices = otherBox.getCornerCoords();
        for (int i = 0; i < myVertices.length; i++) {
            int iForNextVertex = i + 1; //index value for the next vertex in the list of myVertices.
            if (iForNextVertex == myVertices.length) {
                iForNextVertex -= myVertices.length; 
                //i do this because we need to check every edge vector and one of the edge vectors 
                //go from the last vertex to the first vertex
            }
            PlaneVector edgeVector = new PlaneVector(myVertices[i], myVertices[iForNextVertex]);
            //The edgeVector will follow in the clockwise direction. 
            //Because that is how the getCornerCoords() method works. 
            PlaneVector normal = edgeVector.rotate(false);
            normal.normalize();
            //Since i rotate the edgevector counterclockwise,
            //it will be facing away from the center of the box.
            ArrayList<Double> seperationsForOneNormal = new ArrayList<>();
            for (Coordinate vertex : otherVertices) {
                PlaneVector myVertexToOtherVertex = new PlaneVector(myVertices[i], vertex);
                double projectionLength = myVertexToOtherVertex.dotProduct(normal);
                seperationsForOneNormal.add(projectionLength);
            }
            seperations.add(smallestDouble(seperationsForOneNormal));
        }
        return (biggestDouble(seperations) < 0);
    }

    private double smallestDouble(ArrayList<Double> list) {
        double smallest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < smallest) {
                smallest = list.get(i);
            }
        }
        return smallest;
    }

    private double biggestDouble(ArrayList<Double> list) {
        double biggest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > biggest) {
                biggest = list.get(i);
            }
        }
        return biggest;
    }

    @Override
    public Shape getShape(CoordinatePointConverter converter) {
        Coordinate[] coords = this.getCornerCoords();
        double[] xValues = new double[coords.length];
        double[] yValues = new double[coords.length];
        for (int i = 0; i < coords.length; i++) {
            Point2D point = converter.pointFromCoordinate(coords[i]);
            double x = point.getX();
            double y = point.getY();
            xValues[i] = x;
            yValues[i] = y;
        }
        Path2D polygon = new Path2D.Double();
        polygon.moveTo(xValues[0], yValues[0]);
        for (int i = 1; i < xValues.length; i++) {
            polygon.lineTo(xValues[i], yValues[i]);
        }
        polygon.closePath();
        return polygon;
    }


    /**
     * Gets the coordinates at the four corners of the box. 
     * They are ordered in such a way that if you were to follow the coordinates in the array 
     * from beginning to end, you would follow the outline and never cross through 
     * the center of the box. You would follow the corners in a clockwise fashion. For example:
     * If the box is a square and the Coordinate at index=0 is the top right, the Coordinate
     * at index=1 is the bottom right. 
     * @return the four corners of the box as an array of Coordinate records. 
     * 
     */
    public abstract Coordinate[] getCornerCoords();


}

