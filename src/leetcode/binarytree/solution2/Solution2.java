package leetcode.binarytree.solution2;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Solution2 {

    //104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }


    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        int maxHeight = 0;
        while (!nodes.isEmpty()) {
            maxHeight++;
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = nodes.removeFirst();
                if (tmp.left != null) nodes.add(tmp.left);
                if (tmp.right != null) nodes.add(tmp.right);
            }
        }
        return maxHeight;
    }

    //116. 填充每个节点的下一个右侧节点指针
    public Node connect(Node root) {
        if (root == null) return root;
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node cur = nodes.removeFirst();
                if (pre != null) pre.next = cur;
                pre = cur;
                if (cur.left != null) nodes.add(cur.left);
                if (cur.right != null) nodes.add(cur.right);
            }
        }
        return root;
    }

    //515. 在每个树行中找最大值
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            int max = nodes.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode tmp = nodes.removeFirst();
                if (max < tmp.val) max = tmp.val;
                if (tmp.left != null) nodes.add(tmp.left);
                if (tmp.right != null) nodes.add(tmp.right);
            }
            res.add(max);
        }
        return res;
    }

    //429. N 叉树的层序遍历
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            LinkedList<Integer> item = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node tmp = nodes.removeFirst();
                item.add(tmp.val);
                if (tmp.children != null) {
                    nodes.addAll(tmp.children);
                }
            }
            res.add(item);
        }
        return res;
    }

    //637. 二叉树的层平均值
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new LinkedList<>();
        if (root == null) return res;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode tmp = nodes.removeFirst();
                sum += tmp.val;
                if (tmp.left != null) nodes.add(tmp.left);
                if (tmp.right != null) nodes.add(tmp.right);
            }
            res.add(sum / size);
        }
        return res;
    }

    //199. 二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = nodes.removeFirst();
                if (i == size - 1) {
                    res.add(tmp.val);
                }
                if (tmp.left != null) nodes.add(tmp.left);
                if (tmp.right != null) nodes.add(tmp.right);
            }
        }
        return res;
    }

    //107. 二叉树的层序遍历 II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            List<Integer> item = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = nodes.remove(0);
                item.add(tmp.val);
                if (tmp.left != null) nodes.add(tmp.left);
                if (tmp.right != null) nodes.add(tmp.right);
            }
            res.add(0, item);
        }
        return res;
    }

    //102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            ArrayList<Integer> item = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = nodes.removeFirst();
                item.add(tmp.val);
                if (tmp.left != null) nodes.add(tmp.left);
                if (tmp.right != null) nodes.add(tmp.right);
            }
            res.add(item);
        }
        return res;
    }

    //145. 二叉树的后序遍历
    //使用迭代法
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> st = new Stack<>();
        st.add(root);
        while (!st.isEmpty()) {
            TreeNode tmp = st.pop();
            res.add(0, tmp.val);
            if (tmp.left != null) st.add(tmp.left);
            if (tmp.right != null) st.add(tmp.right);
        }
        return res;
    }

    //94. 二叉树的中序遍历
    //使用迭代法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> st = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !st.isEmpty()) {
            if (cur != null) {
                st.add(cur);
                cur = cur.left;
            } else {
                cur = st.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    //144. 二叉树的前序遍历（迭代法）
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> st = new Stack<>();
        st.add(root);
        while (!st.isEmpty()) {
            TreeNode tmp = st.pop();
            res.add(tmp.val);
            if (tmp.right != null) st.add(tmp.right);
            if (tmp.left != null) st.add(tmp.left);
        }
        return res;
    }

}

class TreeNode {
    int val;
    TreeNode right;
    TreeNode left;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}