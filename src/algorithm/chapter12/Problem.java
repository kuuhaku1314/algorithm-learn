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
        /**
         * 二叉搜索树专用字段
         */
        TreeNode<E> parent;
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

    /**
     * 12.1-3 中序遍历非递归3，不使用栈
     * @param treeNode
     * @param <E>
     */
    public static <E> void inOrderTraversalThree(TreeNode<E> treeNode) {
        TreeNode<E> temp;
        while (treeNode != null) {
            temp = treeNode.left;
            if (temp != null) {
                while (temp.right != null && temp.right != treeNode) {
                    temp = temp.right;
                }
                if (temp.right == null) {
                    temp.right = treeNode;
                    treeNode = treeNode.left;
                    continue;
                } else {
                    temp.right = null;
                }
            }
            System.out.println(treeNode.value);
            treeNode = treeNode.right;
        }
    }

    public <E extends Comparable<E>> TreeNode<E> iterativeTreeSearch(TreeNode<E> treeNode, E e) {
        while (treeNode != null && treeNode.value.compareTo(e) != 0) {
            if (treeNode.value.compareTo(e) > 0) {
                treeNode = treeNode.left;
            } else {
                treeNode = treeNode.right;
            }
        }
        return treeNode;
    }

    /**
     * 12.2-2 递归版求最小值
     * @param treeNode
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> TreeNode<E> treeMinimum (TreeNode<E> treeNode) {
        if (treeNode.left != null) {
            treeNode = treeNode.left;
            return treeMinimum(treeNode);
        }
        return treeNode;
    }

    /**
     * 12.2-2 递归版求最大值
     * @param treeNode
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> TreeNode<E> treeMaximum (TreeNode<E> treeNode) {
        if (treeNode.right != null) {
            treeNode = treeNode.right;
            return treeMaximum(treeNode);
        }
        return treeNode;
    }

    /**
     * 求节点的后驱，即大于等于节点值的最小一个
     * @param treeNode
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> TreeNode<E> treeSuccessor(TreeNode<E> treeNode) {
        if (treeNode.right != null) {
            return treeMinimum(treeNode.right);
        }
        TreeNode<E> p = treeNode.parent;
        while (p != null && treeNode.equals(p.right)) {
            treeNode = p;
            p = p.parent;
        }
        return p;
    }

    /**
     * 12.2-3 求节点的前驱，即小于等于节点值的最大一个
     * @param treeNode
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> TreeNode<E> treePredecessor(TreeNode<E> treeNode) {
        if (treeNode.left != null) {
            return treeMaximum(treeNode.left);
        }
        TreeNode<E> p = treeNode.parent;
        while (p != null && treeNode.equals(p.left)) {
            treeNode = p;
            p = p.parent;
        }
        return p;
    }

    /**
     * 12.3-1 二叉搜索树的递归插入
     * @param treeNode
     * @param e
     * @param <E>
     */
    public <E extends Comparable<E>> void treeInsert(TreeNode<E> treeNode, E e) {
        if (e.compareTo(treeNode.value) > 0) {
            if (treeNode.right == null) {
                treeNode.right = new TreeNode<>(e);
            } else {
                treeInsert(treeNode.right, e);
            }
        } else {
            if (treeNode.left == null) {
                treeNode.left = new TreeNode<>(e);
            } else {
                treeInsert(treeNode.left, e);
            }
        }
    }

    /**
     * 二叉搜索树的节点删除，如果树只有一个节点没法删除节点，毕竟外部的引用没法去掉
     * @param root
     * @param e
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> boolean treeDelete(TreeNode<E> root, E e) {
        if (root.right == null && root.left == null && root.value.equals(e)) {
            return false;
        }
        TreeNode<E> treeNode = iterativeTreeSearch(root, e);
        if (treeNode == null) {
            return false;
        }
        if (treeNode.left == null && treeNode.right == null) {
            if (treeNode.parent.right == treeNode) {
                treeNode.parent.right = null;
            } else {
                treeNode.parent.left = null;
            }
        } else if (treeNode.left == null || treeNode.right == null) {
            TreeNode<E> child = treeNode.left == null ? treeNode.right : treeNode.left;
            if (treeNode.parent.right == treeNode) {
                treeNode.parent.right = child;
            } else {
                treeNode.parent.left = child;
            }
            child.parent = treeNode.parent;
        } else {
            TreeNode<E> successor = treeSuccessor(treeNode);
            TreeNode<E> leftTree = treeNode.left;
            TreeNode<E> rightTree = treeNode.right;
            if (successor.parent.right == successor) {
                successor.parent.right = null;
            } else {
                successor.parent.left = null;
            }
            if (rightTree == successor) {
                successor.left = leftTree;
            } else {
                successor.parent.left = successor.right;
                if (successor.right != null) {
                    successor.right.parent = successor.parent;
                }
                successor.left = leftTree;
                successor.right = rightTree;
            }
            if (treeNode.parent.right == treeNode) {
                treeNode.parent.right = successor;
            } else {
                treeNode.parent.left = successor;
            }
            successor.parent = treeNode.parent;
        }
        return true;
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
        inOrderTraversalThree(treeNode1);
    }
}
