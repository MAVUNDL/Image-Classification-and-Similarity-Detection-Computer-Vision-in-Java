package graphical.interfaces;

import DataStructures.interfaces.Edge;
import DataStructures.interfaces.Graph;
import DataStructures.interfaces.Vertex;
import javafx.geometry.Bounds;

import java.util.ArrayList;
import java.util.List;

public interface GraphGUI<V, E>{
    /**
     * This method sets the position of th vertex on the graph
     * @param vertex vertex to be positioned
     * @param x x-coordinate
     * @param y y-coordinate
     */
    void setVertexPosition(Vertex<V, E> vertex, double x, double y);

    /**
     * This method retrieves the x and y position of a vertex on the graph
     * @param vertex vertex to be used to retrieve positions
     * @return returns an array with x and y position
     */
    double[] getVertexPosition(Vertex<V, E> vertex);

    /**
     * @return returns a list of vertexes on the graph
     */
    List<Vertex<V, E>> listOfGraphVertexes();

    /**
     * @return returns a list of vertexes on the graph
     */
    List<Edge<V, E>> listOfGraphEdges();

    /**
     * @return returns a list of underlying graph edges that are not drawn on the gui
     */
    List<Edge<V, E>> EdgesThatAreNotDrawn();

    /**
     * @return returns a list of underlying graph vertexes that are not plotted on the gui
     */
    List<Vertex<V, E>> VertexesThatAreNotPlotted();

    /**
     * @return returns a list of edges drawn on the gui but no longer exist on the underlying graph
     */
    List<Edge<V, E>> removedEdges();

    /**
     * @return  returns a list of vertexes plotted on the gui but no longer exist on the underlying graph
     */
    List<Vertex<V, E>> removedVertexes();

    /**
     * This method check if the two vertexes are adjacent to each other
     * @param v first vertex
     * @param u second vertex
     * @return returns true of they are adjacent else false
     */
    boolean areTheseAdjacent(VertexGUI<V, E> v, VertexGUI<V, E> u);

    /**
     * @return returns the bounding box where all the vertexes are plotted
     */
    Bounds getBoundsForPlotArea();

    /**
     * This method updates the graphs nodes, shapes and labels
     */
    void updateGraphNodes();

    /**
     * This method removes a given vertex node from the gui
     * @param vertex the vertex in the gui
     */
    void removeVertexOnGUI(VertexGUI<V, E> vertex);

    /**
     * This method removes an edge node from the gui
     * @param edge the edge on the gui
     */
    void removeEdgeOnGUI(EdgeGUI<V, E> edge);

    /**
     * This method removes the vertexes and edges on the underlying graph that we removed on the gui
     */
    void removeGraphNodes();

    /**
     * This method adds the vertexes and edges that were not plotted to the gui
     */
    void addGraphNodesOnGUI();

    /**
     * This method adds the edge node on the gui -  uses the underlying graph edge to get its weight amd creates a label with it and attaches it to the gui edge
     * @param edgeGUI  edge node instance
     * @param edge underlying graph edge
     */
    void addEdgeToGUI(EdgeGUI<V, E> edgeGUI, Edge<V, E> edge);

    /**
     * This method plots the vertex node on the gui
     * @param vertex gui vertex node
     */
    void addVertexToGUI(VertexGUI<V, E> vertex);

    /**
     * This method create an edge Node for the two vertex nodes
     * @param startVertex start vertex node
     * @param endVertex end vertex node
     * @param edgeBetween the underlying edge between the two vertexes
     * @return return a GUI edge node connecting the two vertex nodes
     */
    EdgeGUI<V, E> createGUIEdge(VertexGUI<V, E> startVertex, VertexGUI<V, E> endVertex, Edge<V, E> edgeBetween);

    /**
     * This method creates a vertex node for the underling vertex and plots it on the graph using the given positions
     * @param vertex underlying vertex
     * @param x x-coordinate
     * @param y y-coordinate
     * @return returns the created node plotted on the gui
     */
    VertexGUI<V, E> createGUIVertex(Vertex<V, E> vertex, double x, double y);

    /**
     * This method creates the vertexes and edges between the vertexes during the initial phase
     */
    void initialPhase();

    /**
     * This method updates and refreshes the gui in real time
     */
    void updateAndRefreshGUI();

    /**
     * This method updates the graph
     */
    void updateGraph();

    /**
     * @return returns a list of all the edge nodes in the gui
     */
    List<EdgeGUI<V,E>> getEdgeNodes();

    /**
     * @return returns a list of all the vertex nodes in the gui
     */
    List<VertexGUI<V, E>> getVertexNodes();

    /**
     * @return returns the underlying graph
     */
    Graph<V, E> getGraph();


}
