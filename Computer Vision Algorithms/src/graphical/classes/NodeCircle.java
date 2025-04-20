package graphical.classes;

import graphical.interfaces.CircleShape;
import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class NodeCircle implements CircleShape<Circle> {
    /*
        class variables
     */
    private final Circle underlyingCircle;

    public NodeCircle(double x, double y, double radius){
        assert x > 0 && y > 0 && radius > 0; // ensure values are positive and meaningful
        this.underlyingCircle = new Circle(x, y, radius);
    }
    /**
     * This method sets the radius for the shape
     *
     * @param radius radius of the shape
     */
    @Override
    public void setShapeRadius(double radius) {
        this.underlyingCircle.setRadius(radius);
    }

    /**
     * @return returns the radius of the circle
     */
    @Override
    public double getRadius() {
        return this.underlyingCircle.getRadius();
    }

    /**
     * @return returns the property that represents the x-position of the center of the shape
     */
    @Override
    public DoubleProperty xCenterPositionProperty() {
        return this.underlyingCircle.centerXProperty();
    }

    /**
     * @return returns the property that represents the y-position of the center of the shape
     */
    @Override
    public DoubleProperty yCenterPositionProperty() {
        return this.underlyingCircle.centerYProperty();
    }

    /**
     * @return returns the property that represents the radius of the shape
     */
    @Override
    public DoubleProperty shapeRadiusProperty() {
        return this.underlyingCircle.radiusProperty();
    }

    /**
     * @return returns the shape of the object
     */
    @Override
    public Shape getShape() {
        return this.underlyingCircle;
    }
}
