package esinf.tp2.entites.impl;

import esinf.tp2.entites.Route;
import esinf.tp2.entites.Station;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This is a simple implementation of Route interface.
 */
public class RouteImpl implements Route
{
    /**
     * The path of this route.
     */
    private LinkedList<Station> path;

    /**
     * The times from one {@link Station station} to another.
     * <p>
     * Example: The time it takes to go from Station of index 0 to 1 is
     * at index 0 of this list.
     */
    private List<Integer> times;

    /**
     * The map with a list of {@link Station stations} for each line.
     */
    private LinkedHashMap<String, Set<Station>> lines;

    /**
     * Constructs a Route.
     * @param path The path of this route.
     * @param times The times from one {@link Station station} to another.
     * @param lines The map with a list of {@link Station stations} for each line.
     */
    public RouteImpl(LinkedList<Station> path, List<Integer> times, LinkedHashMap<String, Set<Station>> lines)
    {
        this.path = path;
        this.times = times;
        this.lines = lines;
    }

    @Override
    public LinkedList<Station> getPath()
    {
        return path;
    }

    @Override
    public Set<Station> getStationsInLine(String line)
    {
        return lines.get(line);
    }

    @Override
    public int getTimeAtStation(Station station, int offset)
    {
        int index = path.indexOf(station);

        //If the station wasn't found in the path.
        if(index == -1)
        {
            return -1;
        }

        return offset + times.get(index);
    }

    @Override
    public Set<String> getLines()
    {
        return lines.keySet();
    }

    @Override
    public int getTotalTime()
    {
        return times.get(path.size() - 1);
    }
}
