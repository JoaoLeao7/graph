package esinf.tp2.entites;

import java.util.Set;

/**
 * This interface represents a Station.
 */
public interface Station
{
    /**
     * @return The name of the {@link Station station}.
     */
    String getName();

    /**
     * @return A set of line ids which the {@link Station station} belongs to.
     */
    Set<String> getLine();

    /**
     * @return The latitude of the {@link Station station}.
     */
    double getLatitude();

    /**
     * @return The longitude of the {@link Station station}.
     */
    double getLongitude();

    /**
     * Adds a line to the {@link Station station}.
     * @param line The line to add.
     * @return Whether it was successful.
     */
    boolean addLine(String line);
}
