package com.anguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);

        System.out.println("排序前:" + date1Str);
        //insertSort(arr);// 4s
        //insertSort1(arr);// 11s
        insertSort_hsp(arr);// 2s
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后：" + date2Str);
    }

    public static void insertSort_hsp(int[] arr) {
        int insertIndex;
        int insertValue;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertValue = arr[i];
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                //元素后移
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            insertIndex++;
            if (insertIndex != i) {
                arr[insertIndex] = insertValue;
            }
        }
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    //将索引j到i-1位置的数据后移一位，
                    int temp = arr[i];
                    for (int k = i; k > j; k--) {
                        arr[k] = arr[k - 1];
                    }
                    arr[j] = temp;
                    break;
                }
            }
            //System.out.println(String.format("第%d轮排序后:", i) + Arrays.toString(arr));
        }
    }

    public static void insertSort1(int[] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
            //System.out.println(String.format("第%d轮排序后:", i) + Arrays.toString(arr));
        }
    }
}
