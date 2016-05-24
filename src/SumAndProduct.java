import java.util.*;

/**
 * Created by masudio on 4/20/16.
 */
public class SumAndProduct {
    public static int integerBreak(int n) {
        Map<Integer, Set<List<Integer>>> intToSetsMap = new HashMap<>();
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> curSet = new ArrayList<>();
        curSet.add(1);
        result.add(curSet);
        intToSetsMap.put(1, result);
        Set<List<Integer>> sets = integerBreak(n, intToSetsMap);

        int max = 0;
        for(List<Integer> list : sets) {
            if(2 <= list.size()) {
                int product = productOf(list);
                if(product > max) {
                    max = product;
                }
            }
        }

        return max;
    }

    private static Set<List<Integer>> integerBreak(int n, Map<Integer, Set<List<Integer>>> intToSetsMap) {
        Set<List<Integer>> result = intToSetsMap.get(n);
        if(null != result) {
            return result;
        }

        result = new HashSet<>();
        List<Integer> tempList = new ArrayList<>();
        tempList.add(n);
        result.add(tempList);

        Set<List<Integer>> curSet;
        for(int i = 1; i < n; i++) {
            curSet = integerBreak(i, intToSetsMap);
            for(List<Integer> list : curSet) {
                tempList = new ArrayList<>(list);
                tempList.add(n - i);
                result.add(tempList);
            }
        }

        intToSetsMap.put(n, result);
        return result;
    }

    private static int productOf(List<Integer> list) {
        int result = 1;
        for(Integer i : list) {
            result *= i;
        }

        return result;
    }

    public static void main(String[] args) {
        integerBreak(10);
    }
}
