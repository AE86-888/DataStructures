package com.anguigu.binarytree.threadedbinarytree;


public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "bob");
        HeroNode node4 = new HeroNode(8, "njl");
        HeroNode node5 = new HeroNode(10, "tom");
        HeroNode node6 = new HeroNode(14, "mary");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        threadBinaryTree.setRoot(root);
        threadBinaryTree.postThreadTree(root);
        System.out.println("8号节点的前驱：" + node4.getLeft());//3
        System.out.println("8号节点的后继：" + node4.getRight());//10
        //中序遍历线索化二叉树
        //threadBinaryTree.threadList();//8,3,10,1,14,6

        //前序遍历线索化二叉树
        //threadBinaryTree.preThreadList();

        //后续遍历线索化二叉树
        threadBinaryTree.postThreadList();

    }
}

class ThreadBinaryTree {
    private HeroNode root;
    private HeroNode pre;//当前节点的前一个节点

    public void threadTree() {
        this.threadTree(root);
    }

    //遍历线索化二叉树(中序)
    public void threadList() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() != 1) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    //中序线索化二叉树
    public void threadTree(HeroNode node) {
        if (node == null) {
            return;
        }
        //左递归
        threadTree(node.getLeft());
        //线索化 当前节点的前驱
        if (node != null && node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);//表示指向前驱
        }
        //线索化当前节点的后继
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);//表示指向后继
        }
        // !!!更新pre，
        pre = node;
        //右递归
        threadTree(node.getRight());
    }

    //后续遍历线索化二叉树
    public void postThreadList(){
        HeroNode node = root;
        while (true){
            while (node.getLeftType() != 1){
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            if (node == root){
                break;
            }
            node = root.getRight();
        }
    }
    //后续线索化二叉树
    public void postThreadTree(HeroNode node) {
        if (node == null) {
            return;
        }
        //左递归
        postThreadTree(node.getLeft());
        //右递归
        postThreadTree(node.getRight());
        //线索化前驱
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //线索化后继
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
    }

    //前序遍历线索化二叉树
    public void preThreadList() {
        HeroNode node = root;
        while (true) {
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            if (node.getRight() == null) {
                break;
            }
            node = node.getLeft();

        }
    }


    //前序线索化二叉树
    public void preThreadTree(HeroNode node) {
        if (node == null) {
            return;
        }
        //线索化前驱
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //线索化后继
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //左递归
        if (node.getLeftType() == 0) {
            preThreadTree(node.getLeft());
        }

        //右递归
        if (node.getRightType() == 0) {
            preThreadTree(node.getRight());
        }
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空！");
        }
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空数不可以删除~");
        }
    }

    public void delNode2(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode2(no);
            }
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        }
        return null;
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        }
        return null;
    }

    //后续遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        }
        return null;
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("树为空！");
        }

    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("树为空！");
        }
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //leftType/rightType = 0表示指向对应的子树，=1表示指向前驱或后继
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    //删除节点
    // 如果删除的是叶子节点则就直接删除，如果删除的不是叶子节点，则删除对应的子树
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //删除节点的改进：如果删除的是非叶子节点，该节点有左节点，则将左节点替换该节点，否则将右节点替换节点
    public void delNode2(int no) {
        if (this.left != null && this.left.no == no) {
            if (this.left.left != null) {
                this.left.left.right = this.left.right;
                this.left = this.left.left;
            } else {
                this.left = this.left.right;
            }
            return;
        }
        if (this.right != null && this.right.no == no) {
            if (this.right.left != null) {
                this.right.left.right = this.right.right;
                this.right = this.right.left;
            } else {
                this.right = this.right.right;
            }
            return;
        }
        if (this.left != null) {
            this.left.delNode2(no);
        }
        if (this.right != null) {
            this.right.delNode2(no);
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }


    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
}

