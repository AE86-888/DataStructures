package com.anguigu.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 8;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        int index = fibSearch(arr, 1);
        System.out.println(index);
    }

    //获取 斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * @param arr       数组
     * @param findValue 需要查找的数值
     * @return 返回在数组中对应的索引，如果没有找到则返回 -1；
     */
    public static int fibSearch(int[] arr, int findValue) {
        int mid = 0;
        int k = 0;//表示斐波那契数列中元素的索引
        int[] f = fib();
        int left = 0;
        int right = arr.length - 1;
        //找到斐波那契数列中大于数组arr长度的k值
        while (f[k]< arr.length) {
            k++;
        }
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        while (left <= right) {
            mid = left + f[k - 1] - 1;
            if (temp[mid] < findValue) {
                //findValue在数组的右半部分
                //f[k] = f[k-1] + f[k-2]
                k -= 2;
                left = mid + 1;
            } else if (temp[mid] > findValue) {
                //findValue在数组的左半部分
                //f[k] = f[k-1] + f[k-2]
                k--;
                right = mid - 1;
            } else {
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }
}
