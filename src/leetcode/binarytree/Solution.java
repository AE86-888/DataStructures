package leetcode.binarytree;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * 二叉树相关题目
 */
public class Solution {

    public static void main(String[] args) {
        Solution so = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        TreeNode node = so.trimBST(root, 1, 2);

        //so.deleteNode(root,5);
    }

    //538. 把二叉搜索树转换为累加树
   /* public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        pre = 0;
        reversePostOrder(root);
        return root;
    }
    int pre;
    //反中序遍历
    public void reversePostOrder(TreeNode root) {
       if (root.right != null) reversePostOrder(root.right);
       root.val += pre;
       pre = root.val;
       if (root.left != null) reversePostOrder(root.left);
    }*/


    //108. 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;
        return BST(nums, 0, nums.length - 1);
    }

    public TreeNode BST(int[] nums, int left, int right) {
        if (left > right) return null;
        if (right == left) return new TreeNode(nums[left]);
        //找到数组的中间值作为 根节点
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = BST(nums, left, mid - 1);
        root.right = BST(nums, mid + 1, right);
        return root;
    }

    //669. 修剪二叉搜索树
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low) return trimBST(root.right, low, high);
        if (root.val > high) return trimBST(root.left, low, high);
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    public TreeNode trimBST2(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.right != null) root.right = trimBST(root.right, low, high);
        if (root.left != null) root.left = trimBST(root.left, low, high);
        if (root.val < low || root.val > high) {//当前节点需要删除
            //找到要删除的节点
            //1.要删除的节点是叶子节点
            if (root.left == null && root.right == null) return null;
            //2.要删除的节点有左右子树
            if (root.left != null && root.right != null) {
                //直接将左子树 放在 右子树 最小值节点的左子树上
                //找到右子树的最小值
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = root.left;
                return root;
            }
            //3.被删除的节点只有一个子树
            if (root.left == null && root.right != null) {
                root.val = root.right.val;
                TreeNode rightTree = root.right;//记录右子树
                root.left = rightTree.left;
                root.right = rightTree.right;
                return root;
            }
            if (root.left != null && root.right == null) {
                root.val = root.left.val;
                TreeNode leftTree = root.left;//记录左子树
                root.left = leftTree.left;
                root.right = leftTree.right;
                return root;
            }
        }
        return root;
    }

    //450. 删除二叉搜索树中的节点
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right != null) {
                //有两个子树
                TreeNode cur = root.right;
                TreeNode pre = root;
                while (cur.left != null) {
                    pre = cur;
                    cur = cur.left;
                }
                root.val = cur.val;
//                if (pre == root) {
//                    pre.right = cur.right;
//                } else {
//                    pre.left = cur.right;
//                }
                if (deleteNode(cur, cur.val) == null) {
                    if (pre != root) pre.right = null;
                }
            } else {
                //只有一个子树
                return root.left != null ? root.left : root.right;
            }
        }
        return root;
    }


    //450. 删除二叉搜索树中的节点
   /* public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            //被删除的节点是叶子节点
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right != null) {
                //同时有左右子树
                //找到右子树的最左侧节点
                TreeNode cur = root.right;
                while (cur.left != null){
                    cur = cur.left;
                }
                cur.left = root.left;
            } else{
                return root.left != null ? root.left : root.right;
            }
        }
        return root;
    }
*/
    /* public TreeNode deleteNode(TreeNode root, int key) {
         if (root == null) return null;
         //先找到被删除的节点
         if (key < root.val) {
             root.left = deleteNode(root.left, key);
         } else if (key > root.val) {
             root.right = deleteNode(root.right, key);
         } else {
             //此时，root节点就是被删除的节点
             //被删除的节点是叶子节点
             if (root.left == null && root.right == null){
                 return null;
             }else {//被删除的节点不是叶子节点
                 delete(root);
             }
         }
         return root;
     }
 */
    //找到被删除的节点后，改变被删除节点的值
    public void delete(TreeNode root) {
        //被删除的节点 有左右子树
        if (root.left != null) {
            //用左子树的最大值替换root的值，并移除左子树的最大值
            TreeNode cur = root.left;
            TreeNode pre = root;
            while (cur.right != null) {
                pre = cur;
                cur = cur.right;
            }
            root.val = cur.val;
            if (cur.left != null) {
                //左子树的最大值有左子树
                delete(cur);
            } else {
                if (pre == root) {
                    pre.left = null;
                } else {
                    pre.right = null;//移除左子树最大值的节点
                }
            }
        } else if (root.right != null) {
            //被删除的节点只有右子树,找到右子树的最小值
            TreeNode cur = root.right;
            TreeNode pre = root;
            while (cur.left != null) {
                pre = cur;
                cur = cur.left;
            }
            root.val = cur.val;
            if (cur.right != null) {
                delete(cur);
            } else {
                if (pre == root) {
                    pre.right = null;
                } else {
                    pre.left = null;//删除右子树的最小值
                }
            }
        }
    }

    /**
     * 701. 二叉搜索树中的插入操作
     *
     * @param root
     * @param val
     * @return
     */

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    /**
     * 236. 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == root || q == root || root == null) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left == null && right != null) {
            return right;
        } else if (left != null && right == null) {
            return left;
        } else {
            //left == null && right == null
            return null;
        }
    }


    /**
     * 501. 二叉搜索树中的众数
     *
     * @param root
     * @return
     */
    public int[] findMode2(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        res = new ArrayList<Integer>();
        maxCount = 0;
        inorder2(root);
        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    private int count;
    private int maxCount;
    //private TreeNode pre;
    private List<Integer> res;

    //中序遍历
    public void inorder2(TreeNode cur) {
        if (cur.left != null) {
            inorder2(cur.left);
        }
        if (pre == null || pre.val != cur.val) {
            count = 1;
        } else {
            count++;
        }
        if (count > maxCount) {
            res.clear();
            res.add(cur.val);
            maxCount = count;
        } else if (count == maxCount) {
            res.add(cur.val);
        }
        pre = cur;
        if (cur.right != null) {
            inorder2(cur.right);
        }
    }

    public int[] findMode(TreeNode root) {
        if (root.left == null && root.right == null) {
            int[] res = {root.val};
            return res;
        }
        List<Integer> list = new ArrayList<>();
        //中序遍历
        inorder(root, list);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer integer : list) {
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);
            } else {
                map.put(integer, 1);
            }
        }
        Stack<Integer> stack = new Stack<>();
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            if (stack.isEmpty()) {
                stack.add(key);
            } else {
                Integer top = stack.peek();
                if (map.get(top) < map.get(key)) {
                    while (stack.size() > 0) {
                        stack.pop();
                    }
                    stack.add(key);
                } else if (map.get(top) == map.get(key)) {
                    stack.add(key);
                }
            }
        }
        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = stack.pop();
        }
        return res;
    }


    /**
     * 530. 二叉搜索树的最小绝对差
     *
     * @param root
     * @return
     */
    //方法2：递归法
    public TreeNode pre;

    public int getMinimumDifference(TreeNode root) {
        int leftMIn = Integer.MAX_VALUE;
        if (root.left != null) {
            leftMIn = getMinimumDifference(root.left);
        }
        if (pre != null && leftMIn > Math.abs(root.val - pre.val)) {
            leftMIn = Math.abs(root.val - pre.val);
        }
        pre = root;
        int rightMIn = Integer.MAX_VALUE;
        if (root.right != null) {
            rightMIn = getMinimumDifference(root.right);
        }
        return Math.min(leftMIn, rightMIn);
    }


    public int getMinimumDifference2(TreeNode root) {
        //中序遍历
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);
        int min = Math.abs(list.get(0) - list.get(1));
        for (int i = 1; i < list.size() - 1; i++) {
            if (min > Math.abs(list.get(i) - list.get(i + 1))) {
                min = Math.abs(list.get(i) - list.get(i + 1));
            }
        }
        return min;
    }

    /**
     * 98. 验证二叉搜索树
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBST(root, root.val);
    }

    public boolean isBST(TreeNode root, int preVal) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val < root.val) {
            TreeNode cur = root.left.right;
            while (root.left.right != null) {
                if (root.left.right.val >= preVal) {
                    return false;
                }
                cur = cur.right;
            }
        }
        if (root.left != null && root.left.val >= root.val) {
            return false;
        }
        if (root.right != null && root.right.val > root.val) {
            TreeNode cur = root.right.left;
            while (root.right.left != null) {
                if (root.right.left.val <= preVal) {
                    return false;
                }
                cur = cur.left;
            }
        }
        if (root.right != null && root.right.val <= root.val) {
            return false;
        }
        return isBST(root.left, root.val) && isBST(root.right, root.val);
    }

    public boolean isValidBST(TreeNode root) {
        //中序遍历
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 700. 二叉搜索树中的搜索
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }

    /**
     * 617. 合并二叉树
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == null ? root2 : root1;
        }
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }


    /**
     * 654. 最大二叉树
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return maxTree(nums, 0, nums.length);
    }

    public TreeNode maxTree(int[] nums, int left, int right) {
        //左闭右开区间
        if (left >= right) {
            return null;
        }

        //找到数组中的最大值
        int maxIndex = left;//记录最大值在数组中的索引
        for (int i = left + 1; i < right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        //根据最大值构建根节点
        TreeNode root = new TreeNode(nums[maxIndex]);
        if (right - left == 1) {
            return root;
        }
        root.right = maxTree(nums, maxIndex + 1, right);
        root.left = maxTree(nums, left, maxIndex);

        return root;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        return build3(preorder, 0, preorder.length,
                inorder, 0, inorder.length);
    }

    public TreeNode build3(int[] preorder, int left1, int right1,
                           int[] inorder, int left2, int right2) {
        //左闭右开区间
        if (right1 - left1 == 0) {
            return null;
        }
        int rootVal = preorder[left1];
        TreeNode root = new TreeNode(rootVal);
        if (right1 - left1 == 1) {
            return root;
        }
        int der;//定义中序数组的切分点
        for (der = left2; der < right2; der++) {
            if (inorder[der] == rootVal) {
                break;
            }
        }
        root.left = build3(preorder, left1 + 1, left1 + 1 + der - left2,
                inorder, left2, der);
        root.right = build3(preorder, left1 + 1 + der - left2, right1,
                inorder, der + 1, right2);
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    //方法2：使用数组下标，不创建新数组
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return build(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    /**
     * @param inorder   中序遍历数组
     * @param left1     中序遍历数组的左侧下标
     * @param right1    中序遍历数组的右侧下标
     * @param postorder 后序遍历数组
     * @param left2     后序遍历数组的左侧下标
     * @param right2    后序遍历数组的右侧下标
     * @return 根节点
     */
    public TreeNode build(int[] inorder, int left1, int right1, int[] postorder, int left2, int right2) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        int rootVal = postorder[right2];
        TreeNode root = new TreeNode(rootVal);
        if (right1 - left1 == 0) {
            return root;
        }
        //切分数组
        int der;
        for (der = left1; der < right1; der++) {
            if (inorder[der] == rootVal) {
                break;
            }
        }
        if (der == left1) {
            //只有右子树
            root.right = build(inorder, der + 1, right1,
                    postorder, left2 + der - left1, right2 - 1);
        } else if (der > left1 && der < right1) {
            //新的中序数组 左侧
            root.left = build(inorder, left1, left1 + der - left1 - 1,
                    postorder, left2, left2 + der - left1 - 1);
            root.right = build(inorder, der + 1, right1,
                    postorder, left2 + der - left1, right2 - 1);
        } else {
            //只有左子树
            root.left = build(inorder, left1, left1 + der - left1 - 1,
                    postorder, left2, left2 + der - left1 - 1);
        }
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);
        if (inorder.length == 1) {
            return root;
        }

        int der;
        for (der = 0; der < inorder.length; der++) {
            if (inorder[der] == rootVal) {
                break;
            }
        }
        //切分中序数组
        int[] leftInorder = new int[der];
        int[] leftPost = new int[der];
        for (int i = 0; i < leftInorder.length; i++) {
            leftInorder[i] = inorder[i];
        }

        int[] rightInorder = new int[inorder.length - 1 - der];
        int[] rightPost = new int[inorder.length - 1 - der];
        for (int i = 0, j = der + 1; i < rightInorder.length; i++, j++) {
            rightInorder[i] = inorder[j];
        }
        //切分后续数组
        if (der == 0) {
            //只有右子树
            rightPost = Arrays.copyOf(postorder, rightPost.length);
        } else if (der < inorder.length - 1) {
            for (int i = 0, j = 0; i < postorder.length - 1; i++) {
                if (i < leftPost.length) {
                    leftPost[i] = postorder[i];
                } else {
                    rightPost[j++] = postorder[i];
                }
            }
        } else {
            //只有左子树
            leftPost = Arrays.copyOf(postorder, leftPost.length);
        }
        root.left = buildTree(leftInorder, leftPost);
        root.right = buildTree(rightInorder, rightPost);
        return root;
    }

    /**
     * 113. 路径总和 II
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        travelTarget(root, path, targetSum, res);
        return res;
    }

    public void travelTarget(TreeNode root, List<Integer> path, int targetSum, List<List<Integer>> res) {
        path.add(root.val);
        if (root.left == null && root.right == null) {
            int sum = 0;
            for (int i = 0; i < path.size(); i++) {
                sum += path.get(i);
            }
            if (sum == targetSum) {
                List<Integer> copyPath = new ArrayList<>(path);
                res.add(copyPath);
            } else {
                return;
            }
        }
        if (root.left != null) {
            travelTarget(root.left, path, targetSum, res);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            travelTarget(root.right, path, targetSum, res);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 112. 路径总和
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
//        List<Integer> path = new ArrayList<>();
//        return travel(root, path, targetSum);
        return traver2(root, targetSum - root.val);
    }

    public boolean traver2(TreeNode root, int sum) {
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                return true;
            } else {
                return false;
            }
        }
        if (root.left != null) {
            sum -= root.left.val;
            if (traver2(root.left, sum)) {
                return true;
            }
            sum += root.left.val;
        }
        if (root.right != null) {
            sum -= root.right.val;
            if (traver2(root.right, sum)) {
                return true;
            }
            sum += root.right.val;
        }
        return false;
    }

    public boolean travel(TreeNode root, List<Integer> path, int targetSum) {
        if (root == null) {
            return false;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            //叶子节点
            int sum = 0;
            for (int i = 0; i < path.size(); i++) {
                sum += path.get(i);
            }
            if (sum == targetSum) {
                return true;
            }
        }
        if (root.left != null) {
            boolean res = travel(root.left, path, targetSum);
            if (res) {
                return true;
            }
            path.remove(path.size() - 1);// 回溯
        }
        if (root.right != null) {
            boolean res = travel(root.right, path, targetSum);
            if (res) {
                return true;
            }
            path.remove(path.size() - 1);// 回溯
        }
        return false;
    }

    /**
     * 513. 找树左下角的值
     *
     * @param root
     * @return
     */


    public int findBottomLeftValue(TreeNode root) {
        //考虑层次遍历
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        List<List<Integer>> res = new ArrayList<>();
        while (nodes.size() > 0) {
            List<Integer> item = new ArrayList<>();
            int len = nodes.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = nodes.remove(0);
                if (tmp.left != null) {
                    nodes.add(tmp.left);
                }
                if (tmp.right != null) {
                    nodes.add(tmp.right);
                }
                item.add(tmp.val);
            }
            res.add(0, item);
        }
        List<Integer> list = res.get(0);
        return list.get(0);
    }

    /**
     * 404. 左叶子之和
     *
     * @param root
     * @return
     */
    //方法2：递归
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sumOfLeftLeaves(root.left);
        int rightSum = sumOfLeftLeaves(root.right);
        int midVal = 0;
        if (root.left != null &&
                root.left.left == null && root.left.right == null) {
            midVal = root.left.val;
        }
        return leftSum + rightSum + midVal;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return 0;
        }
        TreeNode pre = null;
        preOrder1(root, list, pre);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }

    public void preOrder1(TreeNode cur, List<Integer> list, TreeNode pre) {
        if (cur.left == null && cur.right == null) {
            if (pre != null && pre.left == cur) {
                list.add(cur.val);
            }
        }
        if (cur.left != null) {
            preOrder1(cur.left, list, cur);
        }
        if (cur.right != null) {
            preOrder1(cur.right, list, cur);
        }
    }

    /**
     * 257. 二叉树的所有路径
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        traversal(root, res, path);
        return res;
    }

    public void traversal(TreeNode node, List<String> res, List<Integer> path) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            //是叶子节点
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i));
                sb.append("->");
            }
            //加入最后一个叶子节点
            sb.append(path.get(path.size() - 1));
            res.add(sb.toString());//将路径加入到res
        }
        if (node.left != null) {
            traversal(node.left, res, path);
            path.remove(path.size() - 1);//回溯
        }
        if (node.right != null) {
            traversal(node.right, res, path);
            path.remove(path.size() - 1);//回溯
        }
    }


    /**
     * 110. 平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return getTreeHeight(root) != -1;
    }

    //如果不是平衡二叉树返回-1
    public int getTreeHeight(TreeNode node) {
        //计算树的高度
        if (node == null) {
            return 0;
        }
        int left = getTreeHeight(node.left);
        int right = getTreeHeight(node.right);
        if (left == -1) {
            return -1;
        }
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    /**
     * 222. 完全二叉树的节点个数
     *
     * @param root
     * @return
     */
    //方法1：利用完全二叉树的性质
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (leftHeight == rightHeight) {//左子树是满二叉树
            //满二叉树节点数：2^h - 1；h是二叉树的高度
            //1 << leftHeight 就是 (2^leftHeight - 1 + 1),即左子树节点数  + 一个根节点
            return (1 << leftHeight) + countNodes(root.right);
        } else {//右子树是满二叉数
            return (1 << rightHeight) + countNodes(root.left);
        }
    }

    public int getHeight(TreeNode node) {
        //计算二叉树的高度
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    //方法2：递归,适用于普通的二叉树
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes2(root.left) + countNodes2(root.right) + 1;
    }

    //方法3：层次遍历
    public int countNodes3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        int sum = 0;
        while (!nodes.isEmpty()) {
            int len = nodes.size();
            sum += len;
            for (int i = 0; i < len; i++) {
                TreeNode tmp = nodes.remove(0);
                if (tmp.left != null) {
                    nodes.add(tmp.left);
                }
                if (tmp.right != null) {
                    nodes.add(tmp.right);
                }
            }
        }
        return sum;
    }

    /**
     * 101. 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right == null) {
            return true;
        } else if (left.val != right.val) {
            return false;
        }
        boolean out = compare(left.left, right.right);
        boolean in = compare(left.right, right.left);
        return out && in;
    }

    /**
     * 226. 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    public void invert(TreeNode root) {
        if (root == null) {
            return;
        }
        //交换二叉树的左右子树
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invert(root.left);
        invert(root.right);
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param root
     * @return
     */
    //方法二：后续遍历
    public int minDepth2(TreeNode root) {
        return getMinDepth(root);
    }

    public int getMinDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = getMinDepth(node.left);
        int rightDepth = getMinDepth(node.right);
        if (node.left == null && node.right != null) {
            return rightDepth + 1;
        }
        if (node.right == null && node.left != null) {
            return leftDepth + 1;
        }
        return Math.min(leftDepth, rightDepth) + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        int height = 0;
        while (!nodes.isEmpty()) {
            height++;
            int len = nodes.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = nodes.remove(0);
                if (tmp.left == null && tmp.right == null) {//判断是不是叶子节点
                    return height;
                }
                if (tmp.left != null) {
                    nodes.add(tmp.left);
                }
                if (tmp.right != null) {
                    nodes.add(tmp.right);
                }
            }
        }
        return height;
    }

    /**
     * 559. N 叉树的最大深度
     *
     * @param root
     * @return
     */
    //方法二：使用前序遍历+递归
    public int maxDepth_n2(Node root) {
        return getMaxHeight_n(root);
    }

    public int getMaxHeight_n(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        int size = root.children.size();
        for (int i = 0; i < size; i++) {
            Node node = root.children.get(i);
            int node_height = getMaxHeight_n(node);
            if (node_height > max) {
                max = node_height;
            }
        }
        return max + 1;
    }

    public int maxDepth_n(Node root) {
        if (root == null) {
            return 0;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(root);
        int dep = 0;//深度
        while (!nodes.isEmpty()) {
            dep++;
            int len = nodes.size();
            for (int i = 0; i < len; i++) {
                Node tmp = nodes.remove(0);
                nodes.addAll(tmp.children);
            }
        }
        return dep;
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    //方法二：使用前序遍历
    public int maxDepth2(TreeNode root) {
        return getMaxHeight(root);
    }

    public int getMaxHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = getMaxHeight(root.left);
        int rightMax = getMaxHeight(root.right);
        return Math.max(leftMax, rightMax) + 1;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        int dep = 0;//深度
        while (!nodes.isEmpty()) {
            dep++;
            int len = nodes.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = nodes.remove(0);
                if (tmp.left != null) {
                    nodes.add(tmp.left);
                }
                if (tmp.right != null) {
                    nodes.add(tmp.right);
                }
            }
        }
        return dep;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     *
     * @param root
     * @return
     */
    /*
    * public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        //广度优先遍历
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int len = nodes.size();
            for (int i = 0; i < len; i++) {
                Node tmp = nodes.remove(0);
                if (tmp.left != null) {
                    nodes.add(tmp.left);
                }
                if (tmp.right != null) {
                    nodes.add(tmp.right);
                }
                if (i == len - 1) {
                    continue;
                }
                tmp.next = nodes.get(0);
            }
        }
        return root;
    }
    **/


    /**
     * 515. 在每个树行中找最大值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (nodes.size() > 0) {
            int len = nodes.size();
            int max = nodes.get(0).val;
            for (int i = 0; i < len; i++) {
                TreeNode tmp = nodes.remove(0);
                if (tmp.val > max) {
                    max = tmp.val;
                }
                if (tmp.left != null) {
                    nodes.add(tmp.left);
                }
                if (tmp.right != null) {
                    nodes.add(tmp.right);
                }
            }
            res.add(max);
        }
        return res;
    }

    /**
     * 429. N 叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            int len = nodes.size();
            for (int i = 0; i < len; i++) {
                Node tmp = nodes.remove(0);
                item.add(tmp.val);
                if (tmp.children != null && tmp.children.size() > 0) {
                    for (int j = 0; j < tmp.children.size(); j++) {
                        nodes.add(tmp.children.get(j));
                    }
                }
            }
            res.add(item);
        }
        return res;
    }

    /**
     * 二叉树的层平均值
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        ArrayList<TreeNode> nodes = new ArrayList<>();
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        nodes.add(root);
        //层次遍历
        while (!nodes.isEmpty()) {
            double sum = 0;
            int len = nodes.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = nodes.remove(0);
                if (tmp.left != null) {
                    nodes.add(tmp.left);
                }
                if (tmp.right != null) {
                    nodes.add(tmp.right);
                }
                sum += tmp.val;
            }
            res.add(sum / len);
        }
        return res;
    }

    /**
     * 199. 二叉树的右视图
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            int len = nodes.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = nodes.remove(0);
                if (i == len - 1) {
                    res.add(tmp.val);
                }
                if (tmp.left != null) {
                    nodes.add(tmp.left);
                }
                if (tmp.right != null) {
                    nodes.add(tmp.right);
                }
            }
            lists.add(item);
        }
        return res;
    }

    /**
     * 二叉树的层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int len = list.size();
            List<Integer> item = new ArrayList<>();
            while (len > 0) {
                TreeNode node = list.remove(0);
                item.add(node.val);
                if (node.left != null) {
                    list.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right);
                }
                len--;
            }
            res.add(item);
        }
        return res;
    }

    /**
     * 145. 二叉树的后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    public void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            postOrder(root.left, list);
        }
        if (root.right != null) {
            postOrder(root.right, list);
        }
        list.add(root.val);
    }

    /**
     * 144. 二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    public void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left != null) {
            preOrder(root.left, list);
        }
        if (root.right != null) {
            preOrder(root.right, list);
        }
    }

    /**
     * 94. 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorder(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            inorder(root.right, list);
        }
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

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
