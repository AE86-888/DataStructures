package com.anguigu.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        radixSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println("排序前：" + Arrays.toString(arr));
        int[] temp = new int[arr.length];
        radixSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //二维数组，表示10个桶
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementNum = new int[10];//记录每个桶中放入的数据个数
        int max = arr[0];
        //先得到原数组中的最大值
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        String maxNum = max + "";
        for (int i = 0; i < maxNum.length(); i++) {//最大数是几位数就循环几轮
            int digital = 0;
            for (int j = 0; j < arr.length; j++) {
                digital = arr[j] / ((int) Math.pow(10, i)) % 10;
                bucket[digital][bucketElementNum[digital]] = arr[j];
                bucketElementNum[digital]++;
            }
            //将桶中的放入的数据取出，放回原数组
            int index = 0;
            for (int j = 0; j < bucket.length; j++) {
                for (int k = 0; k < bucketElementNum[j]; k++) {
                    arr[index++] = bucket[j][k];
                }
                bucketElementNum[j] = 0;
            }
        }
    }


}


