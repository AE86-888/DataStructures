package leetcode.shejilianbiao;

import java.util.Map;

public class MyLinkedList {
    Node head;//头节点
    int count;//记录当前节点的个数

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
//        myLinkedList.addAtHead(2);
//        myLinkedList.deleteAtIndex(1);
//        myLinkedList.addAtHead(2);
//        myLinkedList.addAtHead(7);
//        myLinkedList.addAtHead(3);
//        myLinkedList.addAtHead(2);
//        myLinkedList.addAtHead(5);
//        myLinkedList.addAtTail(5);
//        myLinkedList.get(5);
//        myLinkedList.deleteAtIndex(6);
//        myLinkedList.deleteAtIndex(4);
//        myLinkedList.addAtHead(1);
//        myLinkedList.addAtTail(3);
//        myLinkedList.addAtIndex(1,2);
//        myLinkedList.get(1);
//        myLinkedList.deleteAtIndex();
//        myLinkedList.get();


//
//        System.out.println("删除前：");
//        myLinkedList.show();
//        //测试删除节点
//        myLinkedList.deleteAtIndex(4);
//        //查看链表
//        System.out.println("删除后：");
//        myLinkedList.show();
    }

    public MyLinkedList() {
        head = new Node(0);//虚拟头节点
        count = 0;
    }

    //查看链表
    public void show() {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur + " ");
            cur = cur.next;
        }
    }

    public int get(int index) {
        if (index >= count || index < 0) {
            return -1;
        }
        Node curNode = head;
        for (int i = 0; i <= index; i++) {
            curNode = curNode.next;
        }
        return curNode.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(count, val);
    }

    public void addAtIndex(int index, int val) {
        if (index <= 0) {
            //直接在插入到头节点位置
            Node node = new Node(val);
            if (head.next != null) {
                node.next = head.next;
                head.next.pre = node;
            }
            head.next = node;
            node.pre = head;
            count++;
            return;
        }
        if (index > count) {
            return;
        }
        Node preNode = head;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        Node node = new Node(val);
        if (preNode.next == null) {
            preNode.next = node;
            node.pre = preNode;
        } else {
            node.next = preNode.next.next;
            preNode.next.pre = node;
            preNode.next = node;
            node.pre = preNode;
        }
        count++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= count) {//判断index是否有效
            return;
        }
        Node preNode = head;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        if (preNode.next.next != null) {
            preNode.next.pre = preNode;
        }
        preNode.next = preNode.next.next;
        count--;
    }

}

class Node {
    int val;
    Node next;
    Node pre;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}

