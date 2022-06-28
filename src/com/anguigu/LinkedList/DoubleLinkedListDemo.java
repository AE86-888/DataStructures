package com.anguigu.LinkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList dl = new DoubleLinkedList();
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
//        dl.add(hero1);
//        dl.add(hero2);
//        dl.add(hero3);
//        dl.add(hero4);
        System.out.println("按照编号的添加方式");
        dl.addByOrder(hero4);
        dl.addByOrder(hero3);
        dl.addByOrder(hero1);
        dl.addByOrder(hero2);
        dl.list();
//        System.out.println("===测试修改");
//        HeroNode2 newNode = new HeroNode2(2, "njl", "lv");
//        dl.update(newNode);
//        dl.list();
//        System.out.println("====删除后的链表=====");
//        dl.del(3);
//        dl.list();

    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //在双链表尾部添加节点
    public void add(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    //按照编号顺序添加节点
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        boolean flag = false;//标记双链表中是否已经存在 id 相同的节点
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
            if (temp.next != null) {
                temp.next.pre = heroNode;
            }
            heroNode.next = temp.next;
            temp.next = heroNode;
            heroNode.pre = temp;

        }
    }

    // 删除节点
    public void del(int id) {
        if (head.next == null) {
            System.out.println("链表为空~~，删除失败");
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//标记是否找到待删除的节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完
            }
            if (temp.id == id) {
                flag = true;//找到待删除的节点，temp指向待删除节点
                break;
            }
            temp = temp.next;
        }
        if (flag) {//此时temp指向的是待删除的节点
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            } else {
                temp.pre = null;
            }
            temp.pre.next = temp.next;
        } else {
            System.out.printf("该链表中不存在节点 id = %d 的节点，删除失败\n", id);
        }
    }

    //修改节点的信息（节点的id信息不可修改）
    public void update(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("单链表为空~~~");
            return;
        }
        HeroNode2 temp = head.next;
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
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HeroNode2 {
    public int id;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}


