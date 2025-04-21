package helpers;

import DataStructures.interfaces.Vertex;

import java.util.Objects;

/**
 * This class defines a tuple data structure like in python and other language to store two elements in a pair
 * @param <V> generic parameter
 * @param <E> generic parameter
 */
public class TupleOfVertexes<V, E>{
    private final Vertex<V, E> startVertex;
    private final  Vertex<V, E> endVertex;

    public TupleOfVertexes(Vertex<V, E> startVertex, Vertex<V, E> endVertex) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    }

    public Vertex<V,E> getStartVertex(){
        return this.startVertex;
    }

    public Vertex<V, E> getEndVertex(){
        return this.endVertex;
    }
}
