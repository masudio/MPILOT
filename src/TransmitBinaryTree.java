import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by masudio on 4/30/16.
 */
public class TransmitBinaryTree {
    public static void main(String[] args) {
        Deque<String> dq = new ArrayDeque<>();
        dq.offer("1_null_null");
        dq.offer("2_L_1");
        dq.offer("4_R_2");
        dq.offer("3_R_1");

        TreeNode root = deser(dq);
    }

    private static TreeNode deser(Deque<String> dq) {
        TreeNode root = new TreeNode(Integer.valueOf(dq.poll().split("_")[0]));
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        return deser(dq, stack);
    }

    private static TreeNode deser(Deque<String> dq, Deque<TreeNode> stack) {
        if(dq.isEmpty()) {
            return stack.pop();
        }

        TreeNode result = stack.peek();

        while(!dq.isEmpty()) {
            String[] cur = dq.poll().split("_");
            Integer newVal = Integer.valueOf(cur[0]);
            boolean left = cur[1].equals("L");
            Integer parentVal = Integer.valueOf(cur[2]);
            TreeNode newNode = new TreeNode(newVal);

            while(stack.peek().val != parentVal) {
                stack.pop();
            }
            if(left) {
                stack.peek().left = newNode;
            } else {
                stack.peek().right = newNode;
            }

            stack.push(newNode);
        }

        return result;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
