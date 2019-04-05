package P2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class FriendshipGraphTest {

    /*
     * Tests addVertex.
     */
    @Test
    public void addVertexTest() {
        FriendshipGraph graphTest = new FriendshipGraph();
        Person yyy = new Person("yyy");
        Person ddd = new Person("ddd");
        graphTest.addVertex(yyy);
        assertEquals(true, graphTest.addVertex(ddd));
        assertEquals(false, graphTest.addVertex(yyy));
    }

    /*
     * Tests addEdge.
     */
    @Test
    public void addEdgeTest() {
        FriendshipGraph graphTest = new FriendshipGraph();
        Person yyy = new Person("yyy");
        Person ddd = new Person("ddd");
        Person ttt = new Person("ttt");
        graphTest.addVertex(yyy);
        graphTest.addVertex(ddd);
        assertEquals(true, graphTest.addEdge(yyy, ddd));
        assertEquals(false, graphTest.addEdge(yyy, ttt));
    }

    /*
     * Tests getDistance.
     */
    @Test
    public void getDistanceTest() {
        FriendshipGraph graphTest = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graphTest.addVertex(rachel);
        graphTest.addVertex(ross);
        graphTest.addVertex(ben);
        graphTest.addVertex(kramer);
        graphTest.addEdge(rachel, ross);
        graphTest.addEdge(ross, rachel);
        graphTest.addEdge(ross, ben);
        graphTest.addEdge(ben, ross);
        assertEquals(1, graphTest.getDistance(rachel, ross));
        assertEquals(2, graphTest.getDistance(rachel, ben));
        assertEquals(0, graphTest.getDistance(rachel, rachel));
        assertEquals(-1, graphTest.getDistance(rachel, kramer));
    }
    /*
     * Tests Person class.
     */
    @Test
    public void PersonTest() {
        Person yxt=new Person("YXT");
        assertEquals("YXT", yxt.getName());
    }
}
