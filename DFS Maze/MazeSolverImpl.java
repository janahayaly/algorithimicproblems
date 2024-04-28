public class MazeSolverImpl {

    /**
     * You should write your code within this method. A good rule of thumb, especially with
     * recursive problems like this, is to write your input exception handling within this
     * method and then use helper methods to carry out the actual recursion.
     * <p>
     * How you set up the recursive methods is up to you, but note that since this is a *static*
     * method, all helper methods that it calls must *also* be static. Make them package-private
     * (i.e. without private or public modifiers) so you can test them individually.
     *
     * @param maze See the writeup for more details about the format of the input maze.
     * @param sourceCoord The source (starting) coordinate
     * @param goalCoord The goal (ending) coordinate
     * @return a matrix of the same dimension as the input maze containing the solution
     * path marked with 1's, or null if no path exists. See the writeup for more details.
     * @throws IllegalArgumentException in the following instances:
     * 1. If the maze is null
     * 2. If m * n <= 1 where m and n are the dimensions of the maze
     * 3. If either the source coordinate OR the goal coordinate are out of the matrix bounds.
     * 4. If your source or goal coordinate are on a blocked tile.
     */
    public static int[][] solveMaze(int[][] maze, Coordinate sourceCoord, Coordinate goalCoord) {
        //handles all instances for illegal argument exceptions
        if (maze == null || maze.length <= 1 || maze[0].length <= 1 ||
                maze.length * maze[0].length <= 1 ||
                (sourceCoord.getX() < 0 || sourceCoord.getX() >= maze[0].length ||
                        sourceCoord.getY() < 0 || sourceCoord.getY() >= maze.length) ||
                (goalCoord.getX() < 0 || goalCoord.getX() >= maze[0].length ||
                        goalCoord.getY() < 0 || goalCoord.getY() >= maze.length) ||
                maze[sourceCoord.getY()][sourceCoord.getX()] == 1 ||
                maze[goalCoord.getY()][goalCoord.getX()] == 1
        )  {
            throw new IllegalArgumentException("One of the arguments presented is illegal.");
        }
        //creates variable for solution and calls helper
        int[][] solution = new int[maze.length][maze[0].length];
        if (solveMazeHelper(maze, sourceCoord ,goalCoord, sourceCoord, solution)) {
            solution[sourceCoord.getY()][sourceCoord.getX()] = 1;
            return solution;
        } else {
            return null;
        }
    }

    public static boolean solveMazeHelper(int[][] maze, Coordinate sourceCoord,
                                          Coordinate goalCoord, Coordinate currCoord,
                                          int[][] solution) {
        //base cases
        if (currCoord.getY() < 0 || currCoord.getY() >= maze.length ||
                currCoord.getX() < 0 || currCoord.getX() >= maze.length) {
            return false;
        } else if (maze[currCoord.getY()][currCoord.getX()] == 1) {
            return false;
        } else if (currCoord.equals(goalCoord)) {
            return true;
        } else {
            //recursive call in order of down -> up -> left -> right
            Coordinate currCoordNew = new Coordinate(currCoord.getX(), currCoord.getY() + 1);
            maze[currCoord.getY()][currCoord.getX()] = 1;
            if (solveMazeHelper(maze, sourceCoord, goalCoord, currCoordNew, solution)) {
                solution[currCoordNew.getY()][currCoordNew.getX()] = 1;
                return true;
            }
            currCoordNew = new Coordinate(currCoord.getX(), currCoord.getY() - 1);
            maze[currCoord.getY()][currCoord.getX()] = 1;
            if (solveMazeHelper(maze, sourceCoord, goalCoord, currCoordNew, solution)) {
                solution[currCoordNew.getY()][currCoordNew.getX()] = 1;
                return true;
            }
            currCoordNew = new Coordinate(currCoord.getX() - 1, currCoord.getY());
            maze[currCoord.getY()][currCoord.getX()] = 1;
            if (solveMazeHelper(maze, sourceCoord, goalCoord, currCoordNew, solution)) {
                solution[currCoordNew.getY()][currCoordNew.getX()] = 1;
                return true;
            }
            currCoordNew = new Coordinate(currCoord.getX() + 1, currCoord.getY());
            maze[currCoord.getY()][currCoord.getX()] = 1;
            if (solveMazeHelper(maze, sourceCoord, goalCoord, currCoordNew, solution)) {
                solution[currCoordNew.getY()][currCoordNew.getX()] = 1;
                return true;
            }
        }
        //if no solution is found...
        return false;
    }
}
