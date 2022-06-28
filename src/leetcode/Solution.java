package leetcode;

import javafx.util.BuilderFactory;
import jdk.nashorn.internal.objects.NativeUint16Array;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import sun.applet.AppletResourceLoader;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
//        int i = firstBadVersion(2);
//        System.out.println(i);
//        //int[] nums = {1,3,5,6};
//        int[] nums = {1, 3, 5, 6};
//        //int[] nums = {1,3};
//        int target = 2;
//        int i = searchInsert(nums, target);
//        System.out.println(i);
//        Stack<Integer> stack = new Stack<>();
//        stack.add(1);
//        stack.add(2);
//        stack.add(3);
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());

//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(0, 1);
//        list.add(0, 2);
//        list.add(0, 3);
//        System.out.println(list);
//        Object[] objects = list.toArray();
//        Integer[] integers = list.toArray(new Integer[list.size()]);.
//        ListNode listNode1 = new ListNode(1);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(5);
//        ListNode head = listNode1;
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        listNode4.next = listNode5;
//        System.out.println("反转前：");
//        ListNode curNode = head;
//        while (curNode != null) {
//            System.out.println(curNode);
//            curNode = curNode.next;
//        }
//        System.out.println("反转后:");
//        ListNode new_head = reverseList3(head);
//        ListNode cur = new_head;
//        while (cur != null) {
//            System.out.println(cur);
//            cur = cur.next;
//        }
        int[] nums = {3, 2, 2, 3};
        int i = removeElement(nums, 3);
        for (int j = 0; j < i; j++) {
            System.out.print(nums[j] + " ");
        }
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == 6) {
                return mid;
            } else if (mid < 6) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        int mid = left + (right - left) / 2;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static boolean isBadVersion(int mid) {
        int bad = 2;
        return mid >= bad;
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] >= target) {
            return left;
        } else if (nums[right] < target) {
            return right + 1;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    //反转单链表
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode curNode = head;
        while (curNode != null) {
            list.add(0, curNode);
            curNode = curNode.next;
        }
        head = list.get(0);
        curNode = head;
        for (int i = 1; i < list.size(); i++) {
            curNode.next = list.get(i);
            curNode = curNode.next;
            if (i == list.size() - 1) {
                curNode.next = null;
            }
        }
        return head;
    }

    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curNode = head;
        ListNode preNode = null;
        ListNode temp = curNode.next;
        while (true) {
            curNode.next = preNode;
            preNode = curNode;
            curNode = temp;
            if (curNode.next == null) {
                curNode.next = preNode;
                break;
            }
            temp = curNode.next;
        }
        return curNode;
    }

    //使用递归
    public static ListNode reverseList3(ListNode head) {
        return recur(head, null);
    }

    public static ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ListNode res = recur(cur.next, cur);  // 递归后继节点
        cur.next = pre;              // 修改节点引用指向
        return res;
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {//构建map；键：原链表节点; 值：新链表对应的节点
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        //构建新链表的next 和 random
        cur = head;//将cur 重新指向头节点
        while (cur != null) {
            Node cur1 = map.get(cur);//获取新链表的当前节点
            cur1.next = map.get(cur.next);
            cur1.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        //拼接原链表和新链表
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = cur.next.next;
        }
        cur = head;//将cur 重新指向原链表的头节点
        Node head1 = cur.next;//新链表的头节点
        while (cur != null) {
            Node cur1 = cur.next;//指向新链表的当前节点
            if (cur.random == null) {
                cur1.random = null;
            } else {
                cur1.random = cur.random.next;//构建新链表的 random

            }
            cur = cur.next.next;
        }
        cur = head;
        while (cur != null) {//拆分原链表与新链表
            Node cur1 = cur.next;
            cur.next = cur.next.next;
            cur = cur.next;
            if (cur1.next != null) {
                cur1.next = cur1.next.next;
            }
        }
        return head1;
    }

    //两数之和
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);//pre 指向新链表的头节点
        ListNode cur = pre;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        int carry = 0;//表示进位
        int sum = 0;
        while (cur1 != null || cur2 != null) {
            if (cur1 == null) {
                sum = cur2.val + carry;
            } else if (cur2 == null) {
                sum = cur1.val + carry;
            } else {
                sum = cur1.val + cur2.val + carry;
            }
            carry = sum / 10;
            sum = sum % 10;//当前新节点的值
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (cur1 != null) {
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                cur2 = cur2.next;
            }
        }
        if (carry > 0) {
            cur.next = new ListNode(1);
        }
        return pre.next;
    }

    //移除数组中的元素
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                for (int j = i; j < nums.length - 1 - count; j++) {
                    nums[j] = nums[j + 1];
                }
                if (i == nums.length - count - 1) {
                    count++;
                    break;
                }
                i--;
                count++;
            }
        }

        return nums.length - count;
    }

    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        int k = nums.length - 1;
        while (i <= j) {
            if (nums[i] * nums[i] < nums[j] * nums[j]) {
                result[k--] = nums[j] * nums[j];
                j--;
            } else {
                result[k--] = nums[i] * nums[i];
                i++;
            }
        }
        return result;
    }

    //
    public int minSubArrayLen(int target, int[] nums) {
        int result = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    if (j - i + 1 < result) {
                        result = j - i + 1;
                        break;
                    }
                }
            }
        }
        if (result == nums.length + 1) {
            return 0;
        }
        return result;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int result = nums.length + 1;
        int left = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                result = Math.min(result, i - left + 1);
                sum -= nums[left++];
            }
        }
        if (result == nums.length + 1) {
            return 0;
        }
        return result;
    }

    /**
     * @param n
     * @return 返回n*n的顺时针螺旋的矩阵
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int num = 1;
        while (num <= n * n) {
            //从左到右填充
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            top++;
            //从上到下填充
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--;
            //从右到左填充
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = num++;
            }
            bottom--;
            //从下往上填充
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = num++;
            }
            left++;
        }
        return matrix;
    }

    public int[][] generateMatrix2(int n) {
        int start_x = 0, start_y = 0;//起始位置
        int offset = 1;//每次循环的偏移量
        int num = 1;//要填入的数字
        int loop = n / 2;//循环次数
        int[][] matrix = new int[n][n];
        int i, j;
        int mid = n / 2;//中间位置
        while (loop-- > 0) {
            i = start_x;
            j = start_y;
            //从左到右
            for (; j < start_y + n - offset; j++) {
                matrix[start_x][j] = num++;
            }
            //从上到下
            for (; i < start_x + n - offset; i++) {
                matrix[i][j] = num++;
            }
            //从左到右
            for (; j > start_y; j--) {
                matrix[i][j] = num++;
            }
            //从下到上
            for (; i > start_x; i--) {
                matrix[i][j] = num++;
            }

            start_x++;
            start_y++;
            offset += 2;
        }
        if (n % 2 != 0) {
            matrix[mid][mid] = num++;
        }
        return matrix;
    }

    //删除链表中的节点
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode curNode = head;
        while (curNode != null && curNode.next != null) {
            if (curNode.next.val == val) {
                curNode.next = curNode.next.next;
            } else {
                curNode = curNode.next;
            }
        }
        curNode = head;
        if (head.val == val) {
            curNode = curNode.next;
        }
        return curNode;
    }

    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        //使用一个虚拟的头节点，指向真正的头节点
        ListNode pre = new ListNode(val + 1);
        pre.next = head;
        ListNode cur = pre;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return pre.next;
    }

    //反转链表
    public ListNode reverseList_(ListNode head) {
        return re(head, null);
    }

    public ListNode re(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        return re(cur.next, cur);
    }

    //两两交换链表节点
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preHead = new ListNode(0, head);//虚拟头节点
        ListNode cur  = preHead;
        while (head != null){
            //1.
            cur.next = cur.next.next;
            //2.
            head.next = cur.next.next;
            cur.next.next = head;
            cur = cur.next.next;
            head = cur.next.next.next;
        }
        return preHead.next;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

