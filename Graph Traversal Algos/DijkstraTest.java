import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class DijkstraTest {

    @Test
    public void testBasicShortestPath() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 3);
        graph.addEdge(0, 3, 4);
        List<Integer> path = Dijkstra.getShortestPath(graph, 0, 3);
        List<Integer> expectedPath = new LinkedList<>();
        expectedPath.add(0);
        expectedPath.add(3);
        assertEquals(expectedPath, path);
    }

    @Test
    public void testBasicShortestPathAgain() {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 3);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 2);
        graph.addEdge(1, 4, 4);
        List<Integer> path = Dijkstra.getShortestPath(graph, 0, 4);
        List<Integer> expectedPath = new LinkedList<>();
        expectedPath.add(0);
        expectedPath.add(1);
        expectedPath.add(4);
        assertEquals(expectedPath, path);
    }

    @Test
    public void testNoPath() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(2, 3, 2);
        List<Integer> path = Dijkstra.getShortestPath(graph, 0, 3);
        assertEquals(new LinkedList<>(), path);
        assertTrue(path.isEmpty());
    }

    @Test
    public void testSingleNode() {
        Graph graph = new Graph(1);
        List<Integer> path = Dijkstra.getShortestPath(graph, 0, 0);
        List<Integer> expectedPath = new LinkedList<>();
        expectedPath.add(0);
        assertEquals(expectedPath, path);
    }

    @Test
    public void testNegativeWeight() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, -2); // Negative weight edge
        graph.addEdge(2, 3, 3);
        graph.addEdge(0, 3, 4);
        List<Integer> path = Dijkstra.getShortestPath(graph, 0, 3);
        List<Integer> expectedPath = new LinkedList<>();
        expectedPath.add(0);
        expectedPath.add(1);
        expectedPath.add(2);
        expectedPath.add(3);
        assertEquals(expectedPath, path);
    }

    @Test
    public void testLargeGraph() {
        Graph graph = new Graph(1000);
        for (int i = 0; i < 999; i++) {
            graph.addEdge(i, i + 1, 1);
        }
        List<Integer> path = Dijkstra.getShortestPath(graph, 0, 999);
        List<Integer> expectedPath = new LinkedList<>();
        for (int i = 0; i <= 999; i++) {
            expectedPath.add(i);
        }
        assertEquals(expectedPath, path);
    }
}

