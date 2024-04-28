import java.util.*;

/**
 * Returns a widest path between two vertices in an undirected graph. A widest path between two
 * vertices maximizes the weight of the minimum-weight edge in the path.
 * <p/>
 * There are multiple ways to solve this problem. The following algorithm may be helpful:
 * - Kruskal's algorithm using Union Find
 * You are NOT allowed to use Prim's
 * Feel free to use any previous algorithms that you have already implemented.
 */
public final class WidestPath {

    private static int[] parents;

    private static int[] rank;

    private static List<Edge> allEdges;

    private WidestPath() {}

    /**
     * Computes a widest path from {@param src} to {@param tgt} for an undirected graph.
     * If there are multiple widest paths, this method may return any one of them.
     * Input {@param g} guaranteed to be undirected.
     * Input {@param src} and {@param tgt} are guaranteed to be valid and in-bounds.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param g   the graph
     * @param src the vertex from which to start the search
     * @param tgt the vertex to find via {@code src}
     * @return an ordered list of vertices on a widest path from {@code src} to {@code tgt}, or an
     * empty list if there is no such path. The first element is {@code src} and the last
     * element is {@code tgt}. If {@code src == tgt}, a list containing just that element is
     * returned.
     * @implSpec This method should run in worst-case O((n + m) log n) time.
     */
    public static List<Integer> getWidestPath(Graph g, int src, int tgt) {
        parents = new int[g.getSize()];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
        }
        rank = new int[g.getSize()];
        Graph output = outputmaxST(g, src, tgt);
        int[] parents = BFS.run(output, src);
        LinkedList<Integer> result = new LinkedList<>();
        if (pathExists(src, tgt, result, parents)) {
            return result;
        } else {
            return new LinkedList<>();
        }
    }

    private static Graph outputmaxST(Graph g, int src, int tgt) {

        Graph result = new Graph(g.getSize());

        //put each vertex in its own set
        for (int i = 0; i < g.getSize(); i++) {
            parents[i] = i;
            rank[i] = 0;
        }

        allEdges = returnEdges(g);
        Collections.sort(allEdges, Collections.reverseOrder());

        for (Edge edge : allEdges) {
            if (find(edge.getFrom()) != find(edge.getTo())) {
                result.addEdge(edge.getFrom(), edge.getTo(), edge.getWeight());
                result.addEdge(edge.getTo(), edge.getFrom(), edge.getWeight());
                union(edge.getFrom(), edge.getTo());
            }
        }

        return result;
    }

    private static boolean pathExists(int src, int tgt, LinkedList<Integer> pathVertices,
                                      int[] parents) {
        pathVertices.add(tgt);

        if (tgt == src) {
            return true;
        }

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

    private static int find(int vtx) {
        if (vtx != parents[vtx]) {
            parents[vtx] = find(parents[vtx]);
        }
        return parents[vtx];
    }

    private static void union(int set1, int set2) {
        int bigParX = find(set1);
        int bigParY = find(set2);
        if (bigParX == bigParY) {
            return;
        }
        if (rank[bigParX] > rank[bigParY]) {
            parents[bigParY] = bigParX;
        } else {
            parents[bigParX] = bigParY;
            if (rank[bigParX] == rank[bigParY]) {
                rank[bigParY] ++;
            }
        }
    }

    private static List<Edge> returnEdges(Graph g) {
        List<Edge> allEdges = new LinkedList<>();
        //put edges in a set
        for (int i = 0; i < g.getSize(); i++) {
            Set<Integer> neighbors = g.outNeighbors(i);
            for (int neighbor : neighbors) {
                Edge e = new Edge(i, neighbor, g.getWeight(i, neighbor));
                allEdges.add(e);
            }
        }
        return allEdges;
    }

}
