package com.anguigu.avltree;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};
        //int[] arr = {10,12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("根节点" + avlTree.getRoot());
        avlTree.height();
        System.out.println("树的高度：" + avlTree.height());
        System.out.println("左子树高度：" + avlTree.leftHeight());
        System.out.println("右子树高度：" + avlTree.rightHeight());
    }
}

class AVLTree {
    Node root;


    //返回当前树的高度
    public int height() {
        if (root == null) {
            return 0;
        }
        return root.height();
    }

    //返回左子树的高度
    public int leftHeight() {
        if (root == null || root.left == null) {
            return 0;
        }
        return root.left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (root == null || root.right == null) {
            return 0;
        }
        return root.right.height();
    }


    /**
     * 查找结点
     *
     * @param value 要查找的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //删除左子树 值最大的节点，并返回该最大值
    public int delLeftTreeMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        delNode(node.value);
        return node.value;
    }

    //删除右子树 值最小的节点，并返回最小值
    public int delRightTreeMin(Node node) {
        Node targetNode = node;
        while (targetNode.left != null) {
            targetNode = targetNode.left;
        }
        delNode(targetNode.value);
        return targetNode.value;
    }

    public Node getRoot() {
        return root;
    }

    //删除节点
    public void delNode(int value) {
        //1.找到要删除的节点
        Node targetNode = search(value);
        if (targetNode == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        //2. 找到要删除节点的父节点
        Node parent = searchParent(value);

        if (targetNode.left == null && targetNode.right == null) {
            //删除的节点是叶子节点
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            //删除的节点有左右两个节点
            //1、找到要删除节点右子树的最小值，并删除该最小值
            int minValue = delRightTreeMin(targetNode.right);
            //2、将要删除节点的值，改为右子树的最小值
            targetNode.value = minValue;
//            int max = delLeftTreeMax(targetNode.left);
//            targetNode.value = max;
        } else {
            //删除的节点只有 一个 子节点
            if (parent == null) {
                if (targetNode.left != null) {
                    root = targetNode.left;
                } else {
                    root = targetNode.right;
                }
                return;
            }
            if (targetNode.left != null) {
                if (parent.left != null && parent.left.value == value) {
                    parent.left = targetNode.left;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = targetNode.left;
                }
            } else {
                if (parent.left != null && parent.left.value == value) {
                    parent.left = targetNode.right;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = targetNode.right;
                }
            }
        }
    }


    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("空数不可遍历");
            return;
        } else {
            root.infixOrder();
        }
    }

    //递归添加节点
    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        } else {
            root.add(node);
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回当前节点左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回当前节点右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回当前节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转
    public void leftRotate() {
        //1.创建一个新节点，新节点的值等于根节点的值
        Node newNode = new Node(value);
        //2、新节点的左子树设置为当前节点左子树
        newNode.left = left;
        //3、新节点的右子树设置为当前新节点的右子树的左子树
        newNode.right = right.left;
        //4、当前节点的值换位右子节点的值
        value = right.value;
        //5
        right = right.right;
        //6
        left = newNode;

    }

    //右旋转
    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     * 查找该节点
     *
     * @param value 要查找节点的值
     * @return 如果找到则返回该节点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left != null) {
                return this.left.search(value);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.search(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 查找该节点的父节点
     *
     * @param value 要查找节点的值
     * @return 返回要查找节点的父节点，如果没找到返回null
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (this.left != null && value < this.value) {
                return this.left.searchParent(value);
            } else if (this.right != null && value >= this.value) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    //添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        if (rightHeight() - 1 > leftHeight()) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
            return;//必须要！！！只旋转一次，防止后面的旋转发生
        }
        if (leftHeight() - 1 > rightHeight()) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
            }
            rightRotate();
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}