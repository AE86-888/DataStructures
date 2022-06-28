package com.anguigu.sparseArray;

import javax.swing.plaf.IconUIResource;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始二维数组 11*11
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                System.out.print(chessArr1[i][j] + "\t");
            }
            System.out.println();
        }
        //将二维数组转化为稀疏数组
        int sum = 0;

        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        int[][] arr = new int[sum + 1][3];
        arr[0][0] = 11;
        arr[0][1] = 11;
        arr[0][2] = sum;
        int row = 1;
        int col = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    arr[row][col] = i;
                    arr[row][col + 1] = j;
                    arr[row][col+2] = chessArr1[i][j];
                    row++;
                }
            }
        }
        for (int[] ints : arr) {
            for (int i = 0; i < ints.length; i++) {
                System.out.print(ints[i] + "\t");
            }
            System.out.println();
        }
        //将稀疏数组恢复成原始数组
        int[][] yuanArr = new int[arr[0][0]][arr[0][1]];
        for (int i = 1; i <= arr[0][2]; i++) {
            yuanArr[arr[i][0]][arr[i][1]] = arr[i][2];
        }
        System.out.println("======恢复成原始数组======");
        for (int i = 0; i < yuanArr.length; i++) {
            for (int j = 0; j < yuanArr[i].length; j++) {
                System.out.print(yuanArr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
