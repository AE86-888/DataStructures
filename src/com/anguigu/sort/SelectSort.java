package com.anguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {5, 4, 3, 2, 1, -1};
//        selectSort(arr);
//       System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);

        System.out.println("排序前:" + date1Str);
        selectSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后：" + date2Str);
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {//需要进行几轮排序
            int minIndex = i;//记录最小值所在位置的索引
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {//找到最小值和最小值所在的索引
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
