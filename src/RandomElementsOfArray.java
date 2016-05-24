import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by masudio on 4/30/16.
 */
public class RandomElementsOfArray {
    public static void main(String[] args) {
        List<Integer> in = new ArrayList<>();
        in.add(0);
        in.add(1);
        in.add(2);
        in.add(3);
        in.add(4);
        in.add(5);
        in.add(6);
        in.add(7);
        in.add(8);
        in.add(9);

        List<Integer> result = getRandom(in, 5);
        for (Integer integer : result) {
            System.out.println(integer);
        }
    }

    private static List<Integer> getRandom(List<Integer> in, int n) {
        if(n > in.size()) {
            throw new RuntimeException("no!");
        }

        List<Integer> result = new LinkedList<>();
        while(n > 0) {
            int rand = (int)Math.floor(Math.random() * in.size());
            result.add(in.get(rand));
            in.remove(rand);
            n--;
        }

        return result;
    }
}
