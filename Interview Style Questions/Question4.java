import java.util.*;

/**
 * <h1>QUESTION 4: AIRPORT CODE GAME -- OFF BY ONE!</h1>
 * <p/>
 *
 * Class to implement the algorithm to find the smallest chain between two 3 letter
 * airport codes.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question4 {

    /**
     * Solves the "Airport Code Change" game. See complete problem description above.
     * <p/>
     * 
     * You may assume that all codes contain only UPPERCASE alphabetic (A-Z) characters.
     *
     * @param code1 the 3 letter airport code you want to start from
     * @param code2 the 3 letter airport code you want to end at
     * @param codes the set of 3 letter airport codes you can use to get from code1 -> code2
     * @return the smallest chain of airport codes to get from {@code code1 -> code2}. {@code code1}
     *         should be the first element, and {@code code2} should be the last. If no valid
     *         solution exists, return {@code null}. If {@code code1.equals(code2)}, return an empty
     *         list.
     *
     * @implSpec you may assume that all inputs are valid. Do not throw any exceptions.
     */
    public static List<String> getSmallestChain(String code1, String code2, Set<String> codes) {
        HashMap<String, Integer> codeMap = new HashMap<>();
        String[] codeArr = new String[codes.size()];
        int idx = 0;
        for (String code : codes) {
            codeMap.put(code, idx);
            idx++;
        }
        Set<String> keys = codeMap.keySet();
        for (String key : keys) {
            codeArr[codeMap.get(key)] = key;
        }

        // construct graph w edge from one code to another if they are 1 letter apart
        // how to do this in less than n^2
        HashSet<Integer>[] edges = new HashSet[codeMap.size()];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new HashSet<>();
        }

        for (String codeA : codes) {
            for (String codeB : codes) {
                if (!codeA.equals(codeB)) {
                    int differences = 3;
                    for (int i = 0; i < 3; i++) {
                        if (codeA.charAt(i) == codeB.charAt(i)) {
                            differences --;
                        }
                    }
                    if (differences == 1) {
                        addEdge(codeMap.get(codeA), codeMap.get(codeB), edges);
                        addEdge(codeMap.get(codeB), codeMap.get(codeA), edges);
                    }
                }
            }
        }
        // run bfs from source

        int[] parents = bfs(edges, codeMap.get(code1));

        // backtrack from target
        LinkedList<Integer> path = new LinkedList<>();
        LinkedList<String> pathInCodes = new LinkedList<>();
        if (pathExists(codeMap.get(code1), codeMap.get(code2), path, parents)) {
            for (int v : path) {
                pathInCodes.add(codeArr[v]);
            }
            return pathInCodes;
        }

        // denote codes by their position in a codes array
        return new LinkedList<>();
    }

    private static boolean addEdge(int u, int v, HashSet<Integer>[] edges) {
        if (u == v) {
            throw new IllegalArgumentException("u and v are same vertex");
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