import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MazeSolverImplTest {

    private int[][] smallWriteupMaze;
    private int[][] bigWriteupMaze;

    @Before
    public void setupTestMazes() {
        smallWriteupMaze = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        bigWriteupMaze = new int[][]{
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

    }

    @Test
    public void testReturnsSmallMazeSolutionPathInWriteup() {
        int[][] solutionPath = {
                {1, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 0, 0}
        };
        assertArrayEquals(solutionPath, MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 0), new Coordinate(0, 2)));
    }

    @Test
    public void testReturnsBigMazeSolutionPathInWriteup() {
        int[][] bigWriteupSolution = new int[][]{
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] returnedPath = MazeSolverImpl.solveMaze(bigWriteupMaze, new Coordinate(2, 0),
                new Coordinate(4, 0));
        assertArrayEquals(bigWriteupSolution, returnedPath);
    }

    /*
      Note: the above tests are the ones included in the writeup and NOT exhaustive. The autograder
      uses other test cases not listed above. Please thoroughly read all stub files, including
      CoordinateTest.java!

      For help with creating test cases, please see this link:
      https://www.seas.upenn.edu/~cis121/current/testing_guide.html
     */

    @Test
    public void testDeadEndMaze() {

        int[][] deadEndMaze = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 0}
        };


        int[][] deadEndSolution = new int[][]{
                {1, 1, 1, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0}
        };
        int[][] returnedPath = MazeSolverImpl.solveMaze(deadEndMaze, new Coordinate(0, 0),
                new Coordinate(3, 4));
        assertArrayEquals(deadEndSolution, returnedPath);
    }

    @Test
    public void testBorderHeavyMaze() {
        int[][] borderHeavyMaze = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 1, 1, 0, 0},
                {1, 1, 0, 1, 1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        int[][] borderHeavySolution = new int[][]{
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 0, 1, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0}
        };
        int[][] returnedPath = MazeSolverImpl.solveMaze(borderHeavyMaze, new Coordinate(2, 2),
                new Coordinate(8, 8));
        assertArrayEquals(borderHeavySolution, returnedPath);
    }

    @Test
    public void testNoSolutionMaze() {
        int[][] noSolutionMaze = new int[][]{
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 0}
        };

        int[][] returnedPath = MazeSolverImpl.solveMaze(noSolutionMaze, new Coordinate(1, 1),
                new Coordinate(5, 4));
        assertArrayEquals(null, returnedPath);
    }

    @Test (expected = IllegalArgumentException.class)
    public void mazeIsNull() {

        MazeSolverImpl.solveMaze(null, new Coordinate(1, 1),
                new Coordinate(5, 4));

    }

    @Test (expected = IllegalArgumentException.class)
    public void mazeIsEmpty() {
        int[][] emptyMaze = new int[0][0];

        MazeSolverImpl.solveMaze(emptyMaze, new Coordinate(1, 1),
                        new Coordinate(5, 4));

    }

    @Test (expected = IllegalArgumentException.class)
    public void goalIsBlocked() {
        int[][] noSolutionMaze = new int[][]{
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 0}
        };

        MazeSolverImpl.solveMaze(noSolutionMaze, new Coordinate(1, 1),
                        new Coordinate(5, 5));
    }

    @Test (expected = IllegalArgumentException.class)
    public void sourceIsBlocked() {
        int[][] noSolutionMaze = new int[][]{
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 0}
        };

        MazeSolverImpl.solveMaze(noSolutionMaze, new Coordinate(2, 1),
                        new Coordinate(5, 4));
    }

    @Test (expected = IllegalArgumentException.class)
    public void goalIsOOB() {
        int[][] noSolutionMaze = new int[][]{
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 0}
        };

        MazeSolverImpl.solveMaze(noSolutionMaze, new Coordinate(1, 1),
                        new Coordinate(5, 9));
    }

    @Test (expected = IllegalArgumentException.class)
    public void sourceIsOOB() {
        int[][] noSolutionMaze = new int[][]{
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 0}
        };

        MazeSolverImpl.solveMaze(noSolutionMaze, new Coordinate(1, 1),
                        new Coordinate(10, 4));
    }

}
