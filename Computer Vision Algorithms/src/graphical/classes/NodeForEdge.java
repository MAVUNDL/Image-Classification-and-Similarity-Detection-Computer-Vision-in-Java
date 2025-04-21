package graphical.classes;

import DataStructures.interfaces.Edge;
import graphical.interfaces.EdgeGUI;
import graphical.interfaces.LabelGUI;
import graphical.interfaces.VertexGUI;
import graphical.styling.StyledNode;
import graphical.styling.Styler;
import javafx.beans.binding.Bindings;
import javafx.scene.shape.Line;

public class NodeForEdge<V,E> extends Line implements EdgeGUI<V, E>{
    /*
        Class properties
     */
    private final  Edge<V,E> underlyingEdge;
    private final VertexGUI<V, E> startVertex;
    private final VertexGUI<V, E> endVertex;
    private LabelGUI edgeLabel =  null;
    private Styler edgeShapeStyler;

    public NodeForEdge(VertexGUI<V, E> startVertex, VertexGUI<V, E> endVertex, Edge<V, E> edge){
        assert startVertex != null && endVertex != null;
        this.startVertex = startVertex; // set starting vertex
        this.endVertex = endVertex; // set ending vertex
        this.underlyingEdge = edge; // set edge connecting the two vertexes
        this.edgeShapeStyler = new NodeShape(this); // get the shape for the edge for styling
        BindEdgeLineWithVertexes();
    }

    /**
     * This method binds the line properties start and end with start and end vertex properties
     */
    private void BindEdgeLineWithVertexes(){
        this.startXProperty().bind(startVertex.getXCenterProperty());
        this.startYProperty().bind(startVertex.getYCenterProperty());
        this.endXProperty().bind(endVertex.getXCenterProperty());
        this.endYProperty().bind(endVertex.getYCenterProperty());
    }

    /**
     * @return returns the underlying Node object
     */
    @Override
    public Edge<E, E> getUnderlyingEdge() {
        return (Edge<E, E>) this.underlyingEdge;
    }

    /**
     * @return returns the Label node for stying
     */
    @Override
    public StyledNode getStylableNode() {
        return (StyledNode) this.edgeLabel;
    }

    /**
     * This method sets the css style to the underlying node
     *
     * @param css the ccs style
     */
    @Override
    public void setInlineStyle(String css) {
        this.edgeShapeStyler.setInlineStyle(css);
    }

    /**
     * This method replaces the css class for the underlying node
     *
     * @param css_class the css class
     */
    @Override
    public void setStyleClass(String css_class) {
        this.edgeShapeStyler.setStyleClass(css_class);
    }

    /**
     * This method adds the css class for the underlying node
     *
     * @param css_class the css class
     */
    @Override
    public void addStyleClass(String css_class) {
        this.edgeShapeStyler.addStyleClass(css_class);
    }

    /**
     * This method will bind a label to node
     *
     * @param label the styled label
     */
    @Override
    public void attachStyledLabelToNode(NodeLabel label) {
        this.edgeLabel = label;
        label.xProperty().bind(startXProperty().add(endXProperty()).divide(2).subtract(Bindings.divide(label.widthProperty(), 2)));
        label.yProperty().bind(startYProperty().add(endYProperty()).divide(2).add(Bindings.divide(label.heightProperty(), 1.5)));
    }

    /**
     * @return returns the label attached to a node
     */
    @Override
    public NodeLabel getAttachedLabelToNode() {
        return (NodeLabel) this.edgeLabel;
    }
}
