package esinf.tp2.io;

import esinf.tp2.entites.Connection;
import esinf.tp2.entites.Station;
import esinf.tp2.entites.SubwayNetwork;
import esinf.tp2.entites.impl.ConnectionImpl;
import esinf.tp2.entites.impl.StationImpl;
import esinf.tp2.entites.impl.SubwayNetworkImpl;
import esinf.tp2.util.Graph;
import esinf.tp2.util.impl.GraphImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileImporterTest
{
    @Test
    void ensureReadAdjacencyMapWorks()
    {
        String coordinatesFile = "target/test-classes/coordinates.csv";
        String linesAndStationsFile = "target/test-classes/lines_and_stations.csv";
        String connectionsFile = "target/test-classes/connections.csv";

        SubwayNetwork expected = new SubwayNetworkImpl();
        expected.addStation(new StationImpl("A"));
        expected.addStation(new StationImpl("B"));
        expected.addStation(new StationImpl("C"));
        expected.addStation(new StationImpl("D"));

        expected.addConnection(new StationImpl("A"), new StationImpl("B"), new ConnectionImpl("2"), 3);
        expected.addConnection(new StationImpl("A"), new StationImpl("C"), new ConnectionImpl("3"), 1);
        expected.addConnection(new StationImpl("A"), new StationImpl("D"), new ConnectionImpl("4"), 1);

        SubwayNetwork result = FileImporter.readAdjacencyMap(coordinatesFile, linesAndStationsFile, connectionsFile);
        assertEquals(expected, result);
    }
}