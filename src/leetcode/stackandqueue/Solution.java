package leetcode.stackandqueue;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] arr = solution.maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 347. 前 K 个高频元素
     *
     * @param nums
     * @param k
     * @return
     */

    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        // 根据map的value值正序排，相当于一个小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }

    /**
     * 239. 滑动窗口最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 1) {
            return nums;
        }
        int[] res = new int[nums.length - k + 1];
        int left = 0;
        int right = k - 1;
        int index = 0;
        while (right < nums.length) {
            int max = getMax(nums, left, right);
            res[index++] = max;
            left++;
            right++;
        }
        return res;
    }

    //返回数组指定范围内的最大值
    public int getMax(int[] nums, int left, int right) {
        int max = nums[left];
        for (int i = left + 1; i <= right; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }

    /**
     * 150. 逆波兰表达式求值
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("+");
        hashSet.add("-");
        hashSet.add("*");
        hashSet.add("/");

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (hashSet.contains(tokens[i])) {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = cal(num1, num2, tokens[i]);
                stack.add(res + "");
            } else {//是数字
                stack.add(tokens[i]);
            }
        }
        return Integer.parseInt(stack.pop());
    }


    //计算结果
    public int cal(int num1, int num2, String c) {
        if ("+".equals(c)) {
            return num1 + num2;
        } else if ("-".equals(c)) {
            return num1 - num2;
        } else if ("*".equals(c)) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     *
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || s.charAt(i) != stack.peek()) {
                stack.add(s.charAt(i));
            } else if (s.charAt(i) == stack.peek()) {
                stack.pop();
            }
        }
        StringBuilder str = new StringBuilder();
        while (!stack.isEmpty()) {
            str.append(stack.pop());
        }
        return (str.reverse()).toString();
    }

    /**
     * 20. 有效的括号
     *
     * @param s
     * @return
     */
    //方法二：遇到 左括号，在栈 中放入 右括号
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.add(')');
            } else if (s.charAt(i) == '[') {
                stack.add(']');
            } else if (s.charAt(i) == '{') {
                stack.add('}');
            } else if (stack.isEmpty() || s.charAt(i) != stack.peek()) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    //方法1
    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || !isPair(stack.peek(), s.charAt(i))) {
                stack.add(s.charAt(i));
            } else if (!stack.isEmpty() && isPair(stack.peek(), s.charAt(i))) {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    //判断两个字符是不是一对
    public boolean isPair(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || ((c1 == '[' && c2 == ']'))
                || ((c1 == '{' && c2 == '}'));
    }
}
