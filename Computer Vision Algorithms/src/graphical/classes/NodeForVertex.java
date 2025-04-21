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

import java.util.*;

public class NodeForVertex<V, E> extends Group implements VertexGUI<V, E>{
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
    private final List<VertexGUI<V, E>> adjacentVertexes;


    public NodeForVertex(Vertex<V, E> underlyingVertex, double x, double y, double radius){
        this.theUnderlyingVertexObject = underlyingVertex; // setting underlying vertex
        this.vertexLabel = null; // will update this in later stage
        this.xCenterPosition = new SimpleDoubleProperty(); // initializing dynamic property  for the x-position of the center of the node
        this.yCenterPosition = new SimpleDoubleProperty(); // initializing dynamic property  for the y-position of the center of the node
        this.circleRadius =  new SimpleDoubleProperty(); // initializing dynamic property  for the radius of the node
        this.adjacentVertexes = new ArrayList<>(); // crating list of the adjacent vertexes
        vertexShape =  new NodeCircle(x, y, radius); // create the circle for the vertex
        ShapePropertiesBinder(vertexShape); // now since I have created a circle shape for the vertex, now I am binding the properties of the circle with vertex node
        this.styledShape = new NodeShape(this.vertexShape.getShape()); // get the shape for the vertex to style it
        this.getChildren().add(this.vertexShape.getShape()); // add shape to group node
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

    /**
     * THis method adds an adjacent vertex to the list
     * @param vertexNode the adjacent vertex
     */
    @Override
    public void addAdjacentVertex(VertexGUI<V, E> vertexNode){
        this.adjacentVertexes.add(vertexNode);
    }

    /**
     * THis method removes the vertex adjacent to the current vertex
     * @param vertexNode the vertex to be removed
     * @return returns the removed vertex
     */
    @Override
    public VertexGUI<V, E> removeAdjacentVertex(VertexGUI<V, E> vertexNode){
        VertexGUI<V, E> vertex = null;
        if(this.adjacentVertexes.contains(vertexNode)){
            vertex = vertexNode;
            this.adjacentVertexes.remove(vertexNode);
        }
        return vertex;
    }

    /**
     * This method returns a collection of adjacent vertexes from the current list
     * @param vertexes the collection of vertexes
     * @return returns the removed collection
     */
    @Override
    public List<VertexGUI<V,E>> removeAdjacentVertexes(Collection<VertexGUI<V, E>> vertexes){
        Collection<VertexGUI<V, E>> vertexGUIS = null;
        if(new HashSet<>(this.adjacentVertexes).containsAll(vertexes)){
            vertexGUIS = vertexes;
        }
        return (List<VertexGUI<V, E>>) vertexGUIS;
    }

    /**
     * This method checks if the given vertex is adjacent to the current one
     * @param vertexGUI the vertex to be validated
     * @return returns true if it is adjacent else false
     */
    @Override
    public boolean isAdjacent(VertexGUI<V, E> vertexGUI){
        return this.adjacentVertexes.contains(vertexGUI);
    }

    /**
     * @return returns the number of adjacent vertexes to this one
     */
    @Override
    public int adjacentVertexes(){
        return this.adjacentVertexes.size();
    }

    /**
     * This method binds the properties of the vertex node with properties of the shape of the vertex
     * @param vertexShape the shape for the vertex
     */
    private void ShapePropertiesBinder(NodeCircle vertexShape){
        if(this.vertexShape != null){
            if(this.xCenterPosition.isBound()){
                this.xCenterPosition.unbindBidirectional(this.vertexShape.xCenterPositionProperty());
            }
            if(this.yCenterPosition.isBound()){
                 this.yCenterPosition.unbindBidirectional(this.vertexShape.yCenterPositionProperty());
            }

            if(this.circleRadius.isBound()){
                this.circleRadius.unbindBidirectional(this.vertexShape.shapeRadiusProperty());
            }
        }else {
            this.xCenterPosition.bindBidirectional(vertexShape.xCenterPositionProperty());
            this.yCenterPosition.bindBidirectional(vertexShape.yCenterPositionProperty());
            this.circleRadius.bindBidirectional(vertexShape.shapeRadiusProperty());
        }
    }
}
