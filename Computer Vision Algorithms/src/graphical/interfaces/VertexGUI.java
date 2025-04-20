package graphical.interfaces;

import DataStructures.interfaces.Vertex;
import graphical.styling.Styler;
import javafx.beans.property.DoubleProperty;

/**
 * This interface represents a vertex in the GUI
 */
public interface VertexGUI<V, E>  extends Styler {
    /**
     * This method sets the position of the vertex using the x,y coordinates
     * @param x x-coordinate
     * @param y y-coordinate
     */
    void setVertexPosition(double x, double y);

    /**
     * This method sets the x-position of the center of the vertex
     * @param x the x-position
     */
    void setXCenterPosition(double x);

    /**
     * This method sets the y-position of the center of the vertex
     * @param y the y-position
     */
    void setYCenterPosition(double y);

    /**
     * This method sets the radius for the  circle representing the vertex
     * @param radius the radius of the circle
     */
    void setShapeRadius(double radius);

    /**
     * @return returns the x-position of the center of the vertex
     */
    double getXCenterPosition();

    /**
     * @return returns the y-position of the center of the vertex
     */
    double getYCenterPosition();

    /**
     * @return returns the radius of the circle that represents the vertex in the GUI
     */
    double getCircleRadius();

    /**
     * @return returns the actual vertex object that this circle represents on the gui
     */
    Vertex<V,E> getUnderlyingVertex();

    /**
     * @return returns a labeled node for the vertex
     */
    Styler getStylingForVertex();

    /**
     * @return returns the property representing the x-position of the center of the vertex
     */
    DoubleProperty getXCenterProperty();

    /**
     * @return returns the property representing the y-position of the center of the vertex
     */
    DoubleProperty getYCenterProperty();

    /**
     * @return returns the property representing the radius of the shape of the vertex
     */
    DoubleProperty getShapeRadiusProperty();



}
