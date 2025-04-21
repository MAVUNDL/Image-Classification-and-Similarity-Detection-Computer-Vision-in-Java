package DataStructures.classes;

import DataStructures.interfaces.Edge;
import DataStructures.interfaces.Graph;
import DataStructures.interfaces.Vertex;
import javafx.scene.image.Image;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphDS<V, E> implements Graph<V, E> {

    /*
        clas variables
     */
    private Map<String, Vertex<V, E>> graphVertexes;
    private Map<String, Edge<V, E>> graphEdges;

    /**
     * @return returns the number of vertexes in the graph
     */
    @Override
    public int numberOfVertexes() {
        return this.graphVertexes.size();
    }

    /**
     * @return returns the number of edges in the graph
     */
    @Override
    public int numberOfEdges() {
        return this.graphEdges.size();
    }

    /**
     * @return returns a collection of all the vertexes in the graph
     */
    @Override
    public ArrayList<Vertex<V, E>> vertexes() {
        return new ArrayList<>(this.graphVertexes.values());
    }

    /**
     * @return returns a collection of all the edges in the graph
     */
    @Override
    public ArrayList<Edge<V, E>> edges() {
        return new ArrayList<>(this.graphEdges.values());
    }

    /**
     * This method creates a vertex for an element and adds it into the graph
     *
     * @param element the element to be added on the graph
     * @return returns the vertex added on the graph
     */
    @Override
    public Vertex<V, E> addVertex(V element) throws Exception {
        String key = getVertexKey(element); // get key
        if(existingVertex(key)){ // check if vertex with this key exits on the map
            throw new Exception("There's already a vertex containing this element");
        }
        Vertex<V, E> vertex = new GraphVertex<>(element); // create vertex
        this.graphVertexes.put(key, vertex); // add it to the map
        return vertex; // return the newly added vertex
    }

    /**
     * This method adds an edge between two elements
     *
     * @param startElement the start element on the edge
     * @param endElement   the end element on the edge
     * @param weight      the weight of the node
     * @return returns the edge between the two vertexes
     */
    @Override
    public Edge<V, E> addEdge(V startElement, V endElement, E weight) throws Exception {
        // get edge key
        String edgeKey = getEdgeKey(startElement, endElement);
        if(existingEdge(edgeKey)){
            throw new Exception("There's already an edge between these two elements");
        }
        // create vertexes
        Vertex<V, E> startVertex = new GraphVertex<>(startElement);
        Vertex<V, E> endVertex = new GraphVertex<>(endElement);
        // add edge between the vertexes
        Edge<V, E> edge = new GraphEdge<>(startVertex, endVertex, weight);
        // store the edge on the map
        this.graphEdges.put(edgeKey, edge);
        return edge; // return the newly added edge
    }

    /**
     * This removes a vertex from a graph
     *
     * @param vertex the element to be removed on the graph
     * @return returns the vertex removed on the graph
     */
    @Override
    public Vertex<V, E> removeVertex(Vertex<V, E> vertex) throws Exception {
        // check if it belongs to the graph
        if(!validateVertex(vertex)){
            throw new Exception("This vertex does not belong to the graph");
        }
        // iterate through all the edges  associated with this vertex
        for(Edge<V, E> edge: vertex.getEdges()){
            // remove those edges from the graph
            this.graphEdges.remove(getEdgeKey(edge.getStartVertex().element(), edge.getEndVertex().element()));
        }
        // then now remove the vertex from the graph
        this.graphVertexes.remove(getVertexKey(vertex.element()));
        // return the removed vertex
        return vertex;
    }

    /**
     * This method removes an edge between two vertexes on the graph
     *
     * @param edge the edge to be removed
     * @return returns the edge removed
     */
    @Override
    public Edge<V, E> removeEdge(Edge<V, E> edge) throws Exception {
        // check if it belongs to the graph
        if(!validateEdge(edge)){
            throw new Exception("This edge does not belong to the graph");
        }
        // remove the edge from the graph
        this.graphEdges.remove(getEdgeKey(edge.getStartVertex().element(), edge.getEndVertex().element()));
        // return removed edge
        return edge;
    }

    /**
     * This method check is there's an edge between the two vertexes
     *
     * @param startVertex the start vertex
     * @param endVertex   the end vertex
     * @return returns true if there is else false
     */
    @Override
    public Boolean adjacent(Vertex<V, E> startVertex, Vertex<V, E> endVertex) throws Exception {
        // first check if these vertexes belong to the graph
        if(!validateVertex(startVertex) || !validateVertex(endVertex)){
            throw new Exception("One of the vertexes don't belong to the graph");
        }
        // iterate though the graph and check if there exists an edge between these two vertexes and return true if so
        for(Edge<V,E> edge : this.graphEdges.values()){
            if(edge.contains(startVertex) && edge.contains(endVertex)) return true;
        }
        // return false it there's no edge on the graph that connects these vertexes
        return false;
    }

    /**
     * this method return the end vertex on an edge given the start vertex
     *
     * @param vertex the  vertex
     * @param edge   the edge that connects the two edges
     * @return returns the vertex on the end of the edge
     */
    @Override
    public Vertex<V, E> oppositeVertex(Vertex<V, E> vertex, Edge<V, E> edge) throws Exception {
        // create vertex
        Vertex<V, E> oppositeVertex = null;
        // first check if the given vertex and edge exist on the graph
        if(!validateVertex(vertex)){
            throw new Exception("This vertex does not belong to the graph");
        }

        if(!validateEdge(edge)){
            throw new Exception("This edge does not belong to the graph");
        }

        // now check if the given vertex is on the edge
        if(!edge.contains(vertex)){
            throw new Exception("This vertex does not belong to the given edge");
        } else{
            // check if it's the start vertex of end vertex
            if(edge.getStartVertex().equals(vertex)){
                oppositeVertex = edge.getEndVertex();
            }else {
                oppositeVertex = vertex;
            }
        }
        // return the opposite vertex
        return oppositeVertex;
    }

    /**
     * This method replaces the element of a given vertex with a new element
     *
     * @param currentVertex vertex to replace its element
     * @param element     new element to store
     * @return returns the previous element
     */
    @Override
    public V replaceVertex(Vertex<V, E> currentVertex, V element) throws Exception {
        // check if the given vertex exists on the graph
        if(!validateVertex(currentVertex)){
            throw  new Exception("This vertex does not belong to the graph");
        }
        // create vertex
        Vertex<V, E> vertex = new GraphVertex<>(element);
        // check if the vertex already exits on the graph or not
        if(existingVertex(getVertexKey(vertex.element()))){
            throw new Exception("There already exits a vertex with this element");
        }
        // get old element and replace with new
        V oldElement = currentVertex.element();
        currentVertex.setElement(element);
        // return the old element
        return oldElement;
    }

    /**
     * This method replaces the weight on an edge between two vertexes
     *
     * @param edge      the given edge
     * @param newWeight the new weight
     * @return returns the previous weight
     */
    @Override
    public E replaceEdge(Edge<V, E> edge, E newWeight) throws Exception {
        // check if the edge exist of the graph
        if(!validateEdge(edge)){
            throw new Exception("This edge does not belong to the graph");
        }
        // get old weight and replace with new weight
        E oldWeight = edge.getWeight();
        edge.setWeight(newWeight);
        // return the old weight
        return oldWeight;
    }

    /**
     * This method checks if the given vertex belong to the graph, is so return a list of all edges that are associated with the vertex
     *
     * @param vertex vertex at hand
     * @return returns a list of all edges linked to this vertex
     */
    @Override
    public List<Edge<V, E>> getEdgesAssociatedWithVertex(Vertex<V, E> vertex) throws Exception {
        List<Edge<V, E>> associatedEdges = new ArrayList<>();
        if(validateVertex(vertex)){
            for(Edge<V,E> edge: this.graphEdges.values()){
                if(edge.contains(vertex)){
                    associatedEdges.add(edge);
                }
            }
        }
        return associatedEdges;
    }

    /**
     * This method validates if the vertex exits on the graph using its key
     * @param key the key for the vertex
     * @return returns true if the vertex exist on the graph else false
     */
    private boolean existingVertex(String key){
        return this.graphVertexes.containsKey(key);
    }

    /**
     * This method validates if the edge exists on the graph using its key
     * @param key the key for the edge
     * @return returns true if the edge exists on the graph else false
     */
    private boolean existingEdge(String key){
        return this.graphEdges.containsKey(key);
    }

    /**
     * This method creates a key (Name of image)  for the given element
     * @param element the element (image)
     * @return returns the keys
     */
    private String getVertexKey(V element){
        Image ImageNode = (Image) element; // cast to Image object to use image class properties
        return Paths.get(ImageNode.getUrl()).getFileName().toString(); // get the name of the image and return it as a key
    }

    /**
     * This method create a key (Image 1 Name | Image 2 Name) of an edge between two elements
     * @param startElement the start element (Image) on the edge
     * @param endElement the end element (Image) of on the edge
     * @return returns the key
     */
    private String getEdgeKey(V startElement, V endElement){
        // cast to image object to use image class properties
        Image startImageNode = (Image) startElement;
        Image endImageNode = (Image) endElement;
        // get the name of images and create a key
        return Paths.get(startImageNode.getUrl()).getFileName().toString() + " | " +  Paths.get(endImageNode.getUrl()).getFileName().toString();
    }

    /**
     * This method validates if the given vertex exists on the graph
     * @param vertex the given vertex
     * @return returns true if it exists else false
     * @throws Exception throws an exception if the given parameter is not a vertex object
     */
    private boolean validateVertex(Vertex<V, E> vertex) throws Exception {
        if(vertex == null) throw new Exception("This vertex is null");
        GraphVertex<V,E> graphVertex =  null; // create graph vertex
        try{
            graphVertex = (GraphVertex<V, E>) vertex; // cast to graph vertex
        } catch (ClassCastException e){
            throw new Exception("This object is not a vertex");
        }
        // check if this vertex belongs to the graph
        return this.graphVertexes.containsKey(getVertexKey(vertex.element()));
    }

    /**
     * This method validates if the given edge exists on the graph
     * @param edge the given edge
     * @return returns true if it exists else false
     * @throws Exception throws an exception if the given parameter is not an edge object
     */
    private boolean validateEdge(Edge<V, E> edge) throws Exception {
        if(edge == null) throw  new Exception("This edge is null");
        GraphEdge<V, E> graphEdge = null;// create graph edge
        try{
            graphEdge = (GraphEdge<V, E>) edge; // cast to graph edge
        } catch (ClassCastException e){
            throw  new Exception("This object is not an edge");
        }
        // check if this edge belongs to the graph
        return this.graphEdges.containsKey(getEdgeKey(((GraphEdge<V, E>) edge).getStartVertex().element(), ((GraphEdge<V, E>) edge).getEndVertex().element()));
    }
}
