package com.anguigu.LinkedList;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero6 = new HeroNode(6, "lv", "lv");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero2);

        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero6);
        singleLinkedList.list();

        HeroNode hero5 = new HeroNode(5, "jack", "jack");
        HeroNode hero7 = new HeroNode(7, "bob", "bob");
        HeroNode hero8 = new HeroNode(8, "njl", "njl");
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(hero5);
        singleLinkedList1.addByOrder(hero7);
        singleLinkedList1.addByOrder(hero8);
        System.out.println("合并后的链表");
        SingleLinkedList list3 = mergeList(singleLinkedList1.getHead(), singleLinkedList.getHead());
        list3.list();
//        System.out.println("逆序打印单链表====");
//        reversePrint(singleLinkedList.getHead());
        /*
        System.out.println("修改后=======");
        singleLinkedList.update(new HeroNode(1, "小江", "jishiyu"));
        singleLinkedList.list();
        //测试删除节点
        singleLinkedList.del(4);
        System.out.println("====删除节点后====");
        singleLinkedList.list();
        System.out.println("单链表中有效节点的个数：" + getLength(singleLinkedList.getHead()));
        System.out.println("倒数第3个节点：" + getKNode(singleLinkedList.getHead(), 3));
        reverseSingleList(singleLinkedList.getHead());
        System.out.println("====反转后的链表=====");
        singleLinkedList.list();
         */
    }

    //合并两个有序的单链表，合并后的链表依旧是有序的
    public static SingleLinkedList mergeList(HeroNode head1, HeroNode head2) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode head = singleLinkedList.getHead();
        if (head1.next == null || head2.next == null) {
            return null;
        }
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        while (cur1 != null && cur2 != null) {
            HeroNode node = null;
            if (cur1.id < cur2.id) {
                node = cur1;
                cur1 = cur1.next;
            } else if (cur1.id > cur2.id) {
                node = cur2;
                cur2 = cur2.next;
            } else {
                node = cur1;
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            singleLinkedList.addByOrder(node);
        }
        if (cur1 != null){
            singleLinkedList.add(cur1);
        }else if (cur2 != null){
            singleLinkedList.add(cur2);
        }

        return singleLinkedList;
    }

    /**
     * 逆序打印单链表
     *
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        Stack<HeroNode> heroNodes = new Stack<>();
        if (head.next == null) {
            System.out.println("单链表为空~~~");
            return;
        }
        HeroNode cur = head.next;
        while (cur != null) {
            heroNodes.add(cur);
            cur = cur.next;
        }
        while (heroNodes.size() > 0) {
            System.out.println(heroNodes.pop());
        }
    }

    /**
     * 反转链表
     *
     * @param head 原链表的头节点
     */
    public static void reverseSingleList(HeroNode head) {
        int length = getLength(head);
        if (length <= 1) {
            System.out.println("链表长度小于或等于1，不需要反转");
            return;
        }
        HeroNode reverseHead = new HeroNode(0, "", "");
        HeroNode cur = head.next;
        while (true) {
            if (cur == null) {
                break;//已经遍历完 原链表
            }
            if (reverseHead.next == null) {
                reverseHead.next = cur;
                cur = cur.next;
                reverseHead.next.next = null;
            } else {
                HeroNode temp = cur.next;//指向当前链表的下一个节点，否则当前节点next断开后，就失去原链表后面的数据；
                cur.next = reverseHead.next;
                reverseHead.next = cur;
                cur = temp;
            }
        }
        head.next = reverseHead.next;
    }

    /**
     * @param head  单链表的头节点
     * @param index 倒数第 index 个节点
     * @return 倒数第 index 个节点
     */
    public static HeroNode getKNode(HeroNode head, int index) {
        int length = getLength(head);
        if (index > length || index <= 0) {
            return null;//超出了单链表的范围
        }
        HeroNode cur = head.next;
        int toal = 1;
        while (true) {
            if (toal == (length - index + 1)) {
                break;
            }
            cur = cur.next;
            toal++;
        }
        return cur;
    }

    /**
     * @param head 表示一个单链表的头节点
     * @return 单链表的有效节点个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        HeroNode cur = head.next;
        int length = 0;
        while (true) {
            if (cur == null) {
                break;
            } else {
                length++;
                cur = cur.next;
            }
        }
        return length;
    }
}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //在单链表尾部添加节点
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //按照编号顺序添加节点
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;//标记单链表中是否已经存在 id 相同的节点
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.id > heroNode.id) {
                break;
            } else if (temp.next.id == heroNode.id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("已经存在id=%d一样的节点，插入失败~\n", heroNode.id);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 删除节点
    public void del(int id) {
        if (head.next == null) {
            System.out.println("链表为空~~，删除失败");
        }
        HeroNode temp = head;
        boolean flag = false;//标记是否找到待删除的节点
        while (true) {
            if (temp.next == null) {
                break;//已经遍历完
            }
            if (temp.next.id == id) {
                flag = true;//找到待删除的节点，temp指向待删除节点的前一个节点
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("该链表中不存在节点 id = %d 的节点，删除失败\n", id);
        }
    }

    //修改节点的信息（节点的id信息不可修改）
    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("单链表为空~~~");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;//标记是否找到要修改的节点
        while (true) {
            if (temp == null) {
                break;// 表示已经遍历完 单链表
            }
            if (temp.id == heroNode.id) {
                flag = true;//找到要修改的节点
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.println(String.format("该链表中不存在 id=%d 的节点", heroNode.id));
        }
    }

    //遍历单链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("单链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int id;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
