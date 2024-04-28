import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class GraphTest {

    @Test
    public void testGraphCreation() {
        Graph graph = new Graph(5);
        assertEquals(5, graph.getSize());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGraphCreationEmpty() {
        new Graph(0);
    }

    @Test
    public void testHasEdge() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 3);
        assertTrue(graph.hasEdge(0, 1));
        assertTrue(graph.hasEdge(1, 2));
        assertFalse(graph.hasEdge(2, 3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testHasEdgeNoVertex() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 3);
        assertTrue(graph.hasEdge(0, 4));
    }

    @Test
    public void testGetWeight() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 3);
        assertEquals(5, graph.getWeight(0, 1));
        assertEquals(3, graph.getWeight(1, 2));
    }

    @Test (expected = NoSuchElementException.class)
    public void testGetWeightNoEdge() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 3);
        graph.getWeight(0, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddEdge() {
        Graph graph = new Graph(3);
        assertTrue(graph.addEdge(0, 1, 5));
        assertTrue(graph.addEdge(1, 2, 3));
        assertFalse(graph.addEdge(0, 1, 10));
        graph.addEdge(2, 2, 7);
    }

    @Test
    public void testOutNeighbors() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 7);
        Set<Integer> zeroOut = new HashSet<>();
        zeroOut.add(1);
        assertEquals(zeroOut, graph.outNeighbors(0));
        Set<Integer> oneOut = new HashSet<>();
        oneOut.add(2);
        oneOut.add(3);
        assertEquals(oneOut, graph.outNeighbors(1));
        Set<Integer> twoOut = new HashSet<>();
        assertEquals(twoOut, graph.outNeighbors(2));
        Set<Integer> threeOut = new HashSet<>();
        assertEquals(threeOut, graph.outNeighbors(3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testOutNeighborsNoVtx() {
        Graph graph = new Graph(2);
        graph.addEdge(0, 1, 5);
        graph.outNeighbors(4);
    }
}
