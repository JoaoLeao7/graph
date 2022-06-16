package esinf.tp2.entites.impl;

import esinf.tp2.entites.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RouteImplTest
{
    private RouteImpl route;

    @BeforeEach
    void setUp()
    {
        LinkedList<Station> path = new LinkedList<>();
        path.add(new StationImpl("A"));
        path.add(new StationImpl("B"));
        path.add(new StationImpl("C"));

        List<Integer> times = new ArrayList<>();
        times.add(0);
        times.add(3);
        times.add(4);

        LinkedHashMap<String, Set<Station>> lines = new LinkedHashMap<>();
        lines.put("1", new HashSet<>());
        lines.put("2", new HashSet<>());
        lines.get("1").add(new StationImpl("A"));
        lines.get("1").add(new StationImpl("B"));
        lines.get("2").add(new StationImpl("C"));

        route = new RouteImpl(path, times, lines);
    }

    @Test
    void ensureGetPathIsCorrect()
    {
        LinkedList<Station> expected = new LinkedList<>();
        expected.add(new StationImpl("A"));
        expected.add(new StationImpl("B"));
        expected.add(new StationImpl("C"));

        LinkedList<Station> result = route.getPath();
        assertEquals(expected, result);
    }

    @Test
    void ensureGetStationsInLineIsCorrect()
    {
        Set<Station> expected = new HashSet<>();
        expected.add(new StationImpl("A"));
        expected.add(new StationImpl("B"));

        Set<Station> result = route.getStationsInLine("1");
        assertEquals(expected, result);
    }

    @Test
    void ensureGetTimeAtStationReturnNegativeWhenNotFound()
    {
        double expected = -1;
        double result = route.getTimeAtStation(new StationImpl("D"), 1);
        assertEquals(expected, result);
    }

    @Test
    void ensureGetTimeAtStationReturnOffsetWhenItsStart()
    {
        double expected = 1;
        double result = route.getTimeAtStation(new StationImpl("A"), 1);
        assertEquals(expected, result);
    }

    @Test
    void ensureGetTimeAtStationIsCorrect()
    {
        double expected = 4;
        double result = route.getTimeAtStation(new StationImpl("B"), 1);
        assertEquals(expected, result);
    }

    @Test
    void getLines()
    {
        Set<String> expected = new HashSet<>();
        expected.add("1");
        expected.add("2");
        Set<String> result = route.getLines();
        assertEquals(expected, result);
    }

    @Test
    void getTotalTime()
    {
        double expected = 4;
        double result = route.getTotalTime();
        assertEquals(expected, result);
    }
}