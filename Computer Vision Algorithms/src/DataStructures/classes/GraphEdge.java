package DataStructures.classes;

import DataStructures.interfaces.Edge;
import DataStructures.interfaces.Vertex;

/**
 * This class defines an edge in the graph that connects two vertexes
 * @param <V>
 * @param <E>
 */
public class GraphEdge<V,E> implements Edge<V, E> {
    /*
        Defined class members
     */
    private E Weight;
    private Vertex<V, E> startVertex;
    private Vertex<V,E> endVertex;

    /**
     * Constructor to create an edge that connects two vertexes
     * @param start the start vertex on the edge
     * @param end the end vertex on the edge
     * @param edgeWeight the weight of the edge
     */
    public GraphEdge(Vertex<V, E> start, Vertex<V,E> end, E edgeWeight){
        this.Weight = edgeWeight;
        this.startVertex = start;
        this.endVertex = end;
    }

    /**
     * @return This method returns the weight of the edge
     */
    @Override
    public E element() {
        return this.getWeight();
    }

    /**
     * @return returns an array of length 2 containing the start and end vertex connected by the edge
     */
    @Override
    public Vertex<V, E>[] vertexes() {
        assert this.startVertex != null && this.endVertex != null;
        Object[] vertexes = {this.startVertex, this.endVertex};
        return (Vertex<V, E>[]) vertexes;
    }

    /**
     * @return returns the weight of the edge
     */
    @Override
    public E getWeight() {
        return Weight;
    }

    /**
     * This method sets the weight for the edge
     * @param weight value for the weight
     */
    @Override
    public void setWeight(E weight) {
        Weight = weight;
    }

    /**
     * @return returns the vertex at the start of the edge
     */
    @Override
    public Vertex<V, E> getStartVertex() {
        return startVertex;
    }

    /**
     * This method sets the vertex to be at the start of the edge
     * @param startVertex the vertex at the start of the vertex
     */
    @Override
    public void setStartVertex(Vertex<V, E> startVertex) {
        this.startVertex = startVertex;
    }

    /**
     * @return returns the vertex at the end of the edge
     */
    @Override
    public Vertex<V, E> getEndVertex() {
        return endVertex;
    }

    /**
     * This method sets the vertex to be at the end of the edge
     * @param endVertex the vertex to be at the end of the edge
     */
    @Override
    public void setEndVertex(Vertex<V, E> endVertex) {
        this.endVertex = endVertex;
    }

    /**
     * This method checks if the given vertex exists on the edge
     *
     * @param vertex the given vertex
     * @return returns true if it belongs to the edge else false
     */
    @Override
    public boolean contains(Vertex<V, E> vertex) {
        return (this.startVertex.equals(vertex) || this.endVertex.equals(vertex));
    }
}
