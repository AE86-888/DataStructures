package leetcode.hashtable;

import com.sun.applet2.preloader.event.InitEvent;
import com.sun.deploy.ref.AppModel;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.lang.reflect.Array;
import java.util.*;

//hashtable 相关题目
public class Solution {
    public static void main(String[] args) {
//        String s = "aacc";
//        String t = "ccac";
//        boolean anagram = isAnagram(s, t);
//        System.out.println(anagram);
//
//        int[] nums1 = {1, 2};
//        int[] nums2 = {-2, -1};
//        int[] nums3 = {-1, 2};
//        int[] nums4 = {0, 2};
//        int i = fourSumCount(nums1, nums2, nums3, nums4);
//        System.out.println(i);

        //四数之和
        //int[] nums = {-2, -1, -1, 1, 1, 2, 2};
        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3};
        int target = 0;
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.fourSum(nums, target);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    /**
     * 18. 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return lists;
        }
        Arrays.sort(nums);//升序
        for (int i = 0; i < nums.length; i++) {
            //和三数之和的区别，不可以加下面这个条件
//            if (nums[i] > target) {
//                return lists;
//            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                //不可以加 下面这个条件
//                if (nums[i] + nums[j] > target) {
//                    return lists;
//                }
                //注意去重的写法
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        List<Integer> arrList = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                        lists.add(arrList);
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    }
                }
            }
        }
        return lists;
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    //方法1：双指针法
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return lists;
        }
        Arrays.sort(nums);//排序
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return lists;
            }
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    lists.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //去除三元组中重复的
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                }
            }
        }
        return lists;
    }

    /**
     * 383. 赎金信
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        //键:magazine中的字符；值：每个字符出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = magazine.toCharArray();
        for (char c : arr) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        char[] arr2 = ransomNote.toCharArray();
        for (char c : arr2) {
            if (!map.containsKey(c) || map.get(c) == 0) {
                return false;
            }
            map.put(c, map.get(c) - 1);
        }
        return true;
    }

    //方法二：比用map占用的空间小
    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] record = new int[26];//26个英文字母
        char[] chars = magazine.toCharArray();
        for (char c : chars) {
            record[c - 'a']++;//数组是引用类型；
        }
        char[] ran_char = ransomNote.toCharArray();
        for (char c : ran_char) {
            if (record[c - 'a'] <= 0) {
                return false;
            }
            record[c - 'a']--;
        }
        return true;
    }

    /**
     * 454. 四数相加 II
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    //
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                if (map.containsKey(sum)) {
                    Integer integer = map.get(sum);
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }
        //遍历nums3 和 nums4
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int sum = nums3[i] + nums4[j];
                if (map.containsKey(-sum)) {
                    count += map.get(-sum);
                }
            }
        }
        return count;
    }

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值target
     * 的那 两个 整数，并返回它们的数组下标。
     *
     * @param nums 数组 目标值
     * @return
     */
    //方法一：暴力法
    public int[] twoSum(int[] nums, int target) {
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    index1 = i;
                    index2 = j;
                    break;
                }
            }
        }
        if (index1 == index2) {
            return new int[0];
        } else {
            int[] res = new int[2];
            res[0] = index1;
            res[1] = index2;
            return res;
        }
    }

    //方法二：使用hashmap
    public int[] twoSum2(int[] nums, int target) {
        //键：数组中的元素；值:数组索引
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer j = map.get(target - nums[i]);
            if (j != null && j != i) {
                int[] res = new int[2];
                res[0] = i;
                res[1] = j;
                return res;
            }
        }
        return new int[0];
    }

    /**
     * 题目：242.有效的字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] s_char = s.toCharArray();
        char[] t_char = t.toCharArray();
        Arrays.sort(s_char);
        Arrays.sort(t_char);
        for (int i = 0; i < s_char.length; i++) {
            if (t_char[i] != s_char[i]) {
                return false;
            }
        }
        return true;
    }

    //方法二： 利用hashtable (数组来解决)
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        //因为是全是小写字母，26个字母
        int[] record = new int[26];//记录字符出现的次数;
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;
            record[t.charAt(i) - 'a']--;
        }
        for (int j : record) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 349. 两个数组的交集
     *
     * @param nums1 数组
     * @param nums2 数组
     * @return 返回两盒数组的交集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            set2.add(i);
        }
        ArrayList<Integer> list = new ArrayList<>(set1);
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean add = set2.add(list.get(i));
            if (!add) {
                list1.add(list.get(i));
            }
        }
        int[] res = new int[list1.size()];
        for (int i = 0; i < list1.size(); i++) {
            res[i] = list1.get(i);
        }
        return res;
    }

    /**
     * 202. 快乐数
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        do {
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
            slow = bitSquareSum(slow);
        } while (fast != slow);
        return slow == 1;
    }

    //就算数字 每一位的平方和
    public int bitSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int bit = n % 10;
            sum += bit * bit;
            n /= 10;
        }
        return sum;
    }

    //方法二：使用哈希表
    public boolean isHappy2(int n) {
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        while (true) {
            int i = bitSquareSum(n);
            if (!set.add(i)) {
                return i == 1;
            }
        }
    }
}
