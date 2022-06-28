package com.anguigu.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
//        int[] arr = new int[10];
//        for (int i = 0; i < 10; i++) {
//            arr[i] = (int) (Math.random() * 1000);
//        }
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        //int privot = arr[(right + left) / 2];
        int privot = arr[left + (right - left) / 2];
        int temp;
        while (l < r) {
            while (arr[l] < privot) {
                l++;
            }
            while (arr[r] > privot) {
                r--;
            }
            if (l >= r) {//左侧已满足都小于privot，右侧都满足大于privot
                break;
            }
            //交换数组中数据位置
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == privot) {
                r--;
            }
            if (arr[r] == privot) {
                l++;
            }
        }

        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (l < right) {
            quickSort(arr, l, right);
        }
    }
}
