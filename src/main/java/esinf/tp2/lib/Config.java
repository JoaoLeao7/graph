package esinf.tp2.lib;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config
{
    private static final String PROPERTIES_FILE = "config.properties";

    private static final String COORDINATES_PROPERTY_NAME = "coordinates";
    private static final String LINES_AND_STATIONS_PROPERTY_NAME = "lines_and_stations";
    private static final String CONNECTIONS_PROPERTY_NAME = "connections";

    public static String COORDINATES_FILE;
    public static String LINES_AND_STATIONS_FILE;
    public static String CONNECTIONS_FILE;

    public static void loadConfigs()
    {
        Properties prop = new Properties();
        InputStream input = null;

        try
        {
            input = new FileInputStream(PROPERTIES_FILE);

            prop.load(input);

            COORDINATES_FILE = prop.getProperty(COORDINATES_PROPERTY_NAME);
            LINES_AND_STATIONS_FILE = prop.getProperty(LINES_AND_STATIONS_PROPERTY_NAME);
            CONNECTIONS_FILE = prop.getProperty(CONNECTIONS_PROPERTY_NAME);
        }

        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
