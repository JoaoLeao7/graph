package esinf.tp2.entites;

import java.util.LinkedList;
import java.util.List;

/**
 * This interface represents a Subway Network.
 */
public interface SubwayNetwork
{
    /**
     * Adds a station to the {@link SubwayNetwork subway network}.
     * @param station The {@link Station station} to add.
     */
    boolean addStation(Station station);

    /**
     * Adds a connection between {@link Station stations}.
     * @param source The source {@link Station station}.
     * @param destination The destination {@link Station station}.
     * @param connection The {@link Connection connection} between the {@link Station stations}.
     * @param time The time it takes to travel from one {@link Station station} to another.
     */
    boolean addConnection(Station source, Station destination, Connection connection, int time);

    /**
     * Checks if the {@link SubwayNetwork subway network} is connected.
     * @param connectedComponents A list that will fill with the connected components
     * @return true if it is connected, otherwise false.
     */
    boolean isConnected(List<LinkedList<Station>> connectedComponents);

    /**
     * Calculates the shortest path between the source and destination {@link Station stations}.
     * @param source The source {@link Station station}.
     * @param destination The destination {@link Station station}.
     * @return The shortest path in a instance of Route, or null whether at
     *         least one of the Stations were not found or there is not a
     *         path between them.
     * @see esinf.tp2.entites.Route
     */
    Route getShortestRouteInWeight(Station source, Station destination);

    /**
     * Calculates the shortest path between the source and destination {@link Station stations}
     * in term of the count of stations.
     * @param source The source {@link Station station}.
     * @param destination The destination {@link Station station}.
     * @return The shortest path in a instance of Route, or null whether at
     *         least one of the Stations were not found or there is not a
     *         path between them.
     * @see esinf.tp2.entites.Route
     */
    Route getShortestRouteInStations(Station source, Station destination);

    /**
     * Calculates the shortest path between the source and destination {@link Station stations}
     * in term of the count of lines.
     * @param source The source {@link Station station}.
     * @param destination The destination {@link Station station}.
     * @return The shortest path in a instance of Route, or null whether at
     *         least one of the Stations were not found or there is not a
     *         path between them.
     * @see esinf.tp2.entites.Route
     */
    Route getShortestRouteInLines(Station source, Station destination);

    /**
     * Calculates the shortest path between the source and destination {@link Station stations}.
     * @param source The source {@link Station station}.
     * @param destination The destination {@link Station station}.
     * @param stations The intermediary {@link Station stations}.
     * @return The shortest path in a instance of Route, or null whether at
     *         least one of the Stations were not found or there is not a
     *         path between them containing all intermediary stations.
     * @see esinf.tp2.entites.Route
     */
    Route getShortestRouteInWeightGivenStations(Station source, Station destination, List<Station> stations);
}
