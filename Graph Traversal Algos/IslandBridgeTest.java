import static org.junit.Assert.*;

import org.junit.Test;

public class IslandBridgeTest {

    @Test
    public void testOneWayBridges() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 0);
        g.addEdge(1, 2, 0);
        g.addEdge(2, 3, 0);
        g.addEdge(3, 0, 0);
        assertTrue(IslandBridge.allNavigable(g, 0));
        assertTrue(IslandBridge.allNavigable(g, 1));
        assertTrue(IslandBridge.allNavigable(g, 2));
        assertTrue(IslandBridge.allNavigable(g, 3));
    }

    @Test
    public void testIsolatedIslands() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 0);
        g.addEdge(1, 2, 0);
        g.addEdge(3, 4, 0); // Islands 3 and 4 are isolated.
        assertFalse(IslandBridge.allNavigable(g, 0));
        assertFalse(IslandBridge.allNavigable(g, 1));
        assertTrue(IslandBridge.allNavigable(g, 4));
        assertTrue(IslandBridge.allNavigable(g, 2));
        assertFalse(IslandBridge.allNavigable(g, 3));
    }

    @Test
    public void testTreeStructure() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 0);
        g.addEdge(0, 2, 0);
        g.addEdge(1, 3, 0);
        g.addEdge(1, 4, 0);
        assertFalse(IslandBridge.allNavigable(g, 0));
        assertFalse(IslandBridge.allNavigable(g, 1));
        assertTrue(IslandBridge.allNavigable(g, 2));
        assertTrue(IslandBridge.allNavigable(g, 3));
        assertTrue(IslandBridge.allNavigable(g, 4));
    }

    @Test
    public void testTreeStructureWithBackEdges() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 0);
        g.addEdge(0, 2, 0);
        g.addEdge(1, 3, 0);
        g.addEdge(3, 0, 0);
        g.addEdge(4, 2, 0);
        g.addEdge(2, 3, 0);
        assertTrue(IslandBridge.allNavigable(g, 0));
        assertTrue(IslandBridge.allNavigable(g, 1));
        assertTrue(IslandBridge.allNavigable(g, 2));
        assertTrue(IslandBridge.allNavigable(g, 3));
        assertFalse(IslandBridge.allNavigable(g, 4));
    }

    @Test
    public void testCircularNetwork() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 0);
        g.addEdge(1, 2, 0);
        g.addEdge(2, 3, 0);
        g.addEdge(3, 0, 0);
        assertTrue(IslandBridge.allNavigable(g, 0));
        assertTrue(IslandBridge.allNavigable(g, 1));
        assertTrue(IslandBridge.allNavigable(g, 2));
        assertTrue(IslandBridge.allNavigable(g, 3));
    }


}