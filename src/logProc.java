import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by masudio on 4/9/16.
 */
public class logProc {

    /*
case: every line occurs same # times
case: q1
case: 0%
case: no exact sum
*/
    public Set<String> getTop(int percent, List<String> logs) {
        //validate input

        int numQueries = logs.size() * (percent / 100);
        if(0 == numQueries) {
            return new HashSet<>();
        }

        Map<String, Long> map = logs.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        PriorityQueue<Map.Entry<String, Long>> pq = new PriorityQueue<>(
                (e1,e2) -> e1.getValue() < e2.getValue() ? -1 : e1.getValue() == e2.getValue() ? 0 : 1
        );

        int count = 0;
        for(Map.Entry<String, Long> entry : map.entrySet()) {
            if(count < numQueries) {
                count += entry.getValue();
                pq.add(entry);
            } else {
                if(pq.peek().getValue() < entry.getValue()) {
                    while((count - pq.peek().getValue()) + entry.getValue() >= numQueries) {
                        count -= pq.poll().getValue();
                    }
                    pq.add(entry);
                }
            }
        }

        return pq.stream().map(e -> e.getKey()).collect(Collectors.toSet());
    }



    /*
    [5,4,6,3,7,2]

    [1,1,2,1,3,1]
     */
    public static int lis(int[] A) {
        //val

        int max = 1;
        int[] liseach = new int[A.length];
        Arrays.fill(liseach, 1);

        for(int i = 1; i < A.length; i++) {
            for(int j = 0; j < i; j++) {
                if(A[j] < A[i]) {
                    liseach[i] = Math.max(liseach[i], liseach[j] + 1);
                }
            }
            max = Math.max(max, liseach[i]);
        }

        return max;
    }

    public static int lisnlogn(int[] A) {
        //val

        int longestLengthMinus1 = 0;
        int[] idxOfSorted = new int[A.length];
        idxOfSorted[0] = 0;

        for(int i = 1; i < A.length; i++) {
            if(A[idxOfSorted[longestLengthMinus1]] < A[i]) {
                longestLengthMinus1++;
                idxOfSorted[longestLengthMinus1] = i;
            } else {
                replaceCeil(A, idxOfSorted, 0, longestLengthMinus1 + 1, -1, i);
            }
        }

        return longestLengthMinus1 + 1;
    }

    public static void replaceCeil(int[] A, int[] idxOfSorted, int startIdx, int endIdx, int candidateIdx, int toInsert) {
        if(startIdx == endIdx) {
            idxOfSorted[candidateIdx] = toInsert;
            return;
        }

        int nodeIdx = ((endIdx - startIdx) / 2) + startIdx;
        if(A[toInsert] < A[idxOfSorted[nodeIdx]]) {
            candidateIdx = (-1 != candidateIdx && A[idxOfSorted[candidateIdx]] < A[idxOfSorted[nodeIdx]]) ? candidateIdx : nodeIdx;
            replaceCeil(A, idxOfSorted, startIdx, nodeIdx, candidateIdx, toInsert); // go left
        } else if(A[toInsert] > A[idxOfSorted[nodeIdx]]) {
            replaceCeil(A, idxOfSorted, nodeIdx + 1, endIdx, candidateIdx, toInsert);
        }
    }

    public static void main(String[] args) {
        System.out.println("result: " + lisnlogn(new int[]{3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10}));
        System.out.println("result: " + lis(new int[]{3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10}));
    }
}
