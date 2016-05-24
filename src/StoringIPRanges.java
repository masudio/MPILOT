/**
 * Created by masudio on 5/2/16.
 */
public class StoringIPRanges {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, 8);
        TreeNode leftChild = new TreeNode(1, 3);
        TreeNode leftLeftChild = new TreeNode(0, 0);
        TreeNode leftRightChild = new TreeNode(4, 4);
        TreeNode rightChild = new TreeNode(10, 12);

        root.left = leftChild;
        leftChild.left = leftLeftChild;
        leftChild.right = leftRightChild;
        root.right = rightChild;

        remove(root, 2);
        System.out.println();
    }

    public static TreeNode remove(TreeNode root, int target) {
        if(null == root) {
            throw new RuntimeException("value to remove not found");
        } else if(root.start == root.end && root.start == target) {
            return deleteNode(root);
        } else if(root.start == target) {
            root.start += 1;
        } else if(root.end == target) {
            root.end -= 1;
        } else if(root.start < target && root.end > target) {
            TreeNode newRight = new TreeNode(target + 1, root.end);
            TreeNode newLeft = new TreeNode(root.start, target - 1);
            newLeft.left = root.left;
            newRight.left = newLeft;
            newRight.right = root.right;
            return newRight;
        } else if(target < root.start) {
            root.left = remove(root.left, target);
        } else if(target > root.end) {
            root.right = remove(root.right, target);
        }

        return root;
    }

    public static TreeNode deleteNode(TreeNode deleted) {
        if(null == deleted.left && null == deleted.right) {
            return null;
        } else if(null != deleted.left && null == deleted.left.right) {
            return deleted.left;
        } else if(null != deleted.right && null == deleted.right.left) {
            return deleted.right;
        } else {
            TreeNode parentOfRightMost = parentOfRightMost(deleted.left);
            parentOfRightMost.right = parentOfRightMost.right.left;
            parentOfRightMost.right.left = deleted.left;
            parentOfRightMost.right.right = deleted.right;
            return parentOfRightMost.right;
        }
    }

    public static TreeNode parentOfRightMost(TreeNode node) {
        while(node.right.right != null) {
            node = node.right;
        }

        return node;
    }

    public static class TreeNode {
        public int start; // change to IP
        public int end; // change to IP
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
