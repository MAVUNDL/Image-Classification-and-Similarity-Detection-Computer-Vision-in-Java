package DataStructures.interfaces;

import java.util.Collection;

public interface Vertex<V, E> {
    /**
     * @return This method returns the element stored by the vertex
     */
    V element();

    /**
     * This method adds an edge between the current vertex and the new vertex
     * @param endVertex the new vertex
     * @param edgeWeight the weight of the edge
     */
    void addEdge(Vertex<V, E> endVertex, E edgeWeight);

    /**
     @return returns all the edges as a collection associated with the vertex
     */
    Collection<Edge<V, E>> getEdges();

    /**
     * This method set the element stored by the vertex
     * @param element the element
     */
    void  setElement(V element);

}
