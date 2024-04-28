import java.util.*;

/**
 * <h1>QUESTION 2: ROOMMATE MATCHING</h1>
 * <p/>
 *
 * Class to implement the algorithm to select roommates from a 2d-array of pairs.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question2 {

    /**
     * Returns the set of roommates who mutually requested each other.
     *
     * @param requests the 2d-array of pairs of requests of the form (student, requested roommate).
     *                 Note: a student cannot request themself. 
     * @return a set of pairs of roommates who mutually requested each other
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static Set<Pair<String, String>> findMatches(String[][] requests) {
        HashSet<Pair<String, String>> pairs = new HashSet<>();
        //FOR EACH ROW, for ARR[0], look up ARR[1] using binary search and if found add to hash set
        //sort requests first
        List<String> l = new ArrayList<String>();
        for (String[] req : requests) {
            String pair = req[0] + "-" + req[1];
            l.add(pair);
        }
        Collections.sort(l);
        String[] sortedArr = new String[l.size()];
        int idx = 0;
        for (String pair : l) {
            sortedArr[idx] = pair;
            idx ++;
        }
        List<String> checked = new LinkedList<>();

        for (int i = 0; i < requests.length; i++) {
            String ogRoommate = requests[i][0];
            String request = requests[i][1];
            if (binarySearch(sortedArr, request, ogRoommate)) {
                Pair<String, String> roommates = new Pair<>(ogRoommate, request);
                if (!checked.contains(request) && !ogRoommate.equals(request)) {
                    pairs.add(roommates);
                }
                checked.add(ogRoommate);
            }
        }

        return pairs;
    }

    private static boolean binarySearch(String[] arr, String request, String og) {
        if (arr.length == 0) {
            return false;
        } else if (arr.length == 1) {
            if (arr[0].contains(request)) {
                return arr[0].contains("-" + og);
            } else {
                return false;
            }
        } else {
            int mid = (int) (arr.length) / 2;
            if (arr[mid].contains(request)) {
                return arr[mid].contains("-" + og);
            } else if (arr[mid].compareTo(request) < 0) {
                String[] upper = new String[arr.length - (mid + 1)];
                int idx = 0;
                for (int i = mid + 1; i < arr.length; i++) {
                    upper[idx] = arr[i];
                    idx++;
                }
                return binarySearch(upper, request, og);
            } else {
                String[] lower = new String[mid - 1];
                int idx = 0;
                for (int i = 0; i < mid - 1; i++) {
                    lower[idx] = arr[i];
                    idx++;
                }
                return binarySearch(lower, request, og);
            }
        }
    }

}
