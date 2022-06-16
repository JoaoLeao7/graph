package esinf.tp2.entites.impl;

import esinf.tp2.entites.Connection;

/**
 * This is a simple implementation of the interface Connection.
 * @see esinf.tp2.entites.Connection
 */
public class ConnectionImpl implements Connection
{
    private String line;

    /**
     * Constructs a Connection.
     * @param line The id of the line for this {@link Connection connection}.
     */
    public ConnectionImpl(String line)
    {
        this.line = line;
    }

    @Override
    public String getLine()
    {
        return line;
    }

    @Override
    public String toString()
    {
        return String.format("Connection: %s", line);
    }
}
