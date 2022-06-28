package com.anguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr = {5, 4, 3, 2, 1, -1};
        //int[] arr = {1, 2, 3, 4, 5};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);

        System.out.println("排序前:" + date1Str);
        bubble(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后：" + date2Str);
    }

    public static void bubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {//需要循环的次数
            boolean hasChanged = false;//判断在每次排序中，是否发生交换
            for (int j = 0; j < arr.length - 1 - i; j++) {//每次循环需要比较的次数
                if (arr[j] > arr[j + 1]) {
                    hasChanged = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!hasChanged) {
                break;
            }
        }
    }
}
