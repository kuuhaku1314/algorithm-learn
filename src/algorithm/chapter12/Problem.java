package algorithm.chapter12;

import java.util.Stack;

/**
 * @author by kuuhaku
 * @Date 2021/4/10 23:34
 * @Description
 */
public class Problem {

    static class TreeNode<E> {
        E value;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E value) {
            this.value = value;
        }
    }

    /**
     * 12.1-3 中序遍历非递归 解法1
     * @param treeNode
     * @param <E>
     */
    public static <E> void inOrderTraversal(TreeNode<E> treeNode) {
        Stack<TreeNode<E>> stack = new Stack<>();
        while (treeNode != null) {
            stack.push(treeNode);
            treeNode = treeNode.left;
            while (treeNode == null && !stack.isEmpty()) {
                TreeNode<E> pop = stack.pop();
                System.out.println(pop.value);
                if (pop.right != null) {
                    stack.push(pop.right);
                    if (pop.right.left != null) {
                        treeNode = pop.right.left;
                        break;
                    }
                }
            }
        }
    }

    /**
     * 12.1-3 中序遍历非递归2，较通用解法，按照栈思想
     * @param treeNode
     * @param <E>
     */
    public static <E> void inOrderTraversalTwo(TreeNode<E> treeNode) {
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            if (treeNode.left != null) {
                stack.push(treeNode.left);
                treeNode = treeNode.left;
                continue;
            }
            TreeNode<E> pop = stack.pop();
            System.out.println(pop.value);
            treeNode = pop;
            if (treeNode.right != null) {
                stack.push(treeNode.right);
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 12.1-4 先序遍历非递归
     * @param treeNode
     * @param <E>
     */
    public static <E> void preorderTraversal(TreeNode<E> treeNode) {
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(treeNode);
        System.out.println(treeNode.value);
        while (!stack.isEmpty()) {
            if (treeNode.left != null) {
                stack.push(treeNode.left);
                System.out.println(treeNode.left.value);
                treeNode = treeNode.left;
                continue;
            }
            treeNode = stack.pop();
            if (treeNode.right != null) {
                stack.push(treeNode.right);
                System.out.println(treeNode.right.value);
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 12.1-4 后序遍历非递归
     * @param treeNode
     * @param <E>
     */
    public static <E> void postorderTraversal(TreeNode<E> treeNode) {
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(treeNode);
        TreeNode<E> temp = null;
        while (!stack.isEmpty()) {
            if (treeNode.left != null && temp == null) {
                stack.push(treeNode.left);
                treeNode = treeNode.left;
                continue;
            }
            treeNode = stack.pop();
            if (treeNode.right != null && (temp == null || treeNode.right != temp)) {
                stack.push(treeNode);
                temp = null;
                stack.push(treeNode.right);
                treeNode = treeNode.right;
                continue;
            }
            temp = treeNode;
            System.out.println(treeNode.value);
        }
    }



    public static void main(String[] args) {
        TreeNode<Integer> treeNode1 = new TreeNode<>(6);
        TreeNode<Integer> treeNode2 = new TreeNode<>(5);
        TreeNode<Integer> treeNode3 = new TreeNode<>(7);
        TreeNode<Integer> treeNode4 = new TreeNode<>(2);
        TreeNode<Integer> treeNode5 = new TreeNode<>(5);
        TreeNode<Integer> treeNode6 = new TreeNode<>(8);
        TreeNode<Integer> treeNode7 = new TreeNode<>(9);
        TreeNode<Integer> treeNode8 = new TreeNode<>(10);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.right = treeNode6;
        treeNode3.left = treeNode7;
        treeNode6.left = treeNode8;
        postorderTraversal(treeNode1);
    }
}
