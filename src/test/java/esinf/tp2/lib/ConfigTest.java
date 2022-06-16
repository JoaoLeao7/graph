package esinf.tp2.lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void ensureLoadConfigsWorks()
    {
        Config.loadConfigs();
        assertEquals("coordinates.csv", Config.COORDINATES_FILE);
        assertEquals("lines_and_stations.csv", Config.LINES_AND_STATIONS_FILE);
        assertEquals("connections.csv", Config.CONNECTIONS_FILE);
    }
}