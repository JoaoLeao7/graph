/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf.tp2.utils;

import esinf.tp2.util.Edge;
import esinf.tp2.util.Vertex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author frodrigues
 */
public class EdgeTest
{
    Edge<String, String> instance = new Edge<>();

    public EdgeTest()
    {
    }

    /**
     * Test of getElement method, of class Edge.
     */
    @Test
    public void testGetElement()
    {
        String expResult = null;
        assertEquals(expResult, instance.getElement());

        Edge<String, String> instance1 = new Edge<>("edge1", 1, null, null);
        expResult = "edge1";
        assertEquals(expResult, instance1.getElement());
    }

    /**
     * Test of setElement method, of class Edge.
     */
    @Test
    public void testSetElement()
    {
        String eInf = "edge1";
        instance.setElement(eInf);

        assertEquals("edge1", instance.getElement());
    }

    /**
     * Test of getWeight method, of class Edge.
     */
    @Test
    public void testGetWeight()
    {
        int expResult = 0;
        assertEquals(expResult, instance.getWeight());
    }

    /**
     * Test of setWeight method, of class Edge.
     */
    @Test
    public void testSetWeight()
    {
        int ew = 2;
        instance.setWeight(ew);

        int expResult = 2;
        assertEquals(expResult, instance.getWeight());
    }

    /**
     * Test of getVOrig method, of class Edge.
     */
    @Test
    public void testGetVOrig()
    {
        Object expResult = null;
        assertEquals(expResult, instance.getVOrig());

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1, vertex1, vertex1);
        assertEquals(vertex1.getElement(), otherEdge.getVOrig());
    }

    /**
     * Test of setVOrig method, of class Edge.
     */
    @Test
    public void testSetVOrig()
    {
        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        assertEquals(vertex1.getElement(), instance.getVOrig());
    }

    /**
     * Test of getVDest method, of class Edge.
     */
    @Test
    public void testGetVDest()
    {
        Object expResult = null;
        assertEquals(expResult, instance.getVDest());

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1, vertex1, vertex1);
        assertEquals(vertex1.getElement(), otherEdge.getVDest());
    }

    /**
     * Test of setVDest method, of class Edge.
     */
    @Test
    public void testSetVDest()
    {

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVDest(vertex1);
        assertEquals(vertex1.getElement(), instance.getVDest());
    }

    /**
     * Test of getEndpoints method, of class Edge.
     */
    @Test
    public void testGetEndpoints()
    {

        String[] expResult = null;
        String[] result = instance.getEndpoints();
        assertArrayEquals(expResult, result);

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);

        String[] expResult1 = {"Vertex1", "Vertex1"};
        assertArrayEquals(expResult1, instance.getEndpoints());
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals()
    {
        assertFalse(instance.equals(null));

        assertTrue( instance.equals(instance));

        assertTrue(instance.equals(instance.clone()));

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1, vertex1, vertex1);

        assertFalse(instance.equals(otherEdge));
    }

    /**
     * Test of compareTo method, of class Edge.
     */
    @Test
    public void testCompareTo()
    {
        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1, vertex1, vertex1);

        int expResult = -1;
        int result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);

        otherEdge.setWeight(0);
        expResult = 0;
        result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);

        instance.setWeight(2);
        expResult = 1;
        result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class Edge.
     */
    @Test
    public void testClone()
    {
        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1, vertex1, vertex1);

        Edge<String, String> instClone = otherEdge.clone();

        assertTrue(otherEdge.getElement() == instClone.getElement());
        assertTrue(otherEdge.getWeight() == instClone.getWeight());

        String[] expResult = otherEdge.getEndpoints();
        assertArrayEquals(expResult, instClone.getEndpoints());
    }

    /**
     * Test of toString method, of class Edge.
     */
    @Test
    public void testToString()
    {
        instance.setElement("edge1");
        instance.setWeight(1);
        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);

        String expResult = "(edge1) - 1 - Vertex1";
        String result = instance.toString().trim();
        assertEquals(expResult, result);
    }
}
