import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * <h1>QUESTION 3: BUNNY HOP</h1>
 * <p/>
 *
 * Class to implement the algorithm to find the minimum number of hops a bunny rabbit
 * must make to navigate from the bottom right corner to the top left corner of the city.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question3 {

    /**
     * Returns the minimum number of hops needed for the bunny rabbit to make its way from the
     * bottom right corner to the top left corner given the hopping constraints in the writeup,
     * or -1 if a path does not exist.
     *
     * @param city an n x n 2d-array representing the city where each entry is a positive integer
     *             which defines how many cells the bunny can hop up or to the left when that
     *             entry is reached.
     * @return the minimum number of hops the bunny needs to make, or -1 if there is no 
     *         possible path
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static int getMinimumNumberOfHops(int[][] city) {

        HashSet<Integer>[] edges = new HashSet[city.length * city[0].length];

        for (int i = 0; i < edges.length; i++) {
            edges[i] = new HashSet<>();
        }

        int v = 0;
        for (int i = 0; i < city.length; i++) {
            for (int j = 0; j < city[i].length; j++) {
                int hops = city[i][j];
                int upSlot = i - hops;
                if (upSlot >= 0) {
                    addEdge(v, upSlot * city.length + j, edges);
                }
                int leftSlot = j - hops;
                if (leftSlot >= 0) {
                    addEdge(v, i * city.length + leftSlot, edges);
                }
                v ++;
            }
        }

        int source = ((city.length - 1) * city.length) + (city[0].length - 1);
        int[] parents = bfs(edges, source);

        LinkedList<Integer> path = new LinkedList<>();
        if (pathExists(source, 0, path, parents)) {
            return path.size() - 1;
        }

        return -1;
    }

    private static boolean addEdge(int u, int v, HashSet<Integer>[] edges) {
        if (u == v) {
            return false;
        }
        if (hasEdge(u, v, edges)) {
            return false;
        }
        HashSet<Integer> uHash = edges[u];
        uHash.add(v);
        return true;
    }

    private static boolean hasEdge(int u, int v, HashSet<Integer>[] edges) {
        if (u < 0 || u >= edges.length || v < 0 || v >= edges.length) {
            throw new IllegalArgumentException("vertex does not exist...");
        }

        HashSet<Integer> uHash = edges[u];
        return uHash.contains(v);
    }

    private static Set<Integer> outNeighbors(int v, HashSet<Integer>[] edges) {
        if (v < 0 || v >= edges.length) {
            throw new IllegalArgumentException("vertex does not exist...");
        }
        return edges[v];
    }

    public static int[] bfs(HashSet<Integer>[] edges, int s) {

        int numVertices = edges.length;
        boolean[] discovered = new boolean[numVertices];
        int[] parent = new int[numVertices];
        LinkedList<Integer>[] layers = new LinkedList[numVertices];

        for (int i = 0; i < numVertices; i++) {
            discovered[i] = false;
            parent[i] = -1;
        }

        discovered[s] = true;

        LinkedList<Integer> firstLayer = new LinkedList<>();
        firstLayer.add(s);
        layers[0] = firstLayer;
        int idx = 0;

        while (!layers[idx].isEmpty() && idx + 1 < layers.length) {
            layers[idx + 1] = new LinkedList<>();
            for (int vertex : layers[idx]) {
                Set<Integer> adjacentVertices = outNeighbors(vertex, edges);
                for (int neighbor : adjacentVertices) {
                    if (!discovered[neighbor]) {
                        discovered[neighbor] = true;
                        parent[neighbor] = vertex;
                        layers[idx + 1].add(neighbor);
                    }
                }
            }
            idx++;
        }

        return parent;
    }

    private static boolean pathExists(int src, int tgt, LinkedList<Integer> pathVertices,
                                      int[] parents) {
        pathVertices.add(tgt);

        int parent = parents[tgt];
        while (parent != src && parent != -1) {
            pathVertices.addFirst(parent);
            parent = parents[parent];
        }

        pathVertices.addFirst(src);

        if (parent == -1) {
            return false;
        }

        return true;
    }


}