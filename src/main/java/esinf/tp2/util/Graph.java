package esinf.tp2.util;

/**
 * @param <V> The type for the vertices.
 * @param <E> The type for the edges.
 * @author DEI-ESINF
 */
public interface Graph<V, E>
{
    /**
     * @return The number of vertices of the graph.
     */
    int numVertices();

    /**
     * @return All the vertices of the graph as an iterable collection.
     */
    Iterable<V> vertices();

    /**
     * @return The number of edges of the graph.
     */
    int numEdges();

    /**
     * @return The information of all the edges of the graph as an iterable collection.
     */
    Iterable<Edge<V, E>> edges();

    /**
     * @param vOrig Information of vertex source.
     * @param vDest Information of vertex destination.
     * @return the edge or null if vertices are not adjacent or don't exist.
     */
    Edge<V, E> getEdge(V vOrig, V vDest);

    /**
     * Returns the vertices of edge as an array of length two.
     * If the graph is directed, the first vertex is the origin, and
     * the second is the destination. If the graph is undirected, the
     * order is arbitrary.
     * @param edge
     * @return array of two vertices or null if edge doesn't exist
     */
    V[] endVertices(Edge<V, E> edge);

    /**
     * Returns the vertex that is opposite vertex on edge.
     * @param vert
     * @param edge
     * @return opposite vertex, or null if vertex or edge don't exist
     */
    V opposite(V vert, Edge<V, E> edge);

    /**
     * Returns the number of edges leaving vertex v
     * For an undirected graph, this is the same result returned by inDegree
     *
     * @param vert
     * @return number of edges leaving vertex v, -1 if vertex doesn't exist
     */
    int outDegree(V vert);

    /**
     * Returns the number of edges for which vertex v is the destination
     * For an undirected graph, this is the same result returned by outDegree
     *
     * @param vert
     * @return number of edges leaving vertex v, -1 if vertex doesn't exist
     */
    int inDegree(V vert);

    /**
     * Returns an iterable collection of edges for which vertex v is the origin
     * for an undirected graph, this is the same result returned by incomingEdges
     * @param vert
     * @return iterable collection of edges, null if vertex doesn't exist
     */
    Iterable<Edge<V, E>> outgoingEdges(V vert);

    /**
     * Returns an iterable collection of edges for which vertex v is the destination
     * For an undirected graph this is the same result as returned by incomingEdges
     * @param vert
     * @return iterable collection of edges reaching vertex, null if vertex doesn't exist
     */
    Iterable<Edge<V, E>> incomingEdges(V vert);

    /**
     * Inserts a new vertex with some specific comparable type
     * @param newVert the vertex contents
     * @return a true if insertion suceeds, false otherwise
     */
    boolean insertVertex(V newVert);

    /**
     * Adds a new edge between vertices u and v, with some
     * specific comparable type. If vertices u, v don't exist in the graph they
     * are inserted
     * @param vOrig Information of vertex source
     * @param vDest Information of vertex destination
     * @param edge Edge information
     * @param eWeight Edge weight
     * @return True if succeeds, or false if an edge already exists between the two verts.
     */
    boolean insertEdge(V vOrig, V vDest, E edge, int eWeight);


    /**
     * Removes a vertex and all its incident edges from the graph
     * @param vert Information of vertex source
     */
    boolean removeVertex(V vert);

    /**
     * Removes the edge between two vertices
     *
     * @param vOrig Information of vertex source
     * @param vDest Information of vertex destination
     */
    boolean removeEdge(V vOrig, V vDest);

}
    
 
