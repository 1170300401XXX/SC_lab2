package P2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import P1.graph.*;

public class FriendshipGraph {

    // TODO fields
    private final Graph<Person> networkGraph = Graph.empty();

    // Representation invariant:
    // TODO networkGraph contains person and the relationship among them
    // Abstraction function:
    // TODO The graph contains information about the network among people
    // Safety from rep exposure:
    // TODO All fields are private
    public int getDistance(Person name1, Person name2) {
        int m = getNum(name1);
        int n = getNum(name2);
        Queue<Integer> queue = new LinkedList<Integer>();
        if (m < 0 || n < 0) {
            System.out.println("The person isn't exited.");
            checkRep();
            return -2;
        }
        if (m == n) {
            checkRep();
            return 0;
        }
        int length = networkGraph.vertices().size();
        int i = m, j, distance = 1;
        boolean[] visit = new boolean[length];
        int[] level = new int[length];// 表示广搜层数
        visit[m] = true;
        level[m] = 0;
        queue.add(m);
        while (!queue.isEmpty()) {// 当队列非空时
            i = queue.remove().intValue();// 先从队列里删掉i
            for (j = 0; j < length; j++) {
                if (networkGraph.targets(getPerson(i)).containsKey(getPerson(j)) && visit[j] == false) {
                    if (j == n) {
                        Arrays.parallelSort(level);
                        distance = level[length - 1] + 1;
                        checkRep();
                        return distance;
                    } else {
                        queue.add(j);
                        level[j] = level[i] + 1;
                        visit[j] = true;
                    }
                }
            }
        }
        checkRep();
        return -1;
    }

    private Person getPerson(int m) {
        for (Person p : networkGraph.vertices()) {
            if (m == getNum(p)) {
                return p;
            }
        }
        return new Person(null);
    }

    private int getNum(Person name) {
        int m = 0;
        for (Person p : networkGraph.vertices()) {
            if (p.equals(name)) {
                return m;
            }
            m++;
        }
        return 999999999;
    }

    public boolean addVertex(Person name) {
        if (!networkGraph.vertices().contains(name)) {
            networkGraph.add(name);
            checkRep();
            return true;
        } else {
            checkRep();
            return false;
        }
    }

    public boolean addEdge(Person name1, Person name2) {
        if (networkGraph.vertices().contains(name1) && networkGraph.vertices().contains(name2)) {
            networkGraph.set(name1, name2, 1);
            checkRep();
            return true;
        } else {
            checkRep();
            return false;
        }
    }
    // TODO checkRep

    private void checkRep() {
        for (int i = 0; i < networkGraph.vertices().size(); i++) {
            assert networkGraph.vertices().containsAll(networkGraph.targets(getPerson(i)).keySet());
            assert networkGraph.vertices().containsAll(networkGraph.sources(getPerson(i)).keySet());
            assert getPerson(i) != null;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        System.out.println(graph.getDistance(rachel, ross));
        // should print 1
        System.out.println(graph.getDistance(rachel, ben));
        // should print 2
        System.out.println(graph.getDistance(rachel, rachel));
        // should print 0
        System.out.println(graph.getDistance(rachel, kramer));
        // should print -1

    }
}
