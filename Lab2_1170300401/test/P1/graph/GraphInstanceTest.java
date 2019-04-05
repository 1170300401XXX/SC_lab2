/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course
 * staff.
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
 * Tests for instance methods of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    // Testing strategy
    // TODO

    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class) public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices", Collections.emptySet(), emptyInstance().vertices());
    }

    // TODO other tests for instance methods of Graph
    @Test public void testGraph() {
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
}
