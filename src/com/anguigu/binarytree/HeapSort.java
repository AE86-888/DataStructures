package com.anguigu.binarytree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        int temp;
//        adjustArr(arr,1,arr.length);
//        System.out.println(Arrays.toString(arr));
//        adjustArr(arr,0,arr.length);
//        System.out.println(Arrays.toString(arr));
        //首先将数组调整成大顶堆的形式
        //从最后一个非叶子节点开始调整，最后一个非叶子节点的索引：arr.length / 2 - 1
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustArr(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            //交换位置
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;

            adjustArr(arr, 0, j);
        }
    }

    /**
     * 将数组调整成大顶堆
     *
     * @param arr    数组
     * @param i      非叶子节点的位置
     * @param length 需要调整的数组长度,每次都要 -1
     */
    public static void adjustArr(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (temp < arr[k]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
