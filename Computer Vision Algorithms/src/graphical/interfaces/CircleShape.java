package graphical.interfaces;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Shape;

/**
 * This interface represents an object shaped like a circle
 * @param <T>
 */
public interface CircleShape<T extends Shape> {
    /**
     * This method sets the radius for the shape
     * @param radius radius of the shape
     */
    void setShapeRadius(double radius);

    /**
     * @return returns the radius of the circle
     */
    double getRadius();

    /**
     * @return returns the property that represents the x-position of the center of the shape
     */
    DoubleProperty xCenterPositionProperty();

    /**
     * @return returns the property that represents the y-position of the center of the shape
     */
    DoubleProperty yCenterPositionProperty();

    /**
     * @return returns the property that represents the radius of the shape
     */
    DoubleProperty shapeRadiusProperty();

    /**
     * @return returns the shape of the object
     */
    Shape getShape();

}
