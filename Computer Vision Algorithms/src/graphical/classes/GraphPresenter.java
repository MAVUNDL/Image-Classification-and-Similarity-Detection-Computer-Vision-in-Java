package graphical.classes;

import DataStructures.interfaces.Edge;
import DataStructures.interfaces.Graph;
import DataStructures.interfaces.Vertex;
import graphical.interfaces.EdgeGUI;
import graphical.interfaces.GraphGUI;
import graphical.interfaces.VertexGUI;
import helpers.TupleOfVertexes;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.*;

public class GraphPresenter<V,E> extends Pane implements GraphGUI<V,E> {
    /*
        Class properties
     */
    private final  Graph<V,E> graphDataStructure;
    private final Map<Vertex<V, E>, VertexGUI<V, E>> vertexNodes;
    private final Map<Edge<V, E>, EdgeGUI<V, E>> edgeNodes;
    private final Map<Edge<V, E>, TupleOfVertexes<V, E>> graphConnections;

    public GraphPresenter(Graph<V, E> graph){
        this.graphDataStructure = graph;
        this.vertexNodes = new HashMap<>();
        this.edgeNodes = new HashMap<>();
        this.graphConnections = new HashMap<>();
    }

    /**
     * This method sets the position of th vertex on the graph
     *
     * @param vertex vertex to be positioned
     * @param x      x-coordinate
     * @param y      y-coordinate
     */
    @Override
    public void setVertexPosition(Vertex<V, E> vertex, double x, double y) {
        VertexGUI<V, E> vertexNode = vertexNodes.get(vertex);
        if(vertex != null){
            vertexNode.setVertexPosition(x, y);
        }
    }

    /**
     * This method retrieves the x and y position of a vertex on the graph
     *
     * @param vertex vertex to be used to retrieve positions
     * @return returns an array with x and y position
     */
    @Override
    public double[] getVertexPosition(Vertex<V, E> vertex) {
        VertexGUI<V, E> vertexNode = vertexNodes.get(vertex);
        double[] positions = new double[2];
        if(vertex != null){
            positions = new double[]{vertexNode.getXCenterPosition(), vertexNode.getYCenterPosition()};
        }
        return positions;
    }

    /**
     * @return returns a list of vertexes on the graph
     */
    @Override
    public List<Vertex<V, E>> listOfGraphVertexes() {
        return this.graphDataStructure.vertexes();
    }

    /**
     * @return returns a list of vertexes on the graph
     */
    @Override
    public List<Edge<V, E>> listOfGraphEdges() {
        return this.graphDataStructure.edges();
    }

    /**
     * @return returns a list of underlying graph edges that are not drawn on the gui
     */
    @Override
    public List<Edge<V, E>> EdgesThatAreNotDrawn() {
        List<Edge<V, E>> edges = new ArrayList<>();
        for(Edge<V,E> edge: this.graphDataStructure.edges()){
            if(!this.edgeNodes.containsKey(edge)){
                edges.add(edge);
            }
        }
        return edges;
    }

    /**
     * @return returns a list of underlying graph vertexes that are not plotted on the gui
     */
    @Override
    public List<Vertex<V, E>> VertexesThatAreNotPlotted() {
        List<Vertex<V,E>> vertexes = new ArrayList<>();
        for(Vertex<V, E> vertex: this.graphDataStructure.vertexes()){
            if(!this.vertexNodes.containsKey(vertex)){
                vertexes.add(vertex);
            }
        }
        return vertexes;
    }

    /**
     * @return returns a list of edges drawn on the gui but no longer exist on the underlying graph
     */
    @Override
    public List<Edge<V, E>> removedEdges() {
        List<Edge<V, E>> edges = new ArrayList<>();
        // iterate through all the edge nodes in graph
        for(EdgeGUI<V, E> edgeGUI: this.edgeNodes.values()){
            // check if the underlying edge is on the underlying graph, if no add it to the list of removed edges from the underlying graph
            if(!this.graphDataStructure.edges().contains(edgeGUI.getUnderlyingEdge())){
                edges.add((Edge<V, E>) edgeGUI.getUnderlyingEdge());
            }
        }
        return edges;
    }

    /**
     * @return returns a list of vertexes plotted on the gui but no longer exist on the underlying graph
     */
    @Override
    public List<Vertex<V, E>> removedVertexes() {
        List<Vertex<V, E>> vertexes = new ArrayList<>();
        // iterate through all the vertex nodes in graph
        for(VertexGUI<V, E> vertexGUI: this.vertexNodes.values()){
            // check if the underlying vertex is on the underlying graph, if no add it to the list of removed vertexes from the underlying graph
            if(!this.graphDataStructure.vertexes().contains(vertexGUI.getUnderlyingVertex())){
                vertexes.add(vertexGUI.getUnderlyingVertex());
            }
        }
        return vertexes;
    }

    /**
     * This method check if the two vertexes are adjacent to each other
     *
     * @param v first vertex
     * @param u second vertex
     * @return returns true of they are adjacent else false
     */
    @Override
    public boolean areTheseAdjacent(VertexGUI<V, E> v, VertexGUI<V, E> u) {
        return this.graphConnections.containsValue(new TupleOfVertexes<>(v.getUnderlyingVertex(), u.getUnderlyingVertex()));
    }

    /**
     * @return returns the bounding box where all the vertexes are plotted
     */
    @Override
    public Bounds getBoundsForPlotArea() {
        double minX =  Double.MIN_VALUE, minY = Double.MIN_VALUE;
        double maxX = Double.MAX_VALUE, maxY = Double.MAX_VALUE;

        if(this.vertexNodes.isEmpty()){
            return  new BoundingBox(0, 0, getWidth(), getHeight());
        }
        // compute the min and max positions
        for(VertexGUI<V, E> vertexGUI: this.vertexNodes.values()){
            minX = Math.min(minX, vertexGUI.getXCenterPosition());
            maxX = Math.max(maxX, vertexGUI.getXCenterPosition());
            minY = Math.min(minY, vertexGUI.getYCenterPosition());
            maxY = Math.max(maxY, vertexGUI.getYCenterPosition());
        }
        return new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }

    /**
     * This method updates the graphs nodes, shapes and labels
     */
    @Override
    public void updateGraphNodes() {

    }

    /**
     * This method removes a given vertex node from the gui
     *
     * @param vertex the vertex in the gui
     */
    @Override
    public void removeVertexOnGUI(VertexGUI<V, E> vertex) {
        // remove vertex node on the gui
        this.getChildren().remove((Node) vertex);
        // now get the label attached to the node and remove it too
        if(vertex.getAttachedLabelToNode() != null){
            this.getChildren().remove(vertex.getAttachedLabelToNode());
        }
    }

    /**
     * This method removes an edge node from the gui
     *
     * @param edge the edge on the gui
     */
    @Override
    public void removeEdgeOnGUI(EdgeGUI<V, E> edge) {
        // remove edge node of the gui
        this.getChildren().remove((Node) edge);
        // now get the label attached to the node and remove it too
        if(edge.getAttachedLabelToNode() != null){
            this.getChildren().remove(edge.getAttachedLabelToNode());
        }
    }

    /**
     * This method removes the vertexes and edges on the underlying graph that we removed on the gui
     */
    @Override
    public void removeGraphNodes() {
        // get the list of all removed edges from the gui, and remove each edge from the underlying graph
        List<Edge<V, E>> removedEdgesFromGUI = removedEdges();
        for(Edge<V, E> edge : removedEdgesFromGUI){
            // get associated node
            EdgeGUI<V,E> associatedNode = this.edgeNodes.get(edge);
            // get the two vertexes associated with the edge
            TupleOfVertexes<V, E> associatedVertexes = this.graphConnections.get(edge);
            this.edgeNodes.remove(edge); // remove edge from map
            removeEdgeOnGUI(associatedNode);// remove node from gui
            // remove adjacency link between the two associated vertexes since the edge between them is removed
            VertexGUI<V, E>  one = this.vertexNodes.get(associatedVertexes.getStartVertex());
            VertexGUI<V, E> two = this.vertexNodes.get(associatedVertexes.getEndVertex());
            one.removeAdjacentVertex(two);
            two.removeAdjacentVertex(one);
            // removed connection from the map
            this.graphConnections.remove(edge);
        }
        // get the list of all removed vertexes from the gui, and remove each vertex from the underlying graph
        List<Vertex<V,E>> removedVertexesFromGUI = removedVertexes();
        for(Vertex<V,E> vertex: removedVertexesFromGUI){
            // get node associated with vertex
            VertexGUI<V, E>  associatedNode = this.vertexNodes.get(vertex);
            // remove it from the gui
            removeVertexOnGUI(associatedNode);
        }
    }

    /**
     * This method adds the vertexes and edges that were not plotted to the gui
     */
    @Override
    public void addGraphNodesOnGUI() {

    }

    /**
     * This method adds the edge node on the gui -  uses the underlying graph edge to get its weight amd creates a label with it and attaches it to the gui edge
     *
     * @param edgeGUI edge node instance
     * @param edge    underlying graph edge
     */
    @Override
    public void addEdgeToGUI(EdgeGUI<V, E> edgeGUI, Edge<V, E> edge) {

    }

    /**
     * This method plots the vertex node on the gui
     *
     * @param vertex gui vertex node
     */
    @Override
    public void addVertexToGUI(VertexGUI<V, E> vertex) {
    }

    /**
     * This method create an edge Node for the two vertex nodes
     *
     * @param startVertex start vertex node
     * @param endVertex   end vertex node
     * @param edgeBetween the underlying edge between the two vertexes
     * @return return a GUI edge node connecting the two vertex nodes
     */
    @Override
    public EdgeGUI<V, E> createGUIEdge(VertexGUI<V, E> startVertex, VertexGUI<V, E> endVertex, Edge<V, E> edgeBetween) {
        return new NodeForEdge<>(startVertex, endVertex, edgeBetween);
    }

    /**
     * This method creates a vertex node for the underling vertex and plots it on the graph using the given positions
     *
     * @param vertex underlying vertex
     * @param x      x-coordinate
     * @param y      y-coordinate
     * @return returns the created node plotted on the gui
     */
    @Override
    public VertexGUI<V, E> createGUIVertex(Vertex<V, E> vertex, double x, double y) {
        return new NodeForVertex<>(vertex, x, y, 50);
    }

    /**
     * This method creates the vertexes and edges between the vertexes during the initial phase
     */
    @Override
    public void initialPhase() {
        Random random = new Random(); // seed generator
        // for each vertex in the graph, create a node to represent the vertex at a random position
        for(Vertex<V, E> vertex: listOfGraphVertexes()){
            VertexGUI<V, E> vertexNode = createGUIVertex(vertex, random.nextDouble() * getWidth(), random.nextDouble() * getHeight());
            this.vertexNodes.put(vertex, vertexNode); // update map
        } // continue later

    }

    /**
     * This method updates and refreshes the gui in real time
     */
    @Override
    public void updateAndRefreshGUI() {

    }

    /**
     * This method updates the graph
     */
    @Override
    public void updateGraph() {

    }

    /**
     * @return returns a list of all the edge nodes in the gui
     */
    @Override
    public List<EdgeGUI<V, E>> getEdgeNodes() {
        return (List<EdgeGUI<V, E>>) this.edgeNodes.values();
    }

    /**
     * @return returns a list of all the vertex nodes in the gui
     */
    @Override
    public List<VertexGUI<V, E>> getVertexNodes() {
        return (List<VertexGUI<V, E>>) this.vertexNodes.values();
    }

    /**
     * @return returns the underlying graph
     */
    @Override
    public Graph<V, E> getGraph() {
        return this.graphDataStructure;
    }
}
