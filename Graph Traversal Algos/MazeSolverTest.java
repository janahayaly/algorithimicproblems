import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class MazeSolverTest {

    @Test
    public void testGetShortestPath() {
        int[][] maze = {
                {0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0}
        };
        Coordinate src = new Coordinate(0, 0);
        Coordinate tgt = new Coordinate(4, 4);

        List<Coordinate> expectedPath = new LinkedList<>();
        expectedPath.add(new Coordinate(0, 0));
        expectedPath.add(new Coordinate(1, 0));
        expectedPath.add(new Coordinate(2, 0));
        expectedPath.add(new Coordinate(3, 0));
        expectedPath.add(new Coordinate(3, 1));
        expectedPath.add(new Coordinate(3, 2));
        expectedPath.add(new Coordinate(4, 2));
        expectedPath.add(new Coordinate(4, 3));
        expectedPath.add(new Coordinate(4, 4));

        List<Coordinate> shortestPath = MazeSolver.getShortestPath(maze, src, tgt);
        assertEquals(expectedPath, shortestPath);

        int[][] noPathMaze = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        };
        Coordinate src2 = new Coordinate(0, 0);
        Coordinate tgt2 = new Coordinate(2, 2);

        List<Coordinate> noPath = MazeSolver.getShortestPath(noPathMaze, src2, tgt2);
        assertTrue(noPath.isEmpty());
    }


    @Test
    public void testGetShortestPathStraightLine() {
        int[][] maze1 = {
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };
        Coordinate src1 = new Coordinate(0, 0);
        Coordinate tgt1 = new Coordinate(4, 0);

        List<Coordinate> expectedPath1 = new LinkedList<>();
        for (int x = 0; x <= 4; x++) {
            expectedPath1.add(new Coordinate(x, 0));
        }

        List<Coordinate> shortestPath1 = MazeSolver.getShortestPath(maze1, src1, tgt1);
        assertEquals(expectedPath1, shortestPath1);
    }

    @Test
    public void testGetShortestPathAlongBorder() {
        int[][] maze2 = {
                {0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0}
        };
        Coordinate src2 = new Coordinate(0, 4);
        Coordinate tgt2 = new Coordinate(4, 0);

        List<Coordinate> expectedPath2 = new LinkedList<>();
        expectedPath2.add(new Coordinate(0, 4));
        expectedPath2.add(new Coordinate(1, 4));
        expectedPath2.add(new Coordinate(2, 4));
        expectedPath2.add(new Coordinate(3, 4));
        expectedPath2.add(new Coordinate(4, 4));
        expectedPath2.add(new Coordinate(4, 3));
        expectedPath2.add(new Coordinate(4, 2));
        expectedPath2.add(new Coordinate(4, 1));
        expectedPath2.add(new Coordinate(4, 0));

        List<Coordinate> shortestPath2 = MazeSolver.getShortestPath(maze2, src2, tgt2);
        assertEquals(expectedPath2, shortestPath2);
    }

    @Test
    public void testGetShortestPathRandom() {
        int[][] maze3 = {
                {0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        Coordinate src3 = new Coordinate(0, 4);
        Coordinate tgt3 = new Coordinate(4, 2);

        List<Coordinate> expectedPath3 = new LinkedList<>();
        expectedPath3.add(new Coordinate(0, 4));
        expectedPath3.add(new Coordinate(1, 4));
        expectedPath3.add(new Coordinate(2, 4));
        expectedPath3.add(new Coordinate(2, 3));
        expectedPath3.add(new Coordinate(2, 2));
        expectedPath3.add(new Coordinate(3, 2));
        expectedPath3.add(new Coordinate(4, 2));

        List<Coordinate> shortestPath3 = MazeSolver.getShortestPath(maze3, src3, tgt3);
        assertEquals(expectedPath3, shortestPath3);
    }

    @Test
    public void testGetShortestPathMultValid() {
        // Maze with multiple valid paths from tgt to src (different lengths).
        int[][] maze1 = {
                {0, 0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        Coordinate src1 = new Coordinate(0, 0);
        Coordinate tgt1 = new Coordinate(4, 4);

        List<Coordinate> expectedPath = new LinkedList<>();
        expectedPath.add(new Coordinate(0, 0));
        expectedPath.add(new Coordinate(1, 0));
        expectedPath.add(new Coordinate(2, 0));
        expectedPath.add(new Coordinate(2, 1));
        expectedPath.add(new Coordinate(2, 2));
        expectedPath.add(new Coordinate(2, 3));
        expectedPath.add(new Coordinate(3, 3));
        expectedPath.add(new Coordinate(4, 3));
        expectedPath.add(new Coordinate(4, 4));

        List<Coordinate> shortestPath1 = MazeSolver.getShortestPath(maze1, src1, tgt1);
        assertEquals(expectedPath, shortestPath1);
    }

    @Test
    public void testGetShortestPathNoPath() {
        // Maze with multiple valid paths from tgt to src (different lengths).
        int[][] maze1 = {
                {0, 0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {1, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        Coordinate src1 = new Coordinate(1, 0);
        Coordinate tgt1 = new Coordinate(3, 3);

        List<Coordinate> expectedPath = new LinkedList<>();

        List<Coordinate> shortestPath1 = MazeSolver.getShortestPath(maze1, src1, tgt1);
        assertEquals(expectedPath, shortestPath1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSrcOOB() {

        int[][] legalMaze = {
                {0, 1},
                {1, 0}
        };
        Coordinate tgt = new Coordinate(1, 1);

        MazeSolver.getShortestPath(legalMaze, new Coordinate(2, 2), tgt);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInvalidTgt() {

        int[][] legalMaze = {
                {0, 1},
                {1, 0}
        };
        Coordinate src = new Coordinate(0, 0);

        MazeSolver.getShortestPath(legalMaze, src, new Coordinate(1, 0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullMaze() {
        Coordinate src = new Coordinate(0, 0);
        Coordinate tgt = new Coordinate(1, 1);

        MazeSolver.getShortestPath(null, src, tgt);
    }


    @Test
    public void testPathofOne() {

        int[][] maze = {
                {0, 1},
                {1, 0}
        };
        Coordinate src = new Coordinate(0, 0);
        LinkedList<Coordinate> exp = new LinkedList<>();
        exp.add(src);

        assertEquals(exp, MazeSolver.getShortestPath(maze, src, src));
    }

    @Test
    public void testSingleton() {

        int[][] maze = {
                {0}
        };
        Coordinate src = new Coordinate(0, 0);
        LinkedList<Coordinate> exp = new LinkedList<>();
        exp.add(src);

        assertEquals(exp, MazeSolver.getShortestPath(maze, src, src));
    }

}
