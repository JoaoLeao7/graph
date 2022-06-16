package esinf.tp2.entites;

import java.util.LinkedList;
import java.util.Set;

/**
 * This interface represents a Route.
 */
public interface Route
{
    /**
     * @return A list of {@link Station stations} the path of the route in order.
     */
    LinkedList<Station> getPath();

    /**
     * Returns a list of {@link Station stations} of a line.
     * @param line The line to look for.
     * @return The list of {@link Station stations}.
     */
    Set<Station> getStationsInLine(String line);

    /**
     * Returns a time given the station and the time of start.
     * @param station The station to calculate the time.
     * @param offset The time of the start of the route.
     * @return The time at a station or -1 if the station isn't found.
     */
    int getTimeAtStation(Station station, int offset);

    /**
     * @return The set of lines the route went through.
     */
    Set<String> getLines();

    /**
     * @return The total time of this route.
     */
    int getTotalTime();
}
