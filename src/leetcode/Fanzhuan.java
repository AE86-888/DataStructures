package leetcode;

import java.util.HashSet;

public class Fanzhuan {
    static ListNode head;

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        //ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        //listNode3.next = listNode4;
        head = listNode1;
        show(head);
        ListNode listNode = swapPairs(head);
        System.out.println("反转后：");
        show(listNode);


    }

    //
    public static void show(ListNode cur) {
        if (cur == null) {
            return;
        }
        while (cur != null) {
            System.out.print(cur + " ");
            cur = cur.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preHead = new ListNode(0, head);//虚拟头节点
        ListNode cur = preHead;
        while (head != null && head.next != null) {
            //1.
            cur.next = cur.next.next;
            //2.
            head.next = cur.next.next;
            cur.next.next = head;
            cur = cur.next.next;
            head = cur.next;
        }
        return preHead.next;
    }

    //循环链表
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> list = new HashSet<>();
        ListNode cur = head;
        boolean isAdd = true;
        while (cur != null) {
            isAdd = list.add(cur);
            if (!isAdd) {
                break;
            }
            cur = cur.next;
        }
        return cur;
    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        //双指针法
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next.next;
            if (fast == null) {
                return null;
            }
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    //两个链表相交
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode curA = headA;
        ListNode curB = headB;
        int sizeA = 1;
        int sizeB = 1;

        //计算链表长度
        while (curA != null) {
            sizeA++;
            curA = curA.next;
        }
        while (curB != null) {
            sizeB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        if (sizeA > sizeB) {
            //curA 先移动curB的位置
            for (int i = 0; i < sizeA - sizeB; i++) {
                curA = curA.next;
            }
        } else if (sizeA < sizeB) {
            for (int i = 0; i < sizeB - sizeA; i++) {
                curB = curB.next;
            }
        }
        while (curA != null && curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }

    //删除倒数第n个链表
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode preHead = new ListNode(0, head);
        ListNode fast = preHead;
        ListNode slow = preHead;
        for (int i = 0; i <= n; i++) {//让fast先走n+1步
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return preHead.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode preHead = new ListNode(0, head);//虚拟头节点
        ListNode cur = head;
        int count = 1;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        int index = 1;
        ListNode pre = preHead;

        while (index < count - n) {
            pre = pre.next;
            index++;
        }
        pre.next = pre.next.next;
        return preHead.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val + '}';
    }
}

