package com.anguigu.binarytree;

public class BinaryTree {
    public static void main(String[] args) {
        Tree tree = new Tree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        tree.setRoot(root);
//        System.out.println("前序遍历");
//        tree.preOrder();
//        System.out.println("中序遍历");
//        tree.infixOrder();
//        System.out.println("后序遍历");
//        tree.postOrder();
//        System.out.println(root.preOrderSearch(5));
//        System.out.println(root.infixOrderSearch(2));
//        System.out.println(root.postOrderSearch(3));
        //测试删除节点
        System.out.println("删除前，前序遍历");
        tree.preOrder();
        tree.delNode2(3);
        System.out.println("删除后前序遍历");
        tree.preOrder();
    }

}

class Tree {
    private HeroNode root;

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

    public void delNode2(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            }else {
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

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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
            if (this.left.left != null){
                this.left.left.right = this.left.right;
                this.left = this.left.left;
            }else {
                this.left = this.left.right;
            }
            return;
        }
        if (this.right != null && this.right.no == no){
            if (this.right.left != null){
                this.right.left.right = this.right.right;
                this.right = this.right.left;
            }else {
                this.right = this.right.right;
            }
            return;
        }
        if (this.left != null){
            this.left.delNode2(no);
        }
        if (this.right != null){
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
