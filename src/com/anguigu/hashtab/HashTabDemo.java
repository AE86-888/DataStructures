package com.anguigu.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        while (true) {
            System.out.println("输入add 添加元素");
            System.out.println("输入list 遍历");
            System.out.println("输入find 查找");
            System.out.println("输入remove 删除");
            System.out.println("输入exit 退出");
            Scanner scanner = new Scanner(System.in);
            String key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("请输入id：");
                    int id = Integer.parseInt(scanner.next());
                    System.out.print("请输入姓名：");
                    String name = scanner.next();
                    hashTable.add(new Emp(id, name));
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.print("请输入id");
                    id = Integer.parseInt(scanner.next());
                    hashTable.findById(id);
                    break;
                case "remove":
                    System.out.print("请输入id");
                    id = Integer.parseInt(scanner.next());
                    hashTable.removeById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }
}

class HashTable {
    private EmpLinkedList[] empLinkedLists;

    private int size;

    public HashTable(int size) {
        empLinkedLists = new EmpLinkedList[size];
        this.size = size;
        for (int i = 0; i < size; i++) {//不初始化，则数组中的元素默认是null
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //添加元素
    public void add(Emp emp) {
        //根据id 计算出应该存在哪个链表中
        int no = getNoById(emp.id);
        empLinkedLists[no].add(emp);
    }

    //查找
    public void findById(int id) {
        int no = getNoById(id);
        Emp emp = empLinkedLists[no].findById(id);
        if (emp != null) {
            System.out.printf("在第%d个链表找到了id=%d 的员工~", no + 1, id);
        } else {
            System.out.println("没有找到~");
        }
    }

    public void removeById(int id) {
        int no = getNoById(id);
        boolean remove = empLinkedLists[no].remove(id);
        if (remove) {
            System.out.println("删除成功~");
        }
    }

    //遍历
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i + 1);
        }
    }

    public int getNoById(int id) {
        return id % size;
    }
}

class EmpLinkedList {
    private Emp head;

    public EmpLinkedList() {
    }

    //在链表中添加元素
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //删除元素
    public boolean remove(int id) {
        boolean answer = false;
        if (head == null) {
            System.out.println("没有该员工，删除失败~");
            return answer;
        }
        if (head.id == id){
            head = head.next;
            return true;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next.id == id) {
                curEmp.next = curEmp.next.next;
                answer = true;
                break;
            }
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        return answer;
    }

    //查找
    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                return curEmp;
            }
            curEmp = curEmp.next;
            if (curEmp == null) {
                return null;
            }
        }
    }

    //遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "个链表为空~");
            return;
        }
        System.out.print("第" + no + "个链表信息如下：");
        Emp curEmp = head;
        while (true) {
            System.out.print(" =>id=" + curEmp.id + " name=" + curEmp.name);
            curEmp = curEmp.next;
            if (curEmp == null) {
                break;
            }
        }
        System.out.println();
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
