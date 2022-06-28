package com.anguigu.LinkedList;

import javafx.util.BuilderFactory;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList cl = new CircleSingleLinkedList();
        cl.add(25);
        cl.list();
        System.out.println("=====小孩出圈顺序======");
        cl.countBoy(2);
    }
}

class CircleSingleLinkedList {
    Boy first = null;//指向环形单向链表的第一个节点

    //在链表中添加节点
    public void add(int nums) {
        if (nums <= 0) {
            System.out.println("最少添加一个节点~");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                boy.setNext(first);
                curBoy = boy;
                continue;
            }
            curBoy.setNext(boy);
            curBoy = boy;
            boy.setNext(first);
        }
    }

    //遍历
    public void list() {
        if (first == null) {
            System.out.println("链表为空~");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号:" + curBoy.getId());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序
    public void countBoy(int m) {
        if (m <= 0) {
            System.out.println("m不可以小于0");
            return;
        }
        if (first == null) {
            System.out.println("链表为空~");
            return;
        }
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }//让helper 指向最后一个节点
        //first 和 helper 往后移动m-1个节点
        int count = 0;
        while (true) {
            if (count == m - 1) {
                System.out.print(first.getId() + "\t");//小孩出列
                if (first.getNext() == first){
                    first = null;
                    break;
                }
                first = first.getNext();
                helper.setNext(first);
                count = 0;
            }
            first = first.getNext();
            helper = helper.getNext();
            count++;
        }
    }
}

class Boy {//定义Boy 节点
    private int id;
    private Boy next;

    public Boy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
