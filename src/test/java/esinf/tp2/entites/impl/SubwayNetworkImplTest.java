package esinf.tp2.entites.impl;

import esinf.tp2.entites.Route;
import esinf.tp2.entites.Station;
import esinf.tp2.entites.SubwayNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubwayNetworkImplTest
{
    SubwayNetworkImpl subwayNetwork;
    SubwayNetworkImpl subwayNetworkPath;

    @BeforeEach
    void setUp()
    {
        subwayNetwork = new SubwayNetworkImpl();

        subwayNetworkPath = new SubwayNetworkImpl();
        Station s1 = new StationImpl("A");
        s1.addLine("1");
        s1.addLine("2");
        Station s2 = new StationImpl("B");
        s2.addLine("3");
        s2.addLine("1");
        Station s3 = new StationImpl("C");
        s3.addLine("1");
        Station s4 = new StationImpl("D");
        s4.addLine("1");
        Station s5 = new StationImpl("E");
        s5.addLine("1");
        s5.addLine("2");
        s5.addLine("3");
        Station s6 = new StationImpl("F");
        s6.addLine("1");
        s6.addLine("2");

        subwayNetworkPath.addStation(s1);
        subwayNetworkPath.addStation(s2);
        subwayNetworkPath.addStation(s3);
        subwayNetworkPath.addStation(s4);
        subwayNetworkPath.addStation(s5);
        subwayNetworkPath.addStation(s6);

        subwayNetworkPath.addConnection(s1, s2, new ConnectionImpl("1"), 1);
        subwayNetworkPath.addConnection(s2, s5, new ConnectionImpl("3"), 30);
        subwayNetworkPath.addConnection(s2, s3, new ConnectionImpl("1"), 1);
        subwayNetworkPath.addConnection(s3, s4, new ConnectionImpl("1"), 1);
        subwayNetworkPath.addConnection(s4, s5, new ConnectionImpl("1"), 10);
        subwayNetworkPath.addConnection(s4, s6, new ConnectionImpl("2"), 2);
        subwayNetworkPath.addConnection(s5, s6, new ConnectionImpl("1"), 2);
        subwayNetworkPath.addConnection(s6, s1, new ConnectionImpl("1"), 50);
        subwayNetworkPath.addConnection(s6, s2, new ConnectionImpl("3"), 3);
    }

    @Test
    void ensureAddStationWorks()
    {
        subwayNetwork.addStation(new StationImpl("A"));

        SubwayNetworkImpl result = new SubwayNetworkImpl();
        assertNotEquals(subwayNetwork, result);

        result.addStation(new StationImpl("A"));
        assertEquals(subwayNetwork, result);
    }

    @Test
    void ensureAddConnectionWorks()
    {
        subwayNetwork.addStation(new StationImpl("A"));
        subwayNetwork.addStation(new StationImpl("B"));
        subwayNetwork.addConnection(new StationImpl("A"), new StationImpl("B"), new ConnectionImpl("1"), 2);

        SubwayNetworkImpl result = new SubwayNetworkImpl();
        assertNotEquals(subwayNetwork, result);

        result.addStation(new StationImpl("A"));
        result.addStation(new StationImpl("B"));
        result.addConnection(new StationImpl("A"), new StationImpl("B"), new ConnectionImpl("1"), 2);
        assertEquals(subwayNetwork, result);
    }

    @Test
    void ensureHashCodeIsCorrect()
    {
        int expected = 0;
        int result = subwayNetwork.hashCode();
        assertEquals(expected, result);
    }

    @Test
    void ensureEqualsNullFalse()
    {
        boolean result = subwayNetwork.equals(null);
        assertFalse(result);
    }

    @Test
    void ensureEqualsDifferentTypeFalse()
    {
        boolean result = subwayNetwork.equals(new Object());
        assertFalse(result);
    }

    @Test
    void ensureEqualsSimilarObjectTrue()
    {
        boolean result = subwayNetwork.equals(new SubwayNetworkImpl());
        assertTrue(result);
    }

    @Test
    void ensureToStringIsCorrect()
    {
        subwayNetwork.addStation(new StationImpl("A"));
        subwayNetwork.addStation(new StationImpl("B"));
        subwayNetwork.addConnection(new StationImpl("A"), new StationImpl("B"), new ConnectionImpl("1"), 2);
        String expected = "GraphImpl: 2 vertices, 2 edges\n" +
                "Station: A; 0,0; 0,0; [] (0): \n" +
                "      (Connection: 1) - 2 - Station: B; 0,0; 0,0; []\n" +
                "\n" +
                "Station: B; 0,0; 0,0; [] (1): \n" +
                "      (Connection: 1) - 2 - Station: A; 0,0; 0,0; []\n\n";
        String result = subwayNetwork.toString();
        assertEquals(expected, result);
    }

    @Test
    void ensureGetShortestRouteInWeightIsCorrect()
    {
        LinkedList<Station> expected = new LinkedList<>();
        expected.add(new StationImpl("A"));
        expected.add(new StationImpl("B"));
        expected.add(new StationImpl("F"));

        LinkedList<Station> result = subwayNetworkPath.getShortestRouteInWeight(new StationImpl("A"), new StationImpl("F")).getPath();
        assertEquals(expected, result);
    }

    @Test
    void ensureGetShortestRouteInWeightInvalidStations()
    {
        Route route = subwayNetworkPath.getShortestRouteInWeight(new StationImpl("I"), new StationImpl("F"));
        assertNull(route);

        route = subwayNetworkPath.getShortestRouteInWeight(new StationImpl("A"), new StationImpl("A"));
        assertNull(route);
    }

    @Test
    void ensureGetShortestRouteInStationsIsCorrect()
    {
        LinkedList<Station> expected = new LinkedList<>();
        expected.add(new StationImpl("A"));
        expected.add(new StationImpl("B"));
        expected.add(new StationImpl("E"));

        LinkedList<Station> result = subwayNetworkPath.getShortestRouteInStations(new StationImpl("A"), new StationImpl("E")).getPath();
        assertEquals(expected, result);
    }

    @Test
    void getShortestRouteInLines()
    {
        LinkedList<Station> expected = new LinkedList<>();
        expected.add(new StationImpl("A"));
        expected.add(new StationImpl("F"));

        Route route = subwayNetworkPath.getShortestRouteInLines(new StationImpl("A"), new StationImpl("F"));
        LinkedList<Station> result = route.getPath();
        assertEquals(expected, result);
    }

    @Test
    void getShortestRouteInWeightGivenStationsIsCorrect()
    {
        LinkedList<Station> expected = new LinkedList<>();
        expected.add(new StationImpl("A"));
        expected.add(new StationImpl("B"));
        expected.add(new StationImpl("C"));
        expected.add(new StationImpl("D"));
        expected.add(new StationImpl("F"));

        List<Integer> expectedTimes = new ArrayList<>();
        expectedTimes.add(0);
        expectedTimes.add(1);
        expectedTimes.add(2);
        expectedTimes.add(3);
        expectedTimes.add(5);

        List<Station> stations = new ArrayList<>();
        stations.add(new StationImpl("C"));
        Route result = subwayNetworkPath.getShortestRouteInWeightGivenStations(new StationImpl("A"), new StationImpl("F"), stations);

        assertEquals(expected, result.getPath());
        assertEquals(expectedTimes.get(0).intValue() , result.getTimeAtStation(new StationImpl("A"), 0));
        assertEquals(expectedTimes.get(1).intValue() , result.getTimeAtStation(new StationImpl("B"), 0));
        assertEquals(expectedTimes.get(2).intValue() , result.getTimeAtStation(new StationImpl("C"), 0));
        assertEquals(expectedTimes.get(3).intValue() , result.getTimeAtStation(new StationImpl("D"), 0));
        assertEquals(expectedTimes.get(4).intValue() , result.getTimeAtStation(new StationImpl("F"), 0));
    }

    @Test
    void getShortestRouteInWeightGivenStationsBadSrcAndDestination()
    {
        List<Station> stations = new ArrayList<>();
        stations.add(new StationImpl("C"));
        Route result = subwayNetworkPath.getShortestRouteInWeightGivenStations(new StationImpl("A"), new StationImpl("A"), stations);

        assertEquals(null, result);
    }

    @Test
    void getShortestRouteInWeightGivenStationsNoIntermediaryStation()
    {
        LinkedList<Station> expected = new LinkedList<>();
        expected.add(new StationImpl("E"));
        expected.add(new StationImpl("F"));

        List<Station> stations = new ArrayList<>();
        Route result = subwayNetworkPath.getShortestRouteInWeightGivenStations(new StationImpl("E"), new StationImpl("F"), stations);

        assertEquals(expected, result.getPath());
    }

    @Test
    void getShortestRouteInWeightGivenStationsOnlyOne()
    {
        LinkedList<Station> expected = new LinkedList<>();
        expected.add(new StationImpl("C"));
        expected.add(new StationImpl("D"));

        List<Station> stations = new ArrayList<>();
        Route result = subwayNetworkPath.getShortestRouteInWeightGivenStations(new StationImpl("C"), new StationImpl("D"), stations);

        assertEquals(expected, result.getPath());
    }

    @Test
    void getShortestRouteInWeightGivenStationsNoConnection()
    {
        SubwayNetwork other = new SubwayNetworkImpl();
        Station s1 = new StationImpl("A");
        s1.addLine("1");
        s1.addLine("2");
        Station s2 = new StationImpl("B");
        s2.addLine("1");
        Station s3 = new StationImpl("C");
        s2.addLine("2");
        s2.addLine("1");

        other.addStation(s1);
        other.addStation(s2);
        other.addStation(s3);

        other.addConnection(s1, s2, new ConnectionImpl("1"), 2);

        List<Station> stations = new ArrayList<>();
        stations.add(s2);

        Route result = other.getShortestRouteInWeightGivenStations(s1, s3, stations);

        assertEquals(null, result);
    }

    @Test
    void ensureIsConnectedIsCorrectTrue()
    {
        SubwayNetwork other = new SubwayNetworkImpl();
        Station s1 = new StationImpl("A");
        s1.addLine("1");
        s1.addLine("2");
        Station s2 = new StationImpl("B");
        s2.addLine("1");
        Station s3 = new StationImpl("C");
        s2.addLine("2");
        s2.addLine("1");

        other.addStation(s1);
        other.addStation(s2);
        other.addStation(s3);

        other.addConnection(s1, s2, new ConnectionImpl("1"), 2);
        other.addConnection(s2, s3, new ConnectionImpl("1"), 2);
        other.addConnection(s3, s1, new ConnectionImpl("2"), 2);

        List<LinkedList<Station>> components = new ArrayList<>();

        boolean result = other.isConnected(components);

        assertTrue(result);
    }
}