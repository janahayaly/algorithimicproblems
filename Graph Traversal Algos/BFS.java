import java.util.LinkedList;
import java.util.Set;

public class BFS {

    private static boolean[] discovered;

    private static LinkedList<Integer>[] layers;

    private static int[] parent;

    public static int[] run(Graph g, int s) {

        int numVertices = g.getSize();
        discovered = new boolean[numVertices];
        parent = new int[numVertices];
        layers = new LinkedList[numVertices];

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
                Set<Integer> adjacentVertices = g.outNeighbors(vertex);
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


}