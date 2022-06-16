package esinf.tp2.entites.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StationImplTest
{
    private StationImpl station;

    @BeforeEach
     void setUp()
    {
        station = new StationImpl("Paris",30.2, 10.1);
    }

    @Test
    void ensureGetNameIsCorrect()
    {
        String expected = "Paris";
        String result = station.getName();
        assertEquals(expected, result);
    }

    @Test
    void ensureGetLineAndAddLineWorks()
    {
        Set<String> expected = new HashSet<>();
        expected.add("3B");
        station.addLine("3B");
        Set<String> result = station.getLine();
        assertEquals(expected, result);
    }

    @Test
    void ensureGetLatitudeIsCorrect()
    {
        double expected = 30.2;
        double result = station.getLatitude();
        assertEquals(expected, result);
    }

    @Test
    void ensureGetLongitudeIsCorrect()
    {
        double expected = 10.1;
        double result = station.getLongitude();
        assertEquals(expected, result);
    }

    @Test
    void ensureHashCodeIsCorrect()
    {
        int expected = 76884331;
        int result = station.hashCode();
        assertEquals(expected, result);
    }

    @Test
    void ensureEqualsNullObject()
    {
        boolean result = station.equals(null);
        assertFalse(result);
    }

    @Test
    void ensureEqualsDifferentClass()
    {
        boolean result = station.equals(new Object());
        assertFalse(result);
    }

    @Test
    void ensureEqualsSimilarObject()
    {
        boolean result = station.equals(new StationImpl("Paris"));
        assertTrue(result);
    }

    @Test
    void ensureToStringIsCorrect()
    {
        String expected = "Station: Paris; 30,2; 10,1; []";
        String result = station.toString();
        assertEquals(expected, result);
    }
}