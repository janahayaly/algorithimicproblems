import java.util.LinkedList;
import java.util.List;

final public class MazeSolver {

    private MazeSolver() {}

    /**
     * Returns a list of coordinates on the shortest path from {@code src} to {@code tgt}
     * by executing a breadth-first search on the graph represented by a 2D-array of size m x n.
     * Please note, you MUST use your ResizingDeque implementation as the BFS queue for this method.
     * <p>
     * Input {@code maze} guaranteed to be a non-empty and valid matrix.
     * Input {@code src} and {@code tgt} are guaranteed to be valid, in-bounds, and not blocked.
     * <p>
     * Do NOT modify this method header.
     *
     * @param maze the input maze, which is a 2D-array of size m x n
     * @param src The starting Coordinate of the path on the matrix
     * @param tgt The target Coordinate of the path on the matrix
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     * @implSpec This method should run in worst-case O(m x n) time.
     */
    public static List<Coordinate> getShortestPath(int[][] maze, Coordinate src, Coordinate tgt) {
        if (maze == null || (src.getX() < 0 || src.getX() >= maze.length ||
                        src.getY() < 0 || src.getY() >= maze.length) ||
                (tgt.getX() < 0 || tgt.getX() >= maze[0].length ||
                        tgt.getY() < 0 || tgt.getY() >= maze.length) ||
                maze[src.getY()][src.getX()] == 1 ||
                maze[tgt.getY()][tgt.getX()] == 1
        )  {
            throw new IllegalArgumentException("One of the arguments presented is illegal.");
        }
        return getShortestPathHelper(maze, src, tgt);

    }
    private static List<Coordinate> getShortestPathHelper(int[][] maze,
                                                          Coordinate src, Coordinate tgt) {

        if (src == tgt) {
            List<Coordinate> res = new LinkedList<>();
            res.add(tgt);
            return res;
        }

        LinkedList<Coordinate> pathVertices = new LinkedList<>();
        Graph g = new Graph(maze.length * maze[0].length);
        g = fillEdges(maze, g);
        int srcNumb = src.getY() * maze[0].length + src.getX();
        int[] parents = BFS.run(g, srcNumb);
        int tgtNumb = tgt.getY() * maze[0].length + tgt.getX();
        pathVertices.add(new Coordinate(tgtNumb % maze[0].length, tgtNumb / maze.length));

        int parentNumb = parents[tgtNumb];
        while (parentNumb != srcNumb && parentNumb != -1) {
            Coordinate coord = new Coordinate(parentNumb % maze[0].length,
                    parentNumb / maze.length);
            pathVertices.addFirst(coord);
            parentNumb = parents[parentNumb];
        }

        pathVertices.addFirst(new Coordinate(srcNumb % maze[0].length, srcNumb / maze.length));

        if (parentNumb == -1) {
            return new LinkedList<>();
        }

        return pathVertices;
    }

    private static Graph fillEdges(int[][] maze, Graph g) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                int og = i * maze[0].length + j;
                if (maze[i][j] == 0) {
                    int top = (i - 1) * maze[0].length + j;
                    int bottom = (i + 1) * maze[0].length + j;
                    int right = i * maze[0].length + (j + 1);
                    int left = i * maze[0].length + (j - 1);
                    if (i - 1 >= 0) {
                        if (maze[i - 1][j] == 0) {
                            g.addEdge(og, top, 0);
                        }
                    }
                    if (i + 1 < maze.length) {
                        if (maze[i + 1][j] == 0) {
                            g.addEdge(og, bottom, 0);
                        }
                    }
                    if (j - 1 >= 0) {
                        if (maze[i][j - 1] == 0) {
                            g.addEdge(og, left, 0);
                        }
                    }
                    if (j + 1 < maze.length) {
                        if (maze[i][j + 1] == 0) {
                            g.addEdge(og, right, 0);
                        }
                    }
                }
            }
        }
        return g;
    }

}