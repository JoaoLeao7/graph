package esinf.tp2.io;

import esinf.tp2.entites.Station;
import esinf.tp2.entites.SubwayNetwork;
import esinf.tp2.entites.impl.ConnectionImpl;
import esinf.tp2.entites.impl.StationImpl;
import esinf.tp2.entites.impl.SubwayNetworkImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileImporter
{
    /**
     * The regex used for CSV(Comma Separated Values) files.
     */
    private static final String COMMA_SEPARATED_VALUES_REGEX = ";";

    /**
     * Reads all the files to build a {@link SubwayNetwork Subway Network}.
     * @return A {@link SubwayNetwork subway network} with all the information from the files.
     */
    public static SubwayNetwork readAdjacencyMap(String coordinatesFile, String linesAndStationsFile, String connectionsFile)
    {
        SubwayNetwork subwayNetwork = new SubwayNetworkImpl();

        Set<Station> stations = readStations(coordinatesFile);
        readLinesAndStations(linesAndStationsFile, stations);

        for(Station station : stations)
        {
            subwayNetwork.addStation(station);
        }

        readConnections(connectionsFile, subwayNetwork);

        return subwayNetwork;
    }

    /**
     * Reads the file coordinates containing the {@link Station stations}.
     * @param file The file location.
     * @return A set of {@link Station stations}.
     */
    private static Set<Station> readStations(String file)
    {
        try
        {
            Scanner input = new Scanner(new File(file));
            Set<Station> stations = new HashSet<>();

            while(input.hasNext())
            {
                //The params[0] should be the station name.
                //The params[1] should be the latitude.
                //The params[2] should be the longitude.
                String[] params = input.nextLine().split(COMMA_SEPARATED_VALUES_REGEX);
                stations.add(new StationImpl(params[0], Double.parseDouble(params[1]), Double.parseDouble(params[2])));
            }

            input.close();
            return stations;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found while reading the stations.");
        }

        return null;
    }

    /**
     * Reads the file of lines and {@link Station station} names.
     * and adds the lines to the corresponding {@link Station station}.
     * @param file The file location.
     * @param stations The set containing all {@link Station stations}.
     */
    private static void readLinesAndStations(String file, Set<Station> stations)
    {
        try
        {
            Scanner input = new Scanner(new File(file));

            while(input.hasNext())
            {
                //The params[0] should be the line.
                //The params[1] should be the station name.
                String[] params = input.nextLine().split(COMMA_SEPARATED_VALUES_REGEX);
                addLineToStation(stations, params[1], params[0]);
            }

            input.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found while reading Lines and Stations.");
        }
    }

    /**
     * Adds a line to a {@link Station station}.
     * @param stations The set of stations.
     * @param stationId The name of the station.
     * @param lineId The line id.
     * @return Whether it was successful adding it.
     */
    private static boolean addLineToStation(Set<Station> stations, String stationId, String lineId)
    {
        for(Station station : stations)
        {
            if(station.getName().equals(stationId))
            {
                return station.addLine(lineId);
            }
        }

        return false;
    }

    /**
     * Reads the file of connections and adds them to the map.
     * @param file The file location.
     * @param subwayNetwork The {@link SubwayNetwork subway network}.
     */
    private static void readConnections(String file, SubwayNetwork subwayNetwork)
    {
        try
        {
            Scanner input = new Scanner(new File(file));

            while(input.hasNext())
            {
                //The params[0] should be the line.
                //The params[1] should be the station source name.
                //The params[2] should be the station destination name.
                //The params[3] should be the time it takes to travel between stations.
                String[] params = input.nextLine().split(COMMA_SEPARATED_VALUES_REGEX);
                Station source = new StationImpl(params[1]);
                Station destination = new StationImpl(params[2]);
                int time = Integer.parseInt(params[3]);

                subwayNetwork.addConnection(source, destination, new ConnectionImpl(params[0]), time);
            }

            input.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found while reading the connections.");
        }
    }
}
