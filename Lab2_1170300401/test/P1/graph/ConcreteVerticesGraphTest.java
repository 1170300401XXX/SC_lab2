/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course
 * staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }

    /*
     * Testing ConcreteVerticesGraph...
     */

    // Testing strategy for ConcreteVerticesGraph.toString()
    // TODO
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test public void testConcreteVerticesGraphtoString() {
        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";
        String e = "E";
        String f = "F";
        Graph<String> graph = emptyInstance();
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.set(a, b, 2);
        graph.set(a, c, 1);
        assertEquals("[A,{B=2, C=1}][B,{}][C,{}]", graph.toString());
        graph.remove(a);
        assertFalse(graph.remove(a));
        Set<String> verSet = new HashSet<String>();
        graph.add(f);
        verSet.add(b);
        verSet.add(c);
        verSet.add(f);
        assertEquals(verSet, graph.vertices());
        graph.set(f, b, 2);
        graph.set(f, c, 1);
        Map<String, Integer> aTargets = new HashMap<String, Integer>();
        aTargets.put(b, 2);
        aTargets.put(c, 1);
        assertEquals(aTargets, graph.targets(f));
        graph.add(d);
        graph.add(e);
        graph.set(b, d, 2);
        graph.set(e, d, 1);
        Map<String, Integer> dSources = new HashMap<String, Integer>();
        dSources.put(b, 2);
        dSources.put(e, 1);
        assertEquals(dSources, graph.sources(d));
        graph.set(b, d, 0);
        dSources.remove(b);
        assertEquals(dSources, graph.sources(d));
    }

    /*
     * Testing Vertex...
     */

    // Testing strategy for Vertex
    // TODO

    // TODO tests for operations of Vertex
    @Test public void testVertex() {
        Vertex<String> v = new Vertex<String>("yxt");
        assertEquals("yxt", v.getVertex());
        v.setTargetVertices("miao", 2);
        assertEquals("[yxt,{miao=2}]", v.toString());
        assertEquals(2, v.setTargetVertices("miao", 3));
        v.setTargetVertices("amiao", 3);
        Map<String, Integer> targetMap = new HashMap<String, Integer>();
        targetMap.put("miao", 3);
        targetMap.put("amiao", 3);
        assertEquals(targetMap, v.getTargetVertices());
        targetMap.remove("miao");
        v.removeTarget("miao");
        assertEquals(targetMap, v.getTargetVertices());
    }
}
