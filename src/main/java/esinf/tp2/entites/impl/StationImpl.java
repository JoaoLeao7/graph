package esinf.tp2.entites.impl;

import esinf.tp2.entites.Station;

import java.util.HashSet;
import java.util.Set;

/**
 * This is a simple implementation of Station interface.
 * @see esinf.tp2.entites.Station
 */
public class StationImpl implements Station
{
    private String name;
    private Set<String> lines;
    private double latitude;
    private double longitude;

    /**
     * Constructs a Station
     * @param name The name of the Station.
     */
    public StationImpl(String name)
    {
        this.name = name;
        this.lines = new HashSet<>();
        this.latitude = 0.0;
        this.longitude = 0.0;
    }
    /**
     * Constructs a Station.
     * @param name The name of the Station.
     * @param latitude The latitude of the Station.
     * @param longitude The longitude of the Station.
     */
    public StationImpl(String name, double latitude, double longitude)
    {
        this.latitude = latitude;
        this.lines = new HashSet<>();
        this.longitude = longitude;
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Set<String> getLine()
    {
        return lines;
    }

    @Override
    public double getLatitude()
    {
        return latitude;
    }

    @Override
    public double getLongitude()
    {
        return longitude;
    }

    @Override
    public boolean addLine(String line)
    {
        return lines.add(line);
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }

        if(!(obj instanceof StationImpl))
        {
            return false;
        }

        StationImpl other = (StationImpl) obj;

        return this.name.equals(other.name);
    }

    @Override
    public String toString()
    {
        return String.format("Station: %s; %.1f; %.1f; %s", name, latitude, longitude, lines.toString());
    }
}
