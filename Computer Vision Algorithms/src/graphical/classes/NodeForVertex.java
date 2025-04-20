package graphical.classes;

import DataStructures.interfaces.Edge;
import DataStructures.interfaces.Vertex;
import graphical.interfaces.VertexGUI;
import graphical.styling.StyledNode;
import graphical.styling.Styler;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Circle;

import java.util.Collection;
import java.util.List;

public class NodeForVertex<V, E> extends Group implements VertexGUI<V, E>, StyledNode {
    /*
        class variables
     */
    private final Vertex<V, E> theUnderlyingVertexObject;
    private  final DoubleProperty xCenterPosition;
    private final DoubleProperty yCenterPosition;
    private final DoubleProperty circleRadius;
    private final NodeCircle vertexShape;
    private final NodeShape styledShape;
    private NodeLabel vertexLabel;

    public NodeForVertex(Vertex<V, E> underlyingVertex, double x, double y, double radius){
        this.theUnderlyingVertexObject = underlyingVertex;
        this.vertexLabel = null;
        this.xCenterPosition = new SimpleDoubleProperty();
        this.yCenterPosition = new SimpleDoubleProperty();
        this.circleRadius =  new SimpleDoubleProperty();
        vertexShape =  new NodeCircle(x, y, radius); // create the circle for the vertex
        this.styledShape = new NodeShape(this.vertexShape.getShape()); // get the vertex shape to style it
        this.getChildren().add(this.vertexShape.getShape()); // add shape to group
    }

    /**
     * This method will bind a label to node
     *
     * @param label the styled label
     */
    @Override
    public void attachStyledLabelToNode(NodeLabel label) {
        this.vertexLabel = label; // update
        // make calculations to attach label to the vertex
        label.xProperty().bind(xCenterPosition.subtract(Bindings.divide(label.widthProperty(), 2.2)));
        label.yProperty().bind(yCenterPosition.add(Bindings.add(vertexShape.shapeRadiusProperty(), label.heightProperty())));
    }

    /**
     * @return returns the label attached to a node
     */
    @Override
    public NodeLabel getAttachedLabelToNode() {
        return this.vertexLabel;
    }

    /**
     * This method sets the position of the vertex using the x,y coordinates
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    @Override
    public void setVertexPosition(double x, double y) {

    }

    /**
     * This method sets the x-position of the center of the vertex
     *
     * @param x the x-position
     */
    @Override
    public void setXCenterPosition(double x) {
        this.xCenterPosition.set(x);
    }

    /**
     * This method sets the y-position of the center of the vertex
     *
     * @param y the y-position
     */
    @Override
    public void setYCenterPosition(double y) {
        this.yCenterPosition.set(y);
    }

    /**
     * This method sets the radius for the  circle representing the vertex
     *
     * @param radius the radius of the circle
     */
    @Override
    public void setShapeRadius(double radius) {
        this.circleRadius.set(radius);
    }

    /**
     * @return returns the x-position of the center of the vertex
     */
    @Override
    public double getXCenterPosition() {
        return this.xCenterPosition.doubleValue();
    }

    /**
     * @return returns the y-position of the center of the vertex
     */
    @Override
    public double getYCenterPosition() {
        return this.yCenterPosition.doubleValue();
    }

    /**
     * @return returns the radius of the circle that represents the vertex in the GUI
     */
    @Override
    public double getCircleRadius() {
        return this.circleRadius.doubleValue();
    }

    /**
     * @return returns the actual vertex object that this circle represents on the gui
     */
    @Override
    public Vertex<V, E> getUnderlyingVertex() {
        return this.theUnderlyingVertexObject;
    }

    /**
     * @return returns a labeled node for the vertex
     */
    @Override
    public Styler getStylingForVertex() {
        return this;
    }

    /**
     * @return returns the property representing the x-position of the center of the vertex
     */
    @Override
    public DoubleProperty getXCenterProperty() {
        return this.xCenterPosition;
    }

    /**
     * @return returns the property representing the y-position of the center of the vertex
     */
    @Override
    public DoubleProperty getYCenterProperty() {
        return this.yCenterPosition;
    }

    /**
     * @return returns the property representing the radius of the shape of the vertex
     */
    @Override
    public DoubleProperty getShapeRadiusProperty() {
        return this.circleRadius;
    }

    /**
     * This method sets the css style to the underlying node
     *
     * @param css the ccs style
     */
    @Override
    public void setInlineStyle(String css) {
        this.styledShape.setInlineStyle(css);
    }

    /**
     * This method replaces the css class for the underlying node
     *
     * @param css_class the css class
     */
    @Override
    public void setStyleClass(String css_class) {
        this.styledShape.setStyleClass(css_class);
    }

    /**
     * This method adds the css class for the underlying node
     *
     * @param css_class the css class
     */
    @Override
    public void addStyleClass(String css_class) {
        this.styledShape.addStyleClass(css_class);
    }
}
