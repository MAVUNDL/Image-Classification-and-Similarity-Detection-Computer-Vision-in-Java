package DataStructures.interfaces;

/**
 * This interface defines an Edge that connects two Vertexes
 * @param <E> the type parameter of weight for the edge
 * @param <V> the type parameter of the vertexes connected by the edge
 */
public interface Edge<V, E> {
    /**
     * @return This method returns the weight of the edge
     */
    E element();

    /**
     * @return returns an array of length 2 containing the start and end vertex connected by the edge
     */
    Vertex<V, E>[] vertexes();

    /**
     * @return returns the weight of the edge
     */
    E getWeight();

    /**
     * This method sets the weight for the edge
     * @param weight value for the weight
     */
    void setWeight(E weight);

    /**
     * @return returns the vertex at the start of the edge
     */
    Vertex<V, E> getStartVertex();

    /**
     * This method sets the vertex to be at the start of the edge
     * @param startVertex the vertex at the start of the vertex
     */
    void setStartVertex(Vertex<V, E> startVertex);

    /**
     * @return returns the vertex at the end of the edge
     */
    Vertex<V, E> getEndVertex();

    /**
     * This method sets the vertex to be at the end of the edge
     * @param endVertex the vertex to be at the end of the edge
     */
    void setEndVertex(Vertex<V, E> endVertex);

    /**
     * This method checks if the given vertex exists on the edge
     * @param vertex the given vertex
     * @return returns true if it belongs to the edge else false
     */
    boolean contains(Vertex<V, E> vertex);
}
