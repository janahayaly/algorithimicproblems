import java.util.*;

/**
 * <h1>QUESTION 1: FLIGHT SCHEDULES</h1>
 * <p/>
 *
 * Class to implement the algorithm to determine a possible flight schedule given the conditions.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question1 {

    /**
     * Given the number of flights to schedule and conditions for departure, returns one possible
     * departure schedule.
     *
     * @param numFlights the number of flights waiting to take off
     * @param conditions the list of condition pairs (no duplicate pairs may exist)
     * @return one possible valid flight departure ordering, or an empty list if none exists
     * 
     * @implSpec you may assume that input is valid (well formatted, mx2 2d-array) and non-null.
     * Do not throw any exceptions.
     */
    public static List<Integer> getFlightDepartureSchedule(int numFlights, int[][] conditions) {

        HashSet<Integer>[] edges = new HashSet[numFlights];

        for (int i = 0; i < edges.length; i++) {
            edges[i] = new HashSet<>();
        }

        for (int[] cond : conditions) {
            addEdge(cond[0], cond[1], edges);
        }

        //TOPO SORT QUESTION
        Queue<Integer> l = new LinkedList<>();
        LinkedList<Integer> out = new LinkedList<>();
        int[] in = new int[numFlights];

        // compute in - degree of each node
        for (int i = 0; i < numFlights; i++) {
            Set<Integer> neighbors = outNeighbors(i, edges);
            for (int neighbor : neighbors) {
                in[neighbor] ++;
            }
        }

        // initially populate L
        for (int i = 0; i < numFlights; i++) {
            if (in[i] == 0) {
                l.add(i);
            }
        }

        while (!l.isEmpty()) {
            int vertex = l.remove();
            out.addFirst(vertex);
            Set<Integer> neighbors = outNeighbors(vertex, edges);
            for (int neighb : neighbors) {
                in[neighb]--;
                if (in[neighb] == 0) {
                    l.add(neighb);
                }
            }
        }

        if (out.size() <= 1 && numFlights > 1) {
            return new LinkedList<>();
        }

        return out;
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

}
