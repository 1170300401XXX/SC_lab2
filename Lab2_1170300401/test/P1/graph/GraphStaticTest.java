/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // TODO test other vertex label types in Problem 3.2
    @Test
    public void testVertex() {
         int a = 1;
        int b = 2;
        int  c = 3;
        int d = 4;
        int e = 5;
        int f=5;
        Graph<Integer> graph = Graph.empty();
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.set(a, b, 2);
        graph.set(a, c, 1);
        graph.remove(a);
        assertFalse(graph.remove(a));
        Set<Integer> verSet=new HashSet<Integer>();
        graph.add(f);
        verSet.add(b);
        verSet.add(c);
        verSet.add(f);
        assertEquals(verSet, graph.vertices());
        graph.set(f, b, 2);
        graph.set(f, c, 1);
        Map<Integer, Integer> aTargets=new HashMap<Integer, Integer>();
        aTargets.put(b, 2);
        aTargets.put(c, 1);
        assertEquals(aTargets, graph.targets(f));
        graph.add(d);
        graph.add(e);
        graph.set(b, d, 2);
        graph.set(e, d, 1);
        Map<Integer,Integer> dSources=new HashMap<Integer,Integer>();
        dSources.put(b,2);
        dSources.put(e, 1);
        assertEquals(dSources, graph.sources(d));
        graph.set(b, d, 0);
        dSources.remove(b);
        assertEquals(dSources, graph.sources(d));
    }
}
