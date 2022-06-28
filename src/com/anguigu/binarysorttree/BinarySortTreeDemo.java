package com.anguigu.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        binarySortTree.infixOrder();//1,3,5,7,9,10,12
        //System.out.println("查找节点值为1：" + binarySortTree.search(1));
        //System.out.println("根节点：" + binarySortTree.getRoot());
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("删除节点后");
        binarySortTree.infixOrder();
        System.out.println("根节点：" + binarySortTree.getRoot());
    }
}

class BinarySortTree {
    Node root;

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
            if (parent == null){
                if (targetNode.left != null){
                    root = targetNode.left;
                }else {
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