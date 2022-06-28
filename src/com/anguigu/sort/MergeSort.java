package com.anguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {
    //static int count;
    public static void main(String[] args) {
        //int[] arr = {-9, 78, 0, 23, -567, 70};
        //int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] arr = new int[10];
//        for (int i = 0; i < 10; i++) {
//            arr[i] = (int) (Math.random() * 100);
//        }
//        System.out.println("排序前：" + Arrays.toString(arr));
//        int[] temp = new int[arr.length];
//        mergeSort(arr, 0, arr.length - 1, temp);
//        System.out.println("排序后：" + Arrays.toString(arr));
        int[] arr = new int[80000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);

        System.out.println("排序前:" + date1Str);
        mergeSort(arr, 0, arr.length - 1, temp);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后：" + date2Str);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 左递归拆分
            mergeSort(arr, left, mid, temp);
            // 右递归拆分
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i，左边有序序列的初始索引
        int j = mid + 1;//初始化j，右边有序序列的初始索引
        int t = 0;//数组temp当前位置的索引

        //比较分解arr数组后中数据的大小，存放在temp数组中
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        //将temp数组中的元素拷贝到arr中
        t = 0;
        int tempLeft = left;
        //System.out.println(String.format("第%d次合并：tempLeft=%d, right=%d", ++count, tempLeft, right));
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
