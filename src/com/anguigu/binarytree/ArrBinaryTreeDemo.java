package com.anguigu.binarytree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        //arrBinaryTree.preOrder(0);
        //arrBinaryTree.infixOrder(0);
        arrBinaryTree.postOrder(0);
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * @param index 对应数组中的索引，从0开始
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        System.out.print(arr[index] + " ");
        //左递归
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        //右递归
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //中续遍历
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        //左递归
        if (index * 2 + 1 < arr.length) {
            infixOrder(index * 2 + 1);
        }
        System.out.print(arr[index] + " ");
        //右递归
        if (index * 2 + 2 < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    //后续遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        //左递归
        if (index * 2 + 1 < arr.length) {
            postOrder(index * 2 + 1);
        }
        //右递归
        if (index * 2 + 2 < arr.length) {
            postOrder(index * 2 + 2);
        }
        System.out.print(arr[index] + " ");
    }
}
