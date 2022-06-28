package com.anguigu.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看你队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.print("请输入要添加的元素：");
                    int num = Integer.parseInt(scanner.next());
                    try {
                        arrayQueue.addQueue(num);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    break;
                case 'g':
                    try {
                        System.out.println("取出的元素是：" + arrayQueue.getQueue());
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("队列头部元素：" + arrayQueue.headQueue());
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    break;
            }
        }
    }
}

//使用数组模拟队列
class ArrayQueue {
    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾部
    private int[] arr;//用于存放数据模拟队列

    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部，分析出front指向队列头的前一个位置
        rear = -1;//指向队列尾部，指向队列尾部的数据（即，就是队列的最后一个数据）
    }

    //判断队列是否满了
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == -1;
    }

    // 添加元素
    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        rear++;
        arr[rear] = n;
    }

    //获取队列的数据,出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    // 显示队列的所有元素
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 显示队列的头数据，不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front + 1];
    }
}

