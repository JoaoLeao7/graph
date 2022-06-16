package esinf.tp2.entites.impl;

import esinf.tp2.entites.Connection;
import esinf.tp2.entites.Route;
import esinf.tp2.entites.Station;
import esinf.tp2.entites.SubwayNetwork;
import esinf.tp2.util.Edge;
import esinf.tp2.util.Graph;
import esinf.tp2.util.impl.GraphImpl;

import java.util.*;

/**
 * This is a simple implementation of Subway Network interface.
 * This uses a {@link Graph Adjacency Map} to compute what is requested.
 * @see esinf.tp2.util.Graph
 */
public class SubwayNetworkImpl implements SubwayNetwork
{
    private GraphImpl<Station, Connection> adjacencyMap;

    /**
     * Constructs a Subway Network.
     */
    public SubwayNetworkImpl()
    {
        adjacencyMap = new GraphImpl<>(false);
    }

    @Override
    public boolean addStation(Station station)
    {
        return adjacencyMap.insertVertex(station);
    }

    @Override
    public boolean addConnection(Station source, Station destination, Connection connection, int time)
    {
        return adjacencyMap.insertEdge(source, destination, connection, time);
    }

    @Override
    public boolean isConnected(List<LinkedList<Station>> connectedComponents)
    {
        boolean[] visited = new boolean[adjacencyMap.numVertices()];

        for(Station src : adjacencyMap.vertices())
        {
            if(!visited[adjacencyMap.getKey(src)])
            {
                LinkedList<Station> component = new LinkedList<>();
                dfs(src, visited, component);
                connectedComponents.add(component);
            }
        }

        return connectedComponents.size() == 1;
    }

    /**
     * Does a Depth-First search given the start station.
     * @param station The {@link Station station} to start.
     * @param visited Array of discovered stations.
     * @param component A list to fill with the Stations connected.
     */
    private void dfs(Station station, boolean[] visited, LinkedList<Station> component)
    {
        visited[adjacencyMap.getKey(station)] = true;
        component.add(station);

        for(Station adjStation : adjacencyMap.adjVertices(station))
        {
            if(!visited[adjacencyMap.getKey(adjStation)])
            {
                dfs(adjStation, visited, component);
            }
        }
    }

    /**
     * {@inheritDoc}
     * In this case it is going to be used the Dijkstra’s Algorithm
     * and the weight as the time of the connection.
     */
    @Override
    public Route getShortestRouteInWeight(Station source, Station destination)
    {
        //If the source and or the destination are invalid return null.
        if(!validateSourceAndDestination(source, destination))
        {
            return null;
        }

        //All stations in a specific other.
        Station[] vertices = adjacencyMap.allkeyVerts();

        //If a station at that index was visited.
        boolean[] visited = new boolean[adjacencyMap.numVertices()];

        //The index to the previous node with shortest distance.
        int[] pathKeys = new int[adjacencyMap.numVertices()];

        //The shortest distance from a station to a specific one.
        int[] distances = new int[adjacencyMap.numVertices()];

        //Fill the arrays with preset values.
        for(int i = 0; i < vertices.length; i++)
        {
            pathKeys[i] = -1;
            distances[i] = Integer.MAX_VALUE;
        }

        int nodeKey = adjacencyMap.getKey(source);
        distances[nodeKey] = 0;

        while(nodeKey != -1)
        {
            visited[nodeKey] = true;

            for(Edge<Station, Connection> edge : adjacencyMap.outgoingEdges(vertices[nodeKey]))
            {
                int adajenct = adjacencyMap.getKey(edge.getVDest());

                if(!visited[adajenct] && distances[adajenct] > distances[nodeKey] + edge.getWeight())
                {
                    distances[adajenct] = distances[nodeKey] + edge.getWeight();
                    pathKeys[adajenct] = nodeKey;
                }
            }

            nodeKey = getVertexMinDistance(distances, visited);
        }

        return getRoute(source, destination, vertices, pathKeys, distances);
    }

    /**
     * {@inheritDoc}
     * In this case it is going to be used the Dijkstra’s Algorithm
     * and the weight as the number of stations.
     */
    @Override
    public Route getShortestRouteInStations(Station source, Station destination)
    {
        if(!validateSourceAndDestination(source, destination))
        {
            return null;
        }

        //All stations in a specific other.
        Station[] vertices = adjacencyMap.allkeyVerts();

        //If a station at that index was visited.
        boolean[] visited = new boolean[adjacencyMap.numVertices()];

        //The index to the previous node with shortest distance.
        int[] pathKeys = new int[adjacencyMap.numVertices()];

        //The shortest distance from a station to a specific one.
        int[] distances = new int[adjacencyMap.numVertices()];

        //The number of stations traveled.
        int[] number_stations = new int[adjacencyMap.numVertices()];

        //Fill the arrays with preset values.
        for(int i = 0; i < vertices.length; i++)
        {
            pathKeys[i] = -1;
            number_stations[i] = Integer.MAX_VALUE;
        }

        int nodeKey = adjacencyMap.getKey(source);
        number_stations[nodeKey] = 0;

        while(nodeKey != -1)
        {
            visited[nodeKey] = true;

            for(Edge<Station, Connection> edge : adjacencyMap.outgoingEdges(vertices[nodeKey]))
            {
                int adajenct = adjacencyMap.getKey(edge.getVDest());

                if(!visited[adajenct] && number_stations[adajenct] > number_stations[nodeKey] + 1)
                {
                    number_stations[adajenct] = number_stations[nodeKey] + 1;
                    distances[adajenct] = distances[nodeKey] + edge.getWeight();
                    pathKeys[adajenct] = nodeKey;
                }
            }

            nodeKey = getVertexMinDistance(number_stations, visited);
        }

        return getRoute(source, destination, vertices, pathKeys, distances);
    }

    /**
     * {@inheritDoc}
     * In this case it is going to be used the Dijkstra’s Algorithm
     * and the weight as the number of lines.
     */
    @Override
    public Route getShortestRouteInLines(Station source, Station destination)
    {
        if(!validateSourceAndDestination(source, destination))
        {
            return null;
        }

        //All stations in a specific other.
        Station[] vertices = adjacencyMap.allkeyVerts();

        //If a station at that index was visited.
        boolean[] visited = new boolean[adjacencyMap.numVertices()];

        //The index to the previous node with shortest distance.
        int[] pathKeys = new int[adjacencyMap.numVertices()];

        //The shortest distance from a station to a specific one.
        int[] distances = new int[adjacencyMap.numVertices()];

        //The number of lines traveled.
        int[] number_lines = new int[adjacencyMap.numVertices()];

        //The previous line of the station.
        String[] prev_line = new String[adjacencyMap.numVertices()];

        //Fill the arrays with preset values.
        for(int i = 0; i < vertices.length; i++)
        {
            pathKeys[i] = -1;
            number_lines[i] = Integer.MAX_VALUE;
        }

        int nodeKey = adjacencyMap.getKey(source);
        number_lines[nodeKey] = 0;
        prev_line[nodeKey] = vertices[nodeKey].getLine().iterator().next();

        while(nodeKey != -1)
        {
            visited[nodeKey] = true;

            for(Edge<Station, Connection> edge : adjacencyMap.outgoingEdges(vertices[nodeKey]))
            {
                int adajenct = adjacencyMap.getKey(edge.getVDest());

                //A variable to check if the changes line.
                int changesStation = 0;

                if(!edge.getElement().getLine().equals(prev_line[nodeKey]))
                {
                    changesStation = 1;
                }

                if(!visited[adajenct] && number_lines[adajenct] > number_lines[nodeKey] + changesStation)
                {
                    number_lines[adajenct] = number_lines[nodeKey] + changesStation;
                    prev_line[adajenct] = edge.getElement().getLine();
                    distances[adajenct] = distances[nodeKey] + edge.getWeight();
                    pathKeys[adajenct] = nodeKey;
                }
            }

            nodeKey = getVertexMinDistance(number_lines, visited);
        }

        return getRoute(source, destination, vertices, pathKeys, distances);
    }

    /**
     * {@inheritDoc}
     * In this case it is going to calculate all the possible
     * paths between the two stations and then narrow it down
     * to shortest one.
     */
    @Override
    public Route getShortestRouteInWeightGivenStations(Station source, Station destination, List<Station> stations)
    {
        if(!validateSourceAndDestination(source, destination))
        {
            return null;
        }

        if(!validateStations(stations))
        {
            return null;
        }

        if(stations.isEmpty())
        {
            return getShortestRouteInWeight(source, destination);
        }

        List<PathTime> paths = new ArrayList<>();
        allShortestPaths(source, destination, stations, paths, new PathTime());

        if(paths.isEmpty())
        {
            return null;
        }

        if(paths.size() == 1)
        {
            return getRoute(paths.get(0).getPath());
        }

        PathTime minPath = paths.get(0);
        int minTime = minPath.getTime();

        for(int i = 1; i < paths.size(); i++)
        {
            PathTime path = paths.get(i);

            if(path.getTime() < minTime)
            {
                minPath = path;
                minTime = path.getTime();
            }
        }

        return getRoute(minPath.getPath());
    }

    /**
     * Gets all the paths between {@link Station source} and {@link Station destination}
     * with the intermediary stations. This method is recursive.
     * @param source The source {@link Station station}.
     * @param destination The destination {@link Station station}.
     * @param stations The list of intermediary {@link Station stations}.
     * @param paths The list that will fill with all the paths found.
     * @param path The current path.
     */
    private void allShortestPaths(Station source, Station destination, List<Station> stations, List<PathTime> paths, PathTime path)
    {
        stations.remove(source);

        //If it ended this permutation.
        if(stations.isEmpty())
        {
            Route route = getShortestRouteInWeight(source, destination);

            if(!route.getPath().isEmpty())
            {
                addRouteToPathTime(path, route);
                paths.add(path);
            }
        }

        for(Station next : stations)
        {
            //Creates a new path for each possibility
            PathTime other = path.clone();

            //Gets the shortest path between the source and the next station.
            Route route = getShortestRouteInWeight(source, next);

            if(!route.getPath().isEmpty())
            {
                addRouteToPathTime(other, route);
                allShortestPaths(next, destination, new ArrayList<>(stations), paths, other);
            }
        }

        stations.add(source);
    }

    /**
     * Adds the route to a PathTime.
     * @param pathTime The PathTime where to add the route
     * @param route The route to be added
     */
    private void addRouteToPathTime(PathTime pathTime, Route route)
    {
        pathTime.addTime(route.getTotalTime());

        /*
         * Checks if the path as something. If it has that means what it
         * the route that is going to be added will have the first station
         * repeated so it is needed to remove one.
         */
        if(!pathTime.getPath().isEmpty())
        {
            pathTime.getPath().removeLast();
        }

        pathTime.addToPath(route.getPath());
    }

    /**
     * Gets the {@link Route route} between the two {@link Station stations}.
     * @param source The source {@link Station station}.
     * @param destination The destination {@link Station station}.
     * @param vertices All the {@link Station stations} in a specific order.
     * @param pathKeys The keys to the previous {@link Station station}.
     * @param distances The distance from the source to the {@link Station station} on that key.
     * @return A {@link Route route}.
     */
    private Route getRoute(Station source, Station destination, Station[] vertices, int[] pathKeys, int[] distances)
    {
        LinkedList<Station> path = new LinkedList<>();
        LinkedList<Integer> times = new LinkedList<>();
        LinkedHashMap<String, Set<Station>> lines = new LinkedHashMap<>();

        int keyNode = adjacencyMap.getKey(destination);

        if(pathKeys[keyNode] != -1 || vertices[keyNode] == source)
        {
            while(keyNode != -1)
            {
                path.add(0, vertices[keyNode]);
                times.add(0, distances[keyNode]);

                if(pathKeys[keyNode] != -1)
                {
                    String line = adjacencyMap.getEdge(vertices[pathKeys[keyNode]], vertices[keyNode]).getElement().getLine();

                    if(lines.get(line) == null)
                    {
                        lines.put(line, new HashSet<>());
                    }

                    lines.get(line).add(vertices[pathKeys[keyNode]]);
                    lines.get(line).add(vertices[keyNode]);
                }

                keyNode = pathKeys[keyNode];
            }
        }

        return new RouteImpl(path, times, lines);
    }

    /**
     * This method convert a path in a the form of {@link LinkedList list} of {@link Station stations}
     * to a {@link Route route}.
     * @param path The path to convert.
     * @return The {@link Route route} created.
     */
    private Route getRoute(LinkedList<Station> path)
    {
        LinkedList<Integer> times = new LinkedList<>();
        LinkedHashMap<String, Set<Station>> lines = new LinkedHashMap<>();

        //Adds the first time.
        times.add(0);

        for(int i = 1; i < path.size(); i++)
        {
            Edge<Station, Connection> edge = adjacencyMap.getEdge(path.get(i - 1), path.get(i));

            times.add(times.getLast() + edge.getWeight());

            String line = edge.getElement().getLine();

            if(lines.get(line) == null)
            {
                lines.put(line, new HashSet<>());
            }

            lines.get(line).add(path.get(i - 1));
            lines.get(line).add(path.get(i));
        }

        return new RouteImpl(path, times, lines);
    }

    /**
     * Validates if the source and destination {@Station stations}.
     * @param source The source {@link Station station}.
     * @param destination The destination {@link Station station}.
     * @return True whether it both are valid, otherwise false.
     */
    private boolean validateSourceAndDestination(Station source, Station destination)
    {
        if(source.equals(destination))
        {
            return false;
        }

        if (!adjacencyMap.validVertex(source) || !adjacencyMap.validVertex(destination))
        {
            return false;
        }

        return true;
    }

    /**
     * Checks if all the stations in the list are valid.
     * @param stations The list of Stations
     * @return True if all stations are valid, otherwise false.
     */
    private boolean validateStations(Collection<Station> stations)
    {
        for(Station station : stations)
        {
            if(!adjacencyMap.validVertex(station))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the key of the vertex with minimum distance.
     * @param distances The distance for each key.
     * @param visited If the key was already visited.
     * @return The key or -1 if not found.
     */
    private int getVertexMinDistance(int[] distances, boolean[] visited)
    {
        double min = Double.MAX_VALUE;
        int out = -1;

        for(int i = 0; i < distances.length; i++)
        {
            if(!visited[i] && distances[i] < min)
            {
                out = i;
                min = distances[i];
            }
        }

        return out;
    }

    @Override
    public int hashCode()
    {
        return adjacencyMap.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }

        if(!(obj instanceof SubwayNetworkImpl))
        {
            return false;
        }

        SubwayNetworkImpl other = (SubwayNetworkImpl) obj;

        return this.adjacencyMap.equals(other.adjacencyMap);
    }

    @Override
    public String toString()
    {
        return adjacencyMap.toString();
    }

    /**
     * This is a private class to more easily find the
     * shortest path with station in between.
     */
    private class PathTime
    {
        private LinkedList<Station> path;
        private int time;

        PathTime()
        {
            this.path = new LinkedList<>();
            this.time = 0;
        }

        PathTime(LinkedList<Station> path, int time)
        {
            this.path = path;
            this.time = time;
        }

        /**
         * @return The path that it has.
         */
        LinkedList<Station> getPath()
        {
            return path;
        }

        /**
         * @return The total time of the path.
         */
        int getTime()
        {
            return time;
        }

        /**
         * Adds time to the total time.
         * @param time Time to add
         */
        void addTime(int time)
        {
            this.time += time;
        }

        /**
         * Adds a part of the path to path.
         * @param toAdd The part to add.
         */
        void addToPath(LinkedList<Station> toAdd)
        {
            this.path.addAll(toAdd);
        }

        public PathTime clone()
        {
            return new PathTime(new LinkedList<>(path), time);
        }
    }
}
