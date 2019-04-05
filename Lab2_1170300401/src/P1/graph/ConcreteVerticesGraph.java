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
public class ConcreteVerticesGraph<L> implements Graph<L> {

    private final List<Vertex<L>> vertices = new ArrayList<>();

    // Abstraction function:
    // TODO represents vertices' relationship
    // Representation invariant:
    // TODO vertices is the collection of vertex
    // vertex contains its targets and the weight of edge between it and target
    // Safety from rep exposure:
    // TODO All fields are private

    // TODO constructor

    // TODO checkRep

    private void checkRep() {
        // TODO Auto-generated method stub
        List<L> verticesList=new ArrayList<L>();
        for (int i = 0; i < vertices.size(); i++) {
            verticesList.add(vertices.get(i).getVertex());
        }
        for (int i = 0; i < verticesList.size(); i++) {
            assert verticesList.containsAll(vertices.get(i).getTargetVertices().keySet()):"an vertex is illegal";
            assert verticesList.contains(vertices.get(i).getVertex()):"the vertex is illegal";
            assert vertices.get(i)!=null;
        }
        for(L s:verticesList) {
            assert s!=null;
        }
    }

    @Override public boolean add(L vertex) {
        Vertex<L> v = new Vertex<L>(vertex);
        checkRep();
        return vertices.add(v);
        // throw new RuntimeException("not implemented");
    }

    @Override public int set(L source, L target, int weight) {
        Vertex<L> sVertex = new Vertex<L>(source);
        Vertex<L> tVertex = new Vertex<L>(target);
        int w = 0;
        boolean isTarget = false;
        boolean isSource = false;

        for (Vertex<L> v : vertices) {
            if (v.getVertex().equals(target)) {
                isTarget = true;
                break;
            }
        }
        if (!isTarget && weight > 0)
            vertices.add(tVertex);
        for (Vertex<L> l : vertices) {
            if (l.getTargetVertices() != null && l.getVertex().equals(source)) {// 如果图中含有source
                if (l.getTargetVertices().containsKey(target)) {
                    w = l.setTargetVertices(target, weight);
                } else {
                    l.setTargetVertices(target, weight);
                }
                isSource = true;
                break;
            }
        }
        if (!isSource && weight > 0) {// 如果图中没有source点
            vertices.add(sVertex);
            sVertex.setTargetVertices(target, weight);
        }
        checkRep();
        return w;
        // throw new RuntimeException("not implemented");
    }

    @Override public boolean remove(L vertex) {
        Set<L> vSet = new HashSet<L>();
        for (Vertex<L> l : vertices) {
            vSet.add(l.getVertex());
        }
        if (vSet.contains(vertex)) {
            for (Vertex<L> l : vertices) {
                if (l.getTargetVertices().containsKey(vertex)) {
                    l.getTargetVertices().remove(vertex);
                }
            }
            for (Vertex<L> d : vertices) {
                if (d.getVertex().equals(vertex)) {
                    vertices.remove(d);
                    break;
                }
            }
            checkRep();
            return true;
        } else {
            checkRep();
            return false;
        }
        // throw new RuntimeException("not implemented");
    }

    @Override public Set<L> vertices() {
        Set<L> verticeSet = new HashSet<L>();
        for (Vertex<L> v : vertices) {
            verticeSet.add(v.getVertex());
        }
        checkRep();
        return verticeSet;
        // throw new RuntimeException("not implemented");
    }

    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> sourcesMap = new HashMap<L, Integer>();
        for (Vertex<L> i : vertices) {
            if (i.getTargetVertices().containsKey(target)) {
                sourcesMap.put(i.getVertex(), i.getTargetVertices().get(target));
            }
        }
        checkRep();
        return sourcesMap;
        // throw new RuntimeException("not implemented");
    }

    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> targetsMap = new HashMap<L, Integer>();
        for (Vertex<L> i : vertices) {
            if (i.getVertex().equals(source)) {
                targetsMap = i.getTargetVertices();
            }
        }
        checkRep();
        return targetsMap;
        // throw new RuntimeException("not implemented");
    }

    // TODO toString()
    @Override public String toString() {
        String str = new String();
        for (Vertex<L> l : vertices) {
            str += l.toString();
        }
        checkRep();
        return str;
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {

    // TODO fields
    private L vertex;
    private Map<L, Integer> target_v;

    // Abstraction function:
    // TODO represents vertex with its targets
    // the target-->weight(vertex--weight-->target)
    // Representation invariant:
    // TODO vertex!=null
    // the weight of target_v with vertex >=0
    // Safety from rep exposure:
    // TODO All fields are private
    public L getVertex() {
        return this.vertex;
    }

    public Map<L, Integer> getTargetVertices() {
        return this.target_v;
    }

    public int removeTarget(L target) {
        if (getTargetVertices() != null && getTargetVertices().containsKey(target) && target != null) {
            return getTargetVertices().remove(target);
        } else {
            return 0;
        }
    }

    public int setTargetVertices(L target, int weight) {
        int w = 0;
        if (target != null) {
            if (weight == 0) {
                w = this.removeTarget(target);
            } else if (weight > 0) {
                w = removeTarget(target);
                this.target_v.put(target, weight);
            }
        }
        return w;
    }

    // TODO constructor
    public Vertex(L v) {
        // TODO Auto-generated constructor stub
        this.vertex = v;
        this.target_v = new HashMap<L, Integer>();
        checkRep();
    }

    // TODO checkRep
    private void checkRep() {
        // TODO Auto-generated method stub
        assert vertex != null;
    }
    // TODO methods

    // TODO toString()
    @Override public String toString() {
        String str = new String();
        str = "[" + getVertex() + "," + getTargetVertices() + "]";
        return str;
    }

}
