import java.util.*;

final public class IslandBridge {


    // right runtime ?????

    private IslandBridge() {}

    /**
     * See question details in the write-up. Input is guaranteed to be valid.
     *
     * @param g the input graph representing the islands as vertices and bridges as directed edges
     * @param x the starting node
     * @return true, if no matter how you navigate through the one-way bridges from node x,
     * there is always a way to drive back to node x, and false otherwise.
     * @implSpec This method should run in worst-case O(n + m) time.
     */
    public static boolean allNavigable(Graph g, int x) {

        int[] parents = BFS.run(g, x);
        boolean[] checked = new boolean[g.getSize()];

        for (boolean slot : checked) {
            slot = false;
        }

        ResizingDequeImpl<Integer> uncheckedVert = updateUnchecked(checked);

        while (!checkedAll(checked)) {
            int v = uncheckedVert.pollLast();
            List<Integer> pathFromX = new LinkedList<>();
            if (pathExists(x, v, pathFromX, parents)) {
                int[] tgtParents = BFS.run(g, v);
                List<Integer> pathFromV = new LinkedList<>();
                if (pathExists(v, x, pathFromV, tgtParents)) {
                    checked[v] = true;
                    for (int pathVertex : pathFromV) {
                        checked[pathVertex] = true;
                    }
                } else {
                    return false;
                }
            } else {
                checked[v] = true;
            }
            uncheckedVert = updateUnchecked(checked);
        }

        return true;
    }

    private static boolean pathExists(int src, int tgt, List<Integer> pathVertices, int[] parents) {
        pathVertices.add(tgt);

        int parent = parents[tgt];
        while (parent != src && parent != -1) {
            pathVertices.add(parent);
            parent = parents[parent];
        }

        pathVertices.add(src);

        if (parent == -1) {
            return false;
        }

        return true;
    }

    private static boolean checkedAll(boolean[] checked) {
        for (boolean check : checked) {
            if (!check) {
                return false;
            }
        }
        return true;
    }

    private static ResizingDequeImpl<Integer> updateUnchecked(boolean[] checked) {
        ResizingDequeImpl<Integer> unchecked = new ResizingDequeImpl<>();
        for (int i = 0; i < checked.length; i++) {
            if (!checked[i]) {
                unchecked.addLast(i);
            }
        }
        return unchecked;
    }

}
