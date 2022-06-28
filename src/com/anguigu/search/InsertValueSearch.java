package com.anguigu.search;

public class InsertValueSearch {
    public static void main(String[] args) {
//        int[] arr = new int[100];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = i + 1;
//        }
        int[] arr = {1};
        int index = insertValueSearch(arr, 1, 0, arr.length - 1);
        if (index != -1) {
            System.out.println("找到了，索引为：" + index);
        } else {
            System.out.println("没找到");
        }
    }

    public static int insertValueSearch(int[] arr, int findValue, int left, int right) {
        System.out.println("查找次数~");
        if (left > right || findValue < arr[left] || findValue > arr[right]) {
            return -1;
        }
        if (left == right){
            if (arr[left] == findValue){
                return left;
            }else {
                return -1;
            }
        }
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        if (arr[mid] < findValue) {
            return insertValueSearch(arr, findValue, mid + 1, right);
        } else if (arr[mid] > findValue) {
            return insertValueSearch(arr, findValue, left, mid - 1);
        } else {
            return mid;
        }
    }
}
