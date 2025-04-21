package DataStructures.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface defines a graph data structure
 * @param <V>
 * @param <E>
 */
public interface Graph<V, E> {
    /**
     * @return returns the number of vertexes in the graph
     */
    int numberOfVertexes();

    /**
     * @return returns the number of edges in the graph
     */
    int numberOfEdges();

    /**
     * @return returns a collection of all the vertexes in the graph
     */
    ArrayList<Vertex<V, E>> vertexes();

    /**
     * @return returns a collection of all the edges in the graph
     */
    ArrayList<Edge<V, E>> edges();

    /**
     * This method adds a vertex into the graph
     * @param element the element to be added on the graph
     * @return returns the vertex added on the graph
     */
    Vertex<V, E> addVertex(V element) throws Exception;

    /**
     * This method adds an edge between two elements
     * @param startElement the start element on the edge
     * @param endElement   the end element on the edge
     * @param weight the weight of the node
     * @return returns the edge between the two vertexes
     */
    Edge<V, E> addEdge(V startElement, V endElement, E weight) throws Exception;

    /**
     * This removes a vertex from a graph
     * @param vertex the vertex to be removed on the graph
     * @return returns the vertex removed on the graph
     */
    Vertex<V, E> removeVertex(Vertex<V, E> vertex) throws Exception;

    /**
     * This method removes an edge between two vertexes on the graph
     * @param edge the edge to be removed
     * @return returns the edge removed
     */
    Edge<V, E> removeEdge(Edge<V, E> edge) throws Exception;

    /**
     * This method check is there's an edge between the two vertexes
     * @param startVertex the start vertex
     * @param endVertex the end vertex
     * @return returns true if there is else false
     */
    Boolean adjacent(Vertex<V, E> startVertex, Vertex<V, E> endVertex) throws Exception;

    /**
     * this method return the end vertex on an edge given the start vertex
     * @param vertex the vertex
     * @param edge the edge that connects the two edges
     * @return returns the vertex on the end of the edge
     */
    Vertex<V, E> oppositeVertex(Vertex<V, E> vertex, Edge<V, E> edge) throws Exception;

    /**
     * This method replaces the element of a given vertex with a new element
     * @param currentVertex vertex to replace its element
     * @param element new element to store
     * @return returns the previous element
     */
    V replaceVertex(Vertex<V, E> currentVertex, V element) throws Exception;

    /**
     * This method replaces the weight on an edge between two vertexes
     * @param edge the given edge
     * @param newWeight the new weight
     * @return returns the previous weight
     */
    E replaceEdge(Edge<V, E> edge, E newWeight) throws Exception;

    /**
     * This method checks if the given vertex belong to the graph, is so return a list of all edges that are associated with the vertex
     * @param vertex vertex at hand
     * @return returns a list of all edges linked to this vertex
     */
    List<Edge<V, E>> getEdgesAssociatedWithVertex(Vertex<V, E> vertex) throws Exception;

}
