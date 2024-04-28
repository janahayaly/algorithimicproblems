import java.util.List;

/**
 * <h1>QUESTION 5: ODD SIZED FAMILY TREES</h1>
 * <p/>
 *
 * Class to implement the algorithm to determine the number of odd sized sub-trees in a given
 * family tree.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question5 {

    /**
     * Returns the number of odd sized sub-trees in a given family tree.
     *
     * @param family an adjacency list of the family tree
     * @param root the favorite number of the root of the family tree
     * @return the number of odd sized sub-trees
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static int getNumberOfOddSubtrees(List<List<Integer>> family, int root) {
        // recursive structure which iterates through tree and counts children
        int[] numSubtrees = new int[family.size()];
        // terminates when a leaf is reached and returns 1
        for (int i = 0; i < numSubtrees.length; i++) {
            int subtrees = traverseFamily(family, family.get(i));
            numSubtrees[i] = subtrees;
        }
        // backtracks and adds 1 for each leaf, stores how many in an array
        // goes through array and counts odd children
        int numOdds = 0;
        for (int num : numSubtrees) {
            if (num % 2 == 1) {
                numOdds ++;
            }
        }

        return numOdds;
    }

    private static int traverseFamily(List<List<Integer>> family, List<Integer> children) {
        if (children.isEmpty()) {
            return 1;
        } else {
            int sub = 1;
            for (int child : children) {
                sub += traverseFamily(family, family.get(child));
            }
            return sub;
        }
    }

}