import static org.junit.Assert.*;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class WidestPathTest {

    @Test
    public void singleVertexGraph() {
        Graph g = new Graph(1);

        List<Integer> expected = new LinkedList<>();
        expected.add(0);

        List<Integer> result = WidestPath.getWidestPath(g, 0, 0);

        assertEquals(expected, result);
    }

    @Test
    public void noEdgesInGraph() {
        Graph g = new Graph(3);

        List<Integer> result = WidestPath.getWidestPath(g, 0, 2);

        assertTrue(result.isEmpty());
    }

    @Test
    public void longestPathIsWidest() {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 0, 1);
        g.addEdge(0, 2, 2);
        g.addEdge(2, 0, 2);
        g.addEdge(2, 1, 3);
        g.addEdge(1, 2, 3);

        List<Integer> expected = new LinkedList<>();
        expected.add(0);
        expected.add(2);
        expected.add(1);

        List<Integer> result = WidestPath.getWidestPath(g, 0, 1);

        assertEquals(expected, result);
    }

    @Test
    public void shortestPathIsWidest() {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 7);
        g.addEdge(1, 0, 1);
        g.addEdge(0, 2, 2);
        g.addEdge(2, 0, 2);
        g.addEdge(2, 1, 3);
        g.addEdge(1, 2, 3);

        List<Integer> expected = new LinkedList<>();
        expected.add(0);
        expected.add(1);

        List<Integer> result = WidestPath.getWidestPath(g, 0, 1);

        assertEquals(expected, result);
    }

    @Test
    public void disconnectedGraph() {
        Graph g = new Graph(5);

        List<Integer> result = WidestPath.getWidestPath(g, 0, 4);

        assertTrue(result.isEmpty());
    }

    @Test
    public void randomGraph() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 3);
        g.addEdge(2, 3, 2);
        g.addEdge(3, 4, 4);

        List<Integer> expected = new LinkedList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        List<Integer> result = WidestPath.getWidestPath(g, 0, 4);

        assertEquals(expected, result);
    }
}
