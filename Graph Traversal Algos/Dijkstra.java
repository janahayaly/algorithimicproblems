import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Provides access to Dijkstra's algorithm for a weighted graph.
 */
final public class Dijkstra {

    private static int[] distances;

    private static int[] parents;

    private Dijkstra() {
    }

    /**
     * Computes the shortest path between two nodes in a weighted graph.
     * Input graph is guaranteed to be valid and have no negative-weighted edges.
     *
     * @param g   the weighted graph to compute the shortest path on
     * @param src the source node
     * @param tgt the target node
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     */

    public static List<Integer> getShortestPath(Graph g, int src, int tgt) {

        if (src == tgt) {
            LinkedList<Integer> result = new LinkedList<>();
            result.add(tgt);
            return result;
        }

        distances = new int[g.getSize()];
        parents = new int[g.getSize()];
        LinkedList<Integer> path = new LinkedList<>();

        for (int i = 0; i < g.getSize(); i++) {
            distances[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }

        distances[src] = 0;

        Set<Integer> checked = new HashSet<>();
        BinaryMinHeapImpl<Integer, Integer> distMapping = new BinaryMinHeapImpl<>();
        for (int i = 0; i < g.getSize(); i++) {
            distMapping.add(distances[i], i);
        }

        while (!distMapping.isEmpty()) {

            int minVert  = distMapping.extractMin().value;
            checked.add(minVert);
            Set<Integer> adjacentVertices = g.outNeighbors(minVert);

            for (int neighbor : adjacentVertices) {
                if (distMapping.containsValue(neighbor) &&
                        distances[neighbor] > distances[minVert] + g.getWeight(minVert, neighbor)) {
                    distances[neighbor] = distances[minVert] + g.getWeight(minVert, neighbor);
                    distMapping.decreaseKey(neighbor, distances[neighbor]);
                    parents[neighbor] = minVert;
                }
            }
        }

        if (pathExists(src, tgt, path, parents)) {
            return path;
        } else {
            return new LinkedList<>();
        }
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
