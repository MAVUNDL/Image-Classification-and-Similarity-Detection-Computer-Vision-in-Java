package DataStructures.classes;

import DataStructures.interfaces.Edge;
import DataStructures.interfaces.Vertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GraphVertex<V, E> implements Vertex<V, E> {
    /*
        Class variables
     */
    private  V vertexElement; // the element stored by the vertex
    private final List<Edge<V, E>> edges; // a list of all the edges associated with the vertex

    /**
     * This constructor creates a vertex for the given element
     * @param vertexElement the element to be stored by the vertex
     */
    public GraphVertex(V vertexElement){
        this.vertexElement = vertexElement;
        this.edges =  new ArrayList<>();
    }

    /**
     * @return This method returns the element stored by the vertex
     */
    @Override
    public V element() {
        return this.vertexElement;
    }

    /**
     * This method adds an edge between the current vertex and the new vertex
     *
     * @param endVertex  the new vertex
     * @param edgeWeight the weight of the edge
     */
    @Override
    public void addEdge(Vertex<V, E> endVertex, E edgeWeight) {
        // create a new edge
        Edge<V, E> newEdge = new GraphEdge<>(this, endVertex, edgeWeight);
        // add the edge to the list of edges
        this.edges.add(newEdge);
    }

    /**
     * @return returns all the edges as a collection associated with the vertex
     */
    @Override
    public Collection<Edge<V, E>> getEdges() {
        return this.edges;
    }

    /**
     * This method set the element stored by the vertex
     *
     * @param element the element
     */
    @Override
    public void setElement(V element) {
        this.vertexElement = element;
    }

}
