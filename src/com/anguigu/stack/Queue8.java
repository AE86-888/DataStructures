package com.anguigu.stack;

/**
 * 八皇后问题
 */
public class Queue8 {
    int max = 8;
    int count;//统计公有几种解法
    int[] arr = new int[max];//一维数组，索引表示行，存储的数值表示列

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(String.format("公有%d中解法", queue8.count));
    }

    private void check(int n) {
        if (n == max) {//表示正在放置第9的皇后，即，8个皇后已经全部放置好
            print();
            return;
        }
        //依次从第1到第8列，放置皇后看看是否可行
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    // 判断放置第n+1个皇后是否可行
    private boolean judge(int n) {
        // 与之前放置的皇后依次判断是否 同列(因为采用的一维数组，所以肯定不会同行)  或 同在一个斜线上
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    //打印最终结果
    private void print() {
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
