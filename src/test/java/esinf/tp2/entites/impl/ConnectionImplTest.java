package esinf.tp2.entites.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionImplTest
{
    private ConnectionImpl connection;

    @BeforeEach
    void setUp()
    {
        connection = new ConnectionImpl("1");
    }

    @Test
    void ensureGetLineIsCorrect()
    {
        String expected = "1";
        String result = connection.getLine();
        assertEquals(expected, result);
    }

    @Test
    void ensureToStringIsCorrect()
    {
        String expected = "Connection: 1";
        String result = connection.toString();
        assertEquals(expected, result);
    }
}