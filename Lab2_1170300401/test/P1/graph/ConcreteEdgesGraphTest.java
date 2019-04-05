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
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<>();
    }

    /*
     * Testing ConcreteEdgesGraph...
     */

    // Testing strategy for ConcreteEdgesGraph.toString()
    // TODO

    // TODO tests for ConcreteEdgesGraph.toString()
    @Test public void testConcreteEdgesGraphtoString() {
        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";
        String e = "E";
        String f="F";
        Graph<String> graph = emptyInstance();
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.set(a, b, 2);
        graph.set(a, c, 1);
        //System.out.println(graph.toString());
        assertEquals("[A,B,2] [A,C,1] [A, B, C]", graph.toString());
        graph.remove(a);
        assertFalse(graph.remove(a));
        Set<String> verSet=new HashSet<String>();
        graph.add(f);
        verSet.add(b);
        verSet.add(c);
        verSet.add(f);
        assertEquals(verSet, graph.vertices());
        graph.set(f, b, 2);
        graph.set(f, c, 1);
        Map<String, Integer> aTargets=new HashMap<String, Integer>();
        aTargets.put(b, 2); 
        aTargets.put(c, 1);
        assertEquals(aTargets, graph.targets(f));
        graph.add(d);
        graph.add(e);
        graph.set(b, d, 2);
        graph.set(e, d, 1);
        Map<String,Integer> dSources=new HashMap<String,Integer>();
        dSources.put(b,2);
        dSources.put(e, 1);
        assertEquals(dSources, graph.sources(d));
        graph.set(b, d, 0);
        dSources.remove(b);
        assertEquals(dSources, graph.sources(d));
    }
    /*
     * Testing Edge...
     */

    // Testing strategy for Edge
    // TODO

    // TODO tests for operations of Edge
    @Test
    public void testEdge() {
        Edge<String> edge=new Edge<String>("yxt","asdf",2);
        assertEquals("yxt", edge.getSource());
        assertEquals("asdf", edge.getTarget());
        assertEquals(2, edge.getWeight());
        assertEquals("[yxt,asdf,2]", edge.toString());
    }
}
