/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course
 * staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();

    // Abstraction function:
    // TODO
    // Representation invariant:
    // TODO vertices is a collection of vertex
    // edges is a collection of Edge(edge is made up of vertex)
    // Safety from rep exposure:
    // TODO All fields are private

    // TODO constructor

    // TODO checkRep
    private void checkRep() {
        // TODO Auto-generated method stub
        boolean flag = false;
        Set<String> tempSet = new HashSet<String>();
        for (int i = 0; i < edges.size(); i++) {
            String[] tempStrings = edges.get(i).toString().split(" ");
            tempSet.add(tempStrings[0]);
        }
        if (tempSet.size() == edges.size())
            flag = true;
        assert flag == true;
    }

    @Override public boolean add(L vertex) {
        checkRep();
        return vertices.add(vertex);
        // throw new RuntimeException("not implemented");
    }

    /**
     * Add, change, or remove a weighted directed edge in this graph.
     * If weight is nonzero, add an edge or update the weight of that edge;
     * vertices with the given labels are added to the graph if they do not
     * already exist.
     * If weight is zero, remove the edge if it exists (the graph is not
     * otherwise modified).
     * 
     * @param source label of the source vertex
     * @param target label of the target vertex
     * @param weight nonnegative weight of the edge
     * @return the previous weight of the edge, or zero if there was no such
     *         edge
     */
    @Override public int set(L source, L target, int weight) {
        Edge<L> newEdge = new Edge<L>(source, target, weight);
        int w = 0;
        if (weight > 0) {
            for (Edge<?> l : edges) {// 遍历边数组 如果有这个边 则更改权重 输出原权重
                if (l.getSource().equals(source) && l.getTarget().equals(target)) {
                    w = l.getWeight();
                    edges.add(new Edge<L>(source, target, weight));
                    break;
                }
            }
            if (!edges.contains(newEdge)) {
                vertices.add(source);
                vertices.add(target);
                edges.add(newEdge);
            }
        } else if (weight == 0) {
            int i = 0;
            for (i = 0; i < edges.size(); i++) {// 遍历边数组 删除该边返回0
                if (edges.get(i).getSource().equals(source) && edges.get(i).getTarget().equals(target)) {
                    w = edges.get(i).getWeight();
                    edges.remove(i);
                    break;
                }
            }
        }
        checkRep();
        return w;
        // throw new RuntimeException("not implemented");
    }

    @Override public boolean remove(L vertex) {
        if (vertices.contains(vertex)) {
            for (Edge<L> e : edges) {
                if (e.getSource().equals(vertex))
                    edges.remove(e);
                if (e.getTarget().equals(vertex))
                    edges.remove(e);
            }
            checkRep();
            return vertices.remove(vertex);
        } else
            return false;
        // throw new RuntimeException("not implemented");
    }

    @Override public Set<L> vertices() {
        Set<L> verticeSet = new HashSet<L>(vertices);
        checkRep();
        return verticeSet;
        // throw new RuntimeException("not implemented");
    }

    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> sourceVertice = new HashMap<L, Integer>();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getTarget().equals(target))
                sourceVertice.put(edges.get(i).getSource(), edges.get(i).getWeight());
        }
        checkRep();
        return sourceVertice;
        // throw new RuntimeException("not implemented");
    }

    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> targetVertice = new HashMap<L, Integer>();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getSource().equals(source))
                targetVertice.put(edges.get(i).getTarget(), edges.get(i).getWeight());
        }
        checkRep();
        return targetVertice;
        // throw new RuntimeException("not implemented");
    }

    // TODO toString()
    @Override public String toString() {
        String str = new String();
        for (Edge<L> l : edges) {
            str += l.toString() + " ";
        }
        str += vertices;
        checkRep();
        return str;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {

    // TODO fields
    private L source_v;
    private L target_v;
    private int weight;

    // Representation invariant:
    // TODO source_v!=null
    // target_v!=null
    // weight>=0
    // Abstraction function:
    // TODO AF(source_v,target_v,weight)=source_v--weight-->target
    // Safety from rep exposure:
    // TODO All fields are private
    public L getSource() {
        checkRep();
        return this.source_v;
    }

    public L getTarget() {
        checkRep();
        return this.target_v;
    }

    public int getWeight() {
        return this.weight;
    }

    // TODO constructor
    public Edge(L s, L t, int w) {
        // TODO Auto-generated constructor stub
        this.source_v = s;
        this.target_v = t;
        this.weight = w;
        checkRep();
    }

    // TODO checkRep
    private void checkRep() {
        assert weight >= 0;
        assert source_v != null;
        assert target_v != null;
    }

    // TODO methods
    // TODO toString()
    @Override public String toString() {
        checkRep();
        return "[" + getSource() + "," + getTarget() + "," + getWeight() + "]";
    }
}
