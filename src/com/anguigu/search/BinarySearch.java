package com.anguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找,数组必须是有序的；
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 4, 5, 6, 7, 8};
        //int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 4
        );
        System.out.println(list);
    }

    //假定，输入的数组arr是升序排序；
    public static int binarySearch(int[] arr, int left, int right, int findNum) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (findNum < arr[mid]) {
            return binarySearch(arr, left, mid - 1, findNum);
        } else if (findNum > arr[mid]) {
            return binarySearch(arr, mid + 1, right, findNum);
        } else {
            return mid;
        }
    }

    //返回找到数据的所有索引
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findNum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (left > right) {
            return list;
        }
        int mid = left + (right - left) / 2;
        if (findNum < arr[mid]) {
            return binarySearch2(arr, left, mid - 1, findNum);
        } else if (findNum > arr[mid]) {
            return binarySearch2(arr, mid + 1, right, findNum);
        } else {
            int tempMid = mid;
            while (tempMid >= left) {
                tempMid--;
                if (arr[tempMid] == findNum) {
                    list.add(tempMid);
                } else {
                    break;
                }
            }
            list.add(mid);
            tempMid = mid;
            while (tempMid <= right) {
                tempMid++;
                if (arr[tempMid] == findNum) {
                    list.add(tempMid);
                } else {
                    break;
                }
            }
            return list;
        }
    }
}
